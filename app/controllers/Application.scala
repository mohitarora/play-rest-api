package controllers

import play.api.mvc._
import models.Person
import play.api.libs.json.Json

object Application extends Controller {

  def index() = Action {
    Ok(Json.toJson(new Person("Mohit", 31, true)))
  }

}