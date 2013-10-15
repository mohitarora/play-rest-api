package guice

import com.tzavellas.sse.guice.ScalaModule
import com.google.inject.Injector
import akka.actor.ActorSystem
import actors.MasterActor

class AppModule(injector: Injector) extends ScalaModule {

  def configure {
    // Create Actor System
    val _system = ActorSystem("RestActorSystem")
    //Set the Guice Injector in Akka Actor Extension for current actor system.
    GuiceExtensionProvider.get(_system).initialize(injector)
    //Create Master Actor via Guice so that all the actors are initialized by their supervisors.
    _system.actorOf(GuiceExtensionProvider.get(_system).props(classOf[MasterActor]), classOf[MasterActor].getSimpleName);
  }

}

class DevModule(injector: Injector) extends AppModule(injector) {

  override def configure {
    super.configure
  }

}
