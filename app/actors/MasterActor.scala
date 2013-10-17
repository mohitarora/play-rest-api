package actors

import akka.actor.Actor

class MasterActor extends BaseActor {

  /**
   * Master Actor will supervise Counting Actor.
   */
  val countingActor = actor(classOf[CountingActor])

  override def receive: Actor.Receive = {
    case count: Count => countingActor.forward(count)
    case getCount: GetCount => countingActor.forward(getCount)
  }

}
