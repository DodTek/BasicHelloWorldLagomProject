package com.gv.helloexperiment.impl

import com.gv.helloexperiment.api.{HelloExperimentService, Lists, Person}
import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.ClusterSharding
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import akka.util.Timeout
import play.api.libs.json._
/**
  * Implementation of the HelloExperimentService.
*/
class HelloExperimentServiceImpl(clusterSharding: ClusterSharding, persistentEntityRegistry: PersistentEntityRegistry)
  (implicit ec: ExecutionContext) extends HelloExperimentService {

  implicit val timeout = Timeout(5.seconds)

  override def endpointIsPalindrome(value: String): ServiceCall[NotUsed, Boolean] = ServiceCall {
    _ =>
      val parList = List(value).flatMap(_.toUpperCase())
      Future(Lists.isPalindrome(parList))
  }

  override def hello(value: String): ServiceCall[NotUsed, String] = ServiceCall {
    _ =>
      if(value.length > 3) Future("Hello " + value)
      else Future("Hello World")
  }
  override def getPerson : ServiceCall[Person, String] = { p =>
    Future(p.name)
  }
}

case class parListClass(list: List[Any])

