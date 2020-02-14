package com.gv.helloexperiment.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import com.gv.helloexperiment.api._

class HelloExperimentServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(
    ServiceTest.defaultSetup
      .withCassandra()
  ) { ctx =>
    new HelloExperimentApplication(ctx) with LocalServiceLocator
  }

  val client: HelloExperimentService = server.serviceClient.implement[HelloExperimentService]

  override protected def afterAll(): Unit = server.stop()

  "Hello Experiment service" should {

    "say hello" in {
      client.hello("1").invoke().map { answer =>
        answer should ===("Hello Alice")
      }
    }
  }
}
