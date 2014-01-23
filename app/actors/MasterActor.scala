package actors

import akka.actor.{ActorLogging, Actor}

class MasterActor extends Actor with ActorLogging with BaseActor {

  /**
   * Master Actor will supervise Counting Actor.
   */
  val countingActor = actor(classOf[CountingActor])

  override def receive: Actor.Receive = {
    case Count => countingActor.forward(Count)
    case GetCount => countingActor.forward(GetCount)
  }

}
