package controllers

import play.api.mvc._
import models.Person
import play.api.libs.json.Json
import com.google.inject.{Inject, Singleton}

@Singleton
class Application @Inject() extends Controller {

  def index() = Action {
    Ok(Json.toJson(new Person("XYZ", 21, true)))
  }

}