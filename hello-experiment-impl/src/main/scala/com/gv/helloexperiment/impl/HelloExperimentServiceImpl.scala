package com.gv.helloexperiment.impl

import com.gv.helloexperiment.api.{HelloExperimentService, Lists}
import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.ClusterSharding
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import akka.util.Timeout

/**
  * Implementation of the HelloExperimentService.
*/
class HelloExperimentServiceImpl(clusterSharding: ClusterSharding, persistentEntityRegistry: PersistentEntityRegistry)
  (implicit ec: ExecutionContext) extends HelloExperimentService {

  implicit val timeout = Timeout(5.seconds)

  override def hello(id: Int): ServiceCall[NotUsed, String] = ServiceCall {
    _ =>
      if (id == 1) Future("Hello Alice")
      else Future("Hello world")
  }
}
