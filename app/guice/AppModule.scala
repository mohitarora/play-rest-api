package guice

import com.tzavellas.sse.guice.ScalaModule
import com.google.inject.{Provides, Singleton}
import akka.actor.ActorSystem
import play.api.libs.concurrent.Akka
import play.api.Play.current

/**
 * Guice Application Module
 */
class AppModule extends ScalaModule {

  override def configure {

  }

  @Provides
  @Singleton
  def actorSystem: ActorSystem = Akka.system

}

/**
 * Guice Application Module in Development mode
 */
class DevModule extends AppModule {

  override def configure {
    super.configure
  }

}
