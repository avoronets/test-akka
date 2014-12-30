import akka.actor.{Props, ActorSystem}

/**
 * Created by Aleksey Voronets on 29.12.14.
 */
object CreationApp extends App {
  val system = ActorSystem("helloakka")
  val creationActor = system.actorOf(Props[CreationActor], "creationActor")
  creationActor ! "start"
}
