package actors

import akka.actor.Actor

class MasterActor extends Actor {

  override def receive: Actor.Receive = {
    case x => // some behavior
  }

}
