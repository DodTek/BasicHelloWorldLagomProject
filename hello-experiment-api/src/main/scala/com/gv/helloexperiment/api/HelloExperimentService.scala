package com.gv.helloexperiment.api

import akka.{Done, NotUsed}
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.deser.MessageSerializer
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object HelloExperimentService  {
  val TOPIC_NAME = "greetings"
}

/**
  * The Hello Experiment service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HelloExperimentService.
  */
trait HelloExperimentService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello/Alice
    */
  private val serializer = new StringSerializer
  def hello(id: Int): ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("hello-experiment")
      .withCalls(
        pathCall("/api/hello/:id", hello _),
        //pathCall("/api/hello/:id", useGreeting _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}
