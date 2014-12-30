import akka.actor.{ActorRef, ActorSystem, Props, Actor, Inbox}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

object HelloAkkaScala extends App
{
    val system = ActorSystem("helloakka")
    val testActor1 = system.actorOf(Props[TestActor1], "testActor1")
    val testActor2 = system.actorOf(Props[TestActor2], "testActor2")

    system.scheduler.schedule(0 seconds, 1 seconds, testActor2, SendMessage(testActor1,
        TestMessage("It works!", 1)))(system.dispatcher, testActor2)
}


class TestActor1 extends Actor
{
    var messageQue: Set[(ActorRef, Int)] = Set()

    def receive =
    {
        case TestMessage(message: String, messageId: Int) =>
        {
            if (!messageQue.contains((sender, messageId)))
                println("Сообщение: " + message + " id: " + messageId)
            else
                println("Сообщение уже обрабатывалось! " + messageQue.size)
            messageQue = messageQue + ((sender, messageId))
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


class ClientActor extends Actor
{
    val system =
        ActorSystem("System1", ConfigFactory.load().getConfig("systemOne"))
    val remote = system.actorOf(Props[TestRemote], "testRemote")

    def receive =
    {
        case str: String => remote ! str
    }
}

class TestRemote extends Actor
{
    def receive =
    {
        case str: String => println(str)
    }
}

class CreationActor extends Actor
{
    val system1 = ActorSystem("System1", ConfigFactory.load().getConfig("systemOne"))
    val system2 = ActorSystem("System2", ConfigFactory.load().getConfig("systemTwo"))

    val testActor1 = system1.actorOf(Props[TestActor1], "testActor1")
    val testActor2 = system1.actorOf(Props[TestActor2], "testActor2")

    def receive =
    {
        case "start" =>
        {
            system1.scheduler.schedule(0 seconds, 1 seconds, testActor2, SendMessage(testActor1,
                TestMessage("It works!", 1)))(system2.dispatcher, testActor2)
        }
    }
}


case class TestMessage(message: String, messageId: Int)

case class SendMessage(to: ActorRef, message: TestMessage)