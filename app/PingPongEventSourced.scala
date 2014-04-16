import akka.actor.{Props, ActorSystem}
import akka.persistence.EventsourcedProcessor

object PingPongEventSourced extends App {

  final case object Ball

  final case object BallReceived

  class PingPongProcessor extends EventsourcedProcessor {

    var counter = 0

    val name = self.path.name

    def increment() = counter += 1

    override def receiveCommand: Receive = {
      case Ball => {
        increment()
        persist(BallReceived) {
          evt => {
            println(s"${name} Counter: ${counter}")
            Thread.sleep(1000)
            sender ! Ball
          }
        }
      }
    }

    override def receiveRecover: Receive = {
      case BallReceived =>
        increment()
        println(s"$name counter: $counter (recover)")
    }

  }

  val system = ActorSystem("pingpong")
  val pingActorRef = system.actorOf(Props[PingPongProcessor], "pingActor")
  val pongActorRef = system.actorOf(Props[PingPongProcessor], "pongActor")

  pingActorRef tell(Ball, pongActorRef)
}
