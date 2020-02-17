package com.gv.helloexperiment.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

object HelloExperimentService  {
  val TOPIC_NAME = "greetings"
}

/**
 * Creates class to use read in lists
 */

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
  def hello(value: String): ServiceCall[NotUsed, String]

  /**
   * Example: curl http://localhost:9000/api/isPalindrome/1234321
   */
  def endpointIsPalindrome(value: String): ServiceCall[NotUsed, Boolean]

  /**
   * Example: curl http://localhost:9000/api/getPerson
   *
   * @return String
   */
  def getPerson: ServiceCall[Person, String]

  def getList: ServiceCall[InputListInt, String]

  def addToList: ServiceCall[Person, String]

  def returnList: ServiceCall[NotUsed, String]
  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("hello-experiment")
      .withCalls(
        restCall(Method.GET,"/api/hello:id", hello _),
        restCall(Method.GET,"/api/isPalindrome/:id", endpointIsPalindrome _),
        restCall(Method.POST,"/api/getPerson/", getPerson _),
        restCall(Method.POST,"/api/getList/", getList _),
        restCall(Method.POST,"/api/add/", addToList _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}
