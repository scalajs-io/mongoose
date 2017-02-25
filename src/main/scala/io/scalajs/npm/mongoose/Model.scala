package io.scalajs.npm.mongoose

import io.scalajs.nodejs.SystemError
import io.scalajs.util.ScalaJsHelper._

import scala.scalajs.js

/**
  * Mongoose Model
  * @author lawrence.daniels@gmail.com
  */
@js.native
class Model(val doc: js.Any, val fields: js.Array[String], val skipId: js.Any) extends js.Object {

}

/**
  * Mongoose Model
  * @author lawrence.daniels@gmail.com
  */
object Model {

  /**
    * Model CRUD Mix-in
    */
  @js.native
  trait ModelCRUD[T] extends js.Object {

    def findOne(query: js.Any, callback: js.Function2[SystemError, T, Any]): Unit = js.native

    def findById(id: ObjectId, callback: js.Function2[SystemError, T, Any]): Unit = js.native

    def find(query: js.Any, callback: js.Function2[SystemError, js.Array[T], Any]): Unit = js.native

    def remove(): Unit = js.native

    def remove(callback: js.Function1[SystemError, Any]): Unit = js.native

    def save(): js.Promise[js.Any] = js.native

    def save(callback: js.Function1[SystemError, Any]): Unit = js.native

    def update(callback: js.Function1[SystemError, Any]): Unit = js.native

  }

  /**
    * Model Enrichment
    * @param model the given [[Model]]
    */
  implicit class ModelEnrichment(val model: Model) extends AnyVal {

    @inline
    def create[T <: js.Any](): T with ModelCRUD[T] = model.New[T with ModelCRUD[T]]()

  }

}