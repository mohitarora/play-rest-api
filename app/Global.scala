import com.google.inject.Guice
import guice.{DevModule, AppModule}
import play.api.{Play, GlobalSettings}
import play.api.Play.current

object Global extends GlobalSettings {

  private lazy val _injector = {
    Play.isProd match {
      case true => Guice.createInjector(new AppModule(_injector))
      case false => Guice.createInjector(new DevModule(_injector))
    }
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    _injector.getInstance(controllerClass)
  }

}
