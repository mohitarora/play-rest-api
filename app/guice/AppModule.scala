package guice

import com.tzavellas.sse.guice.ScalaModule
import akka.actor.ActorSystem
import play.api.libs.concurrent.Akka
import play.api.Play.current

/**
 * Guice Application Module
 */
class AppModule extends ScalaModule {

  override def configure {
    bind(classOf[ActorSystem]).toInstance(Akka.system)
  }

}

/**
 * Guice Application Module in Development mode
 */
class DevModule extends AppModule {

  override def configure {
    super.configure
  }

}
