package guice

import akka.actor.{Actor, IndirectActorProducer}
import com.google.inject.Injector
import play.api.Logger

/**
 *
 * An indirect actor producer that lets Google Guice create the Actor instances.
 *
 * @param injector - Guice Injector that will create the actors
 * @param actorType - Actor Type that needs to be created.
 */
class GuiceActorProducer(injector: Injector, actorType: Class[Actor]) extends IndirectActorProducer {

  override def produce: Actor = {
    Logger.info("Initializing " + actorType.getSimpleName + " via Guice")
    injector.getInstance(actorType)
  }

  override def actorClass: Class[_ <: Actor] = classOf[Actor]

}
