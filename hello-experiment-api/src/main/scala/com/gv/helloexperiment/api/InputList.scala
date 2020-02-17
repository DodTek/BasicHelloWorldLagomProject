package com.gv.helloexperiment.api

import play.api.libs.json.{Format, Json}

case class InputListString(list: List[String])
object InputListString {
  implicit val format: Format[InputListString] = Json.format[InputListString]
}

case class InputListInt(list: List[Int])
object InputListInt {
  implicit val format: Format[InputListInt] = Json.format[InputListInt]
}

case class InputListDouble(list: List[Double])
object InputListDouble {
  implicit val format: Format[InputListDouble] = Json.format[InputListDouble]
}

case class InputList(str: InputListString,int: InputListInt, double: InputListDouble)

object InputList {
  val list = List(InputListString,InputListInt,InputListDouble)
  implicit val format: Format[InputList] = Json.format[InputList]
}
/*
case class OutputList(list: List[Any])
object OutputList {
  implicit val format: Format[OutputList] = Json.format[OutputList]
}
 */

