import actors.MasterActor
import com.google.inject.Guice
import guice.{GuiceExtensionProvider, DevModule, AppModule}
import play.api.libs.concurrent.Akka
import play.api.{Application, Play, GlobalSettings}
import play.api.Play.current

object Global extends GlobalSettings {

  private lazy val _injector = {
    Play.isProd match {
      case true => Guice.createInjector(new AppModule)
      case false => Guice.createInjector(new DevModule)
    }
  }

  override def onStart(app: Application): Unit = {
    val _system = Akka.system
    GuiceExtensionProvider.get(_system).initialize(_injector)
    _system.actorOf(GuiceExtensionProvider.get(_system).props(classOf[MasterActor]), classOf[MasterActor].getSimpleName);
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    _injector.getInstance(controllerClass)
  }

}
