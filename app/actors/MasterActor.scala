package actors

import akka.actor.Actor

class MasterActor extends BaseActor {

  /**
   * Master Actor will supervise Counting Actor.
   */
  val countingActor = actor(classOf[CountingActor])

  override def receive: Actor.Receive = {
    case Count => countingActor.forward(Count)
    case GetCount => countingActor.forward(GetCount)
  }

}
