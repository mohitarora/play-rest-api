package controllers

import play.api.mvc._
import models.Person
import play.api.libs.json.Json
import com.google.inject.{Inject, Singleton}
import akka.actor.ActorSystem
import actors.{MasterActor, ActorRegistry}

@Singleton
class Application @Inject()(actorSystem: ActorSystem) extends Controller {

  private val masterActor = actorSystem.actorSelection(ActorRegistry(classOf[MasterActor]))

  def index() = Action {
    Ok(Json.toJson(new Person(masterActor.toString(), 21, true)))
  }

}