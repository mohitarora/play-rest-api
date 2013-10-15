package actors

import akka.actor.Actor

class CountingActor extends BaseActor {

  def receive: Actor.Receive = {
    case x => // some behavior
  }

}
