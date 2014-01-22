package actors

import akka.actor.Actor
import com.google.inject.Inject
import service.CountingService

class CountingActor @Inject()(countingService: CountingService) extends BaseActor {

  private var counter = 0

  def receive: Actor.Receive = {
    case Count => counter = countingService.increment(counter)
    case GetCount => sender ! counter
  }

}

case object Count

case object GetCount