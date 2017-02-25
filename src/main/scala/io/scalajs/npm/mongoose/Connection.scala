package io.scalajs.npm.mongoose

import scala.scalajs.js

/**
  * Mongoose Connection
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait Connection extends js.Object {

  def model(name: String, schema: Schema = js.native): Model = js.native

}
