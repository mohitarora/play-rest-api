package guice

import com.tzavellas.sse.guice.ScalaModule

class AppModule extends ScalaModule {

  def configure {

  }

}

class DevModule extends AppModule {

  override def configure {
    super.configure
  }

}
