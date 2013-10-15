package actors

import akka.actor.Actor
import guice.GuiceExtensionProvider

/**
 * Base Actor for any common behaviour.
 */
trait BaseActor extends Actor {

  val extension = GuiceExtensionProvider.get(context.system)

}
