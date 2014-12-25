import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
 * Created by Aleksey Voronets on 25.12.14.
 */
object System2 extends App
{
  val root = ConfigFactory.load()
  val system = ActorSystem("System1", root.getConfig("systemTwo"))
  val testActor2 = system.actorOf(Props[TestActor1], "testActor2")
}
