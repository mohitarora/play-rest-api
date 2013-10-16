import actors.MasterActor
import akka.actor.ActorSystem
import com.google.inject.Guice
import guice.{GuiceExtensionProvider, DevModule, AppModule}
import play.api.{Application, Play, GlobalSettings}
import play.api.Play.current

object Global extends GlobalSettings {

  /**
   * Create Guice Injector
   */
  private lazy val _injector = Play.isProd match {
    case true => Guice.createInjector(new AppModule)
    case false => Guice.createInjector(new DevModule)
  }

  /**
   * Initialize Actor System, Actors are initialized via Guice.
   */
  override def onStart(app: Application): Unit = {
    val _system = _injector.getInstance(classOf[ActorSystem])
    // Set Guice Injector to Guice Actor Extension
    GuiceExtensionProvider(_system).initialize(_injector)
    // Create Master Actor, This is a chain reaction because supervisors are responsible for
    // creating actors that they are supervising
    _system.actorOf(GuiceExtensionProvider(_system).props(classOf[MasterActor]), classOf[MasterActor].getSimpleName)
  }

  /**
   * This method will return controller from Guice.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = _injector.getInstance(controllerClass)


}
