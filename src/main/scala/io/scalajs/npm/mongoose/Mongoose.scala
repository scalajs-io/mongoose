package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Mongoose provides a straight-forward, schema-based solution to model your application data. It includes
  * built-in type casting, validation, query building, business logic hooks and more, out of the box.
  * @version 4.7.6
  * @see http://mongoosejs.com/
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("mongoose", JSImport.Namespace)
object Mongoose extends js.Object {

  def connect(url: String): js.Object = js.native

  def model(name: String, schema: Schema): Model = js.native

}