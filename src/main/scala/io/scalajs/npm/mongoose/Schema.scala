package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Everything in Mongoose starts with a Schema. Each schema maps to a MongoDB collection and defines
  * the shape of the documents within that collection.
  * @version 4.7.6
  * @see http://mongoosejs.com/
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("mongoose", "Schema")
class Schema(structure: js.Any) extends js.Object {

  def methods: Methods = js.native

}

/**
  * Schema Singleton
  * @author lawrence.daniels@gmail.com
  */
object Schema {

  def apply(fields: (String, js.Any)*): Schema = {
    new Schema(js.Dictionary(fields: _*))
  }

}