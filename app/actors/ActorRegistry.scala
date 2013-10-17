package actors

import akka.actor.{ActorPath, Actor}
import scala.Predef._

/**
 * This object will be used to map actor class to actor path in system so that it can be retrieved easily based on
 * actor class. ActorRegistry can be used in akka actors or play controllers to locate akka actors.
 */
object ActorRegistry {

  //Mutable map to add actor class mapping to actor path
  val registry = scala.collection.mutable.Map[Class[_ <: Actor], ActorPath]()

  def apply(actorClass: Class[_ <: Actor]): ActorPath = registry(actorClass)

}
