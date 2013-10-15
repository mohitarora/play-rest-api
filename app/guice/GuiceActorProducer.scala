package guice

import akka.actor.{Actor, IndirectActorProducer}
import com.google.inject.Injector

class GuiceActorProducer(injector: Injector, actorType: Class[Actor]) extends IndirectActorProducer {

  override def produce: Actor = {
    injector.getInstance(actorType)
  }

  override def actorClass: Class[_ <: Actor] = classOf[Actor]

}
