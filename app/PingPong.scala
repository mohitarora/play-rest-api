import akka.actor.{Props, ActorSystem, ActorLogging, Actor}

object PingPong extends App {

  final case object Ball

  class PingPongActor extends Actor with ActorLogging {

    var count = 0
    
    val name = self.path.name

    override def receive: Receive = {
      case Ball => {
        count += 1
        println(s"${name} Counter: ${count}")
        Thread.sleep(1000)
        sender ! Ball
      }
    }
  }

  val system = ActorSystem("pingpong")
  val pingActorRef = system.actorOf(Props[PingPongActor], "pingActor")
  val pongActorRef = system.actorOf(Props[PingPongActor], "pongActor")

  pingActorRef tell(Ball, pongActorRef)

}
