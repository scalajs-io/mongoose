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
@js.native
@JSImport("mongoose", "Schema")
object Schema extends js.Object {

  @js.native
  @JSImport("mongoose", "Schema.Types")
  object Types extends js.Object {
    type String = java.lang.String
    type Number = scala.Double
    type Date = js.Date
    type Buffer = io.scalajs.nodejs.buffer.Buffer
    type Boolean = scala.Boolean
    type Mixed = js.Any
    type ObjectId = io.scalajs.npm.mongodb.ObjectID
    type Array[A] = js.Array[A]
  }

}
