import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import com.typesafe.config.ConfigFactory

/**
 * Created by Aleksey Voronets on 25.12.14.
 */
object System1 extends App
{
  val root = ConfigFactory.load()
  val system = ActorSystem("System1", root.getConfig("systemOne"))
  val testActor1 = system.actorOf(Props[TestActor1], "testActor1")
  println(system)
  println("Remote app started")
}
