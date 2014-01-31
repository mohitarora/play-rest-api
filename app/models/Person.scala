package models

import play.api.libs.json.Json

case class Person(name: String, age: Int, lovesExercise: Boolean)

object Person {
  implicit val format = Json.format[Person]
}
