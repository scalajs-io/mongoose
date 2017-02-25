package io.scalajs.npm

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.|

/**
  * mongoose package object
  * @author lawrence.daniels@gmail.com
  */
package object mongoose {

  ///////////////////////////////////////////////////////////////////
  //    Type Definitions
  ///////////////////////////////////////////////////////////////////

  /**
    * Schema Field Types
    */
  @js.native
  trait SchemaFieldType[+T] extends js.Object

  type ArrayFieldType = SchemaFieldType[js.Array[_]]
  type BooleanFieldType = SchemaFieldType[Boolean]
  type BufferFieldType = SchemaFieldType[Buffer]
  type DateFieldType = SchemaFieldType[js.Date | Double]
  type MixedFieldType = SchemaFieldType[js.Any]
  type NumberFieldType = SchemaFieldType[Double | Int]
  type ObjectIdFieldType = SchemaFieldType[ObjectId]
  type StringFieldType = SchemaFieldType[String]

}
