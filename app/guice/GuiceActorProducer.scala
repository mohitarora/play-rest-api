package guice

import play.api.Logger
import com.google.inject.Injector
import akka.actor.{Actor, IndirectActorProducer}

/**
 *
 * An indirect actor producer that lets Google Guice create the Actor instances.
 *
 * @param injector - Guice Injector that will create the actors
 * @param actorType - Actor Type that needs to be created.
 */
class GuiceActorProducer(injector: Injector, actorType: Class[Actor]) extends IndirectActorProducer {

  override def produce: Actor = {
    val actor: Actor = injector.getInstance(actorType)
    Logger.info("Initialized Actor " + actor.self.path + " via Guice")
    actor
  }

  override def actorClass: Class[_ <: Actor] = actorType

}
