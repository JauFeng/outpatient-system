package models

import play.api.libs.json.{Json, Format}

/**
  * Created by crow1 on 2016/11/21.
  */
case class Test(id: Long, name: String)

object Test {
  val testFormat: Format[Test] = Json.format[Test]
}