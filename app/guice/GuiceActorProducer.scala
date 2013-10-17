package guice

import akka.actor.{Actor, IndirectActorProducer}
import com.google.inject.Injector
import play.api.Logger
import actors.ActorRegistry

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
    Logger.info(s"Initialized Actor $actor.self.path via Guice")
    // Add an entry to Actor Registry
    ActorRegistry.registry(actorClass) = actor.self.path
    actor
  }

  override def actorClass: Class[_ <: Actor] = actorType

}
