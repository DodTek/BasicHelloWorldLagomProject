package com.gv.helloexperiment.impl

import com.gv.helloexperiment.api.{HelloExperimentService, InputList, InputListInt, Lists, Person}
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
    Future(s"My name is " + p.name + "I am " + p.age + " I live at " + p.address)
  }

  override def getList: ServiceCall[InputListInt, String] = {p =>
    Future(Lists.randomSelectElement(4,p.list).toString())
  }
  var PersonList: List[Person] = List(Person("BOB",21,"ads"))
  override def addToList: ServiceCall[Person, String] = {p =>
    PersonList ::: List(p)
    Future("Added Person to List")
  }

  override def returnList: ServiceCall[NotUsed, String] = ???
}

