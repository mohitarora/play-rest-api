package actors

import akka.actor.Actor

class MasterActor extends BaseActor {

  /**
   * Master Actor will supervise Counting Actor.
   */
  val countingActor = context.actorOf(extension.props(classOf[CountingActor]), classOf[CountingActor].getSimpleName)

  override def receive: Actor.Receive = {
    case x => // some behavior
  }

}
