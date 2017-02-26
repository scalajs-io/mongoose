package io.scalajs.npm.mongoose

import io.scalajs.nodejs.SystemError
import io.scalajs.npm.mongodb.UpdateWriteResult
import io.scalajs.util.ScalaJsHelper._

import scala.scalajs.js

/**
  * Mongoose Model
  * @author lawrence.daniels@gmail.com
  */
@js.native
class MongooseModel[T](val doc: js.Any, val fields: js.Array[String], val skipId: js.Any) extends js.Object {

  def findOne(query: js.Any): Query[T] = js.native

  def findOne(query: js.Any, callback: js.Function2[SystemError, T, Any]): Unit = js.native

  def findById(id: ObjectId): Query[T] = js.native

  def findById(id: ObjectId, callback: js.Function2[SystemError, T, Any]): Unit = js.native

  def find(query: js.Any): Query[js.Array[T]] = js.native

  def find(query: js.Any, callback: js.Function2[SystemError, js.Array[T], Any]): Unit = js.native

  def remove(query: js.Any): js.Promise[UpdateWriteResult] = js.native

  def remove(query: js.Any, callback: js.Function2[SystemError, UpdateWriteResult, Any]): Unit = js.native

}

/**
  * Mongoose Model Singleton
  * @author lawrence.daniels@gmail.com
  */
object MongooseModel {

  /**
    * Model CRUD Mix-in
    */
  @js.native
  trait CRUD[T] extends js.Object {

    def remove(): js.Promise[Model[T]] = js.native

    def remove(callback: js.Function2[SystemError, Model[T], Any]): Unit = js.native

    def save(): js.Promise[Model[T]] = js.native

    def save(callback: js.Function2[SystemError, Model[T], Any]): Unit = js.native

    def update(): js.Promise[UpdateWriteResult] = js.native

    def update(callback: js.Function2[SystemError, Model[T], Any]): Unit = js.native

  }

  ///////////////////////////////////////////////////////////////////
  //    Implicit Classes
  ///////////////////////////////////////////////////////////////////

  /**
    * Mongoose Model Enrichment
    * @param model the given [[MongooseModel]]
    */
  implicit class MongooseModelEnrichment[A](val model: MongooseModel[A]) extends AnyVal {

    @inline
    def apply(): Model[A] = model.New[Model[A]]()

  }

}