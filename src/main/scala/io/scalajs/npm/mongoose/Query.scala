package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Mongoose Query
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("mongoose", "Query")
class Query[T]() extends js.Object {

  def exec(): MongooseThenable[T] = js.native

}
