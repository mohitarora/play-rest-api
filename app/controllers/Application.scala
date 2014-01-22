package controllers

import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.duration._
import com.google.inject.Singleton
import models.Person
import actors.{GetCount, Count, MasterActor, ActorRegistry}

@Singleton
class Application extends Controller {

  implicit val timeout = Timeout(5 seconds) //enabled by duration._ import

  private lazy val masterActor = Akka.system.actorSelection(ActorRegistry(classOf[MasterActor]))

  def index = Action.async {
    masterActor ! Count
    // Future Returned by actor is Future[Any] because actor is dynamic, mapTo method will return a new casted Future
    // if cast is successful or a ClassCastException if not.
    val future = (masterActor ? GetCount).mapTo[Int]
    // map method on casted future will return Future[Result] Future[Result] will eventually be redeemed with a value
    // of type Result. By giving a Future[Result] instead of normal Result we are able to quickly generate the result
    // without blocking. Play will serve the result as soon as promise is redeemed. Read about Promises if further
    // information is required.
    future.map {
      response => Ok(Json.toJson(new Person("Mohit", response, true)))
    }
  }

}