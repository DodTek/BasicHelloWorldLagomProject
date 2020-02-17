package com.gv.helloexperiment.api

import play.api.libs.json.{Format, Json}

case class Person(name: String, age: Int, address: String)
object Person {
  implicit val format: Format[Person] = Json.format[Person]
}
