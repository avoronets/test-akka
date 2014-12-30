import akka.actor.{Props, ActorSystem}

/**
 * Created by Aleksey Voronets on 29.12.14.
 */
object TestRemoteApp extends App
{
  val system = ActorSystem("helloakka")
  val clientActor = system.actorOf(Props[ClientActor], "clientActor")

  clientActor ! "It works remotely"
}
