package controllers

import play.api.mvc._

object Application extends Controller {

  def index(name: String) = Action {
    Ok("Hello " + name)
  }

}