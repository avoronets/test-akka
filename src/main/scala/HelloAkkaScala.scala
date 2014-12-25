import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.concurrent.duration._

object HelloAkkaScala extends App {

  val system = ActorSystem("helloakka")

  val testActor1 = system.actorOf(Props[TestActor1], "testActor1")
  val testActor2 = system.actorOf(Props[TestActor2], "testActor2")

  system.scheduler.schedule(0 seconds, 1 seconds, testActor2, SendMessage(testActor1,
    TestMessage("It works!", 1)))(system.dispatcher, testActor2)

}



class TestActor1 extends Actor
{
  var messageQue: Set[Int] = Set()

  def receive =
  {
    case TestMessage(message: String, messageId: Int) =>
    {
      if(!messageQue.contains(messageId))
        println("Сообщение: " + message + " id: " + messageId)
      else println("Сообщение уже обрабатывалось! " + messageQue.size)
      messageQue = messageQue + messageId
    }
  }
}

class TestActor2 extends Actor
{
  def receive =
  {
    case SendMessage(to: ActorRef, message: TestMessage) => to ! message
  }
}

case class TestMessage(message: String, messageId: Int)

case class SendMessage(to: ActorRef, message: TestMessage)