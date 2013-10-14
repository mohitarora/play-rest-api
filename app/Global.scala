import com.google.inject.Guice
import guice.{AppModule, DevModule}
import play.api.{Play, GlobalSettings}
import play.api.Play.current

object Global extends GlobalSettings {

  private lazy val injector = {
    Play.isProd match {
      case true => Guice.createInjector(new AppModule)
      case false => Guice.createInjector(new DevModule)
    }
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    injector.getInstance(controllerClass)
  }
}
