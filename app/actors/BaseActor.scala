package actors

import akka.actor.{ActorLogging, ActorRef, Actor}
import guice.GuiceExtensionProvider

/**
 * Base Actor for any common behaviour.
 */
trait BaseActor extends Actor with ActorLogging {


  /**
   * Create an actor that current actor will supervise.
   * @param actorClass - Actor Class for which actor needs to be created
   * @return - Actor Reference.
   */
  protected def actor(actorClass: Class[_ <: Actor]): ActorRef =
    context.actorOf(GuiceExtensionProvider(context.system).props(actorClass), actorClass.getSimpleName)

}
