package io.scalajs.npm.mongoose

import io.scalajs.RawOptions
import io.scalajs.nodejs.SystemError
import io.scalajs.npm.mongodb.{ConnectionOptions, Db}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

/**
  * Mongoose provides a straight-forward, schema-based solution to model your application data. It includes
  * built-in type casting, validation, query building, business logic hooks and more, out of the box.
  * @see http://mongoosejs.com/
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait Mongoose extends js.Object {

  def createConnection(url: String, options: ConnectionOptions | RawOptions = js.native): Db = js.native

  /**
    * Opens the default mongoose connection.
    * @param url      the connecion URL
    * @param options  the connection options
    * @param callback the callback
    */
  def connect(url: String,
              options: ConnectionOptions | RawOptions = js.native,
              callback: js.Function2[SystemError, Connection, Any] = js.native): js.Promise[Connection] = js.native

  /**
    * Disconnects all connections.
    * @param callback the call back
    */
  def disconnect(callback: js.Function = js.native): js.Promise[js.Any] = js.native

  def model(name: String, schema: Schema = js.native): Model = js.native

}

/**
  * Mongoose Singleton
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("mongoose", JSImport.Namespace)
object Mongoose extends Mongoose {

  @js.native
  object Schema extends js.Object {

    @js.native
    object Types extends js.Object {

      def Array: ArrayFieldType = js.native

      def Boolean: BooleanFieldType = js.native

      def Buffer: BufferFieldType = js.native

      def Date: DateFieldType = js.native

      def Mixed: MixedFieldType = js.native

      def Number: NumberFieldType = js.native

      def ObjectId: ObjectIdFieldType = js.native

      def String: StringFieldType = js.native

    }

  }

}
