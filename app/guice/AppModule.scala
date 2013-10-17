package guice

import com.tzavellas.sse.guice.ScalaModule

/**
 * Guice Application Module
 */
class AppModule extends ScalaModule {

  override def configure {

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
