package guice

import akka.actor._
import com.google.inject.Injector
import java.lang.reflect.Type

/**
 * An Akka Extension Provider
 */
object GuiceExtensionProvider extends ExtensionId[GuiceExtension] with ExtensionIdProvider {

  override def lookup = GuiceExtensionProvider

  /**
   * Is used by Akka to instantiate the Extension identified by this ExtensionId, internal use only.
   */
  override def createExtension(system: ExtendedActorSystem): GuiceExtension = new GuiceExtension(system)

}

/**
 * The Extension implementation.
 */
class GuiceExtension(system: ExtendedActorSystem) extends Extension {

  private var injector: Injector = _

  /**
   * Used to initialize the Guice Injector for the extension.
   */
  def initialize(injector: Injector) = this.injector = injector

  /**
   * Create a Props for the specified actorType using the GuiceActorProducer class.
   *
   * @param actorType The type of the actor to create Props for
   * @return a Props that will create the typed actor bean using Guice
   */
  def props(actorType: Type): Props = Props(classOf[GuiceActorProducer], injector, actorType)

}


