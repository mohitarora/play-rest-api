package guice

import akka.actor._
import com.google.inject.Injector
import java.lang.reflect.Type

object GuiceExtensionProvider extends ExtensionId[GuiceExtension] with ExtensionIdProvider {

  override def lookup = GuiceExtensionProvider

  override def createExtension(system: ExtendedActorSystem): GuiceExtension = new GuiceExtension(system)

}

class GuiceExtension(system: ExtendedActorSystem) extends Extension {

  private var injector: Injector = null

  def initialize(injector: Injector) = {
    this.injector = injector
  }

  def props(actorType: Type): Props = {
    Props(classOf[GuiceActorProducer], injector, actorType)
  }

}


