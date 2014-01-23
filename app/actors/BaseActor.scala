package actors

import akka.actor.{ActorRef, Actor}
import guice.GuiceExtensionProvider

/**
 * Base Actor for any common behaviour.
 */
trait BaseActor {

  this: Actor =>

  /**
   * Create an actor that current actor will supervise.
   * @param actorClass - Actor Class for which actor needs to be created
   * @param name - Actor name
   * @return - Actor Reference.
   */
  def actor(actorClass: Class[_ <: Actor], name: String): ActorRef =
    context.actorOf(GuiceExtensionProvider(context.system).props(actorClass), name)

}
