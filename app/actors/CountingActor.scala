package actors

import akka.actor.Actor
import com.google.inject.Inject
import service.CountingService

class CountingActor @Inject()(countingService: CountingService) extends BaseActor {

  private var counter = 0

  def receive: Actor.Receive = {
    case count: Count => counter = countingService.increment(counter)
    case getCount: GetCount => sender ! counter
  }

}

class Count

class GetCount