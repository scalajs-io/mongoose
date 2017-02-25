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
trait Mongoose extends js.Object {

  def connect(url: String): js.Object = js.native

  def model(name: String, schema: Schema): Model = js.native

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

      def Array: SchemaFieldType = js.native

      def Boolean: SchemaFieldType = js.native

      def Buffer: SchemaFieldType = js.native

      def Date: SchemaFieldType = js.native

      def Mixed: SchemaFieldType = js.native

      def Number: SchemaFieldType = js.native

      def ObjectId: SchemaFieldType = js.native

      def String: SchemaFieldType = js.native

    }

  }

}