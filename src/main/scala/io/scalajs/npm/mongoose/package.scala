package io.scalajs.npm

import io.scalajs.RawOptions
import io.scalajs.nodejs.SystemError
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.npm.mongodb.ConnectionOptions
import io.scalajs.npm.mongoose.MongooseModel.CRUD
import io.scalajs.util.PromiseHelper._

import scala.concurrent.Promise
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

  type Model[A] = A with CRUD[A]

  type ArrayFieldType = SchemaFieldType[js.Array[_]]
  type BooleanFieldType = SchemaFieldType[Boolean]
  type BufferFieldType = SchemaFieldType[Buffer]
  type DateFieldType = SchemaFieldType[js.Date | Double]
  type MixedFieldType = SchemaFieldType[js.Any]
  type NumberFieldType = SchemaFieldType[Double | Int]
  type ObjectIdFieldType = SchemaFieldType[ObjectId]
  type StringFieldType = SchemaFieldType[String]

  ///////////////////////////////////////////////////////////////////
  //    Implicit Definitions
  ///////////////////////////////////////////////////////////////////

  /**
    * Mongoose Enrichment
    * @param connectable the given [[Connectable]]
    */
  implicit class MongooseEnrichment(val connectable: Connectable) extends AnyVal {

    @inline
    def connectAsync(url: String, options: ConnectionOptions | RawOptions = null): Promise[Connection] = {
      promiseWithError1[SystemError, Connection](connectable.connect(url, options, _))
    }

  }

}
