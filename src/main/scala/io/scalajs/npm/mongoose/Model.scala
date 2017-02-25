package io.scalajs.npm.mongoose

import io.scalajs.nodejs.SystemError
import io.scalajs.util.ScalaJsHelper._

import scala.scalajs.js

/**
  * Mongoose Model
  * @author lawrence.daniels@gmail.com
  */
@js.native
class Model[T](val doc: js.Any, val fields: js.Array[String], val skipId: js.Any) extends js.Object {

  def findOne(query: js.Any): Query[T] = js.native

  def findOne(query: js.Any, callback: js.Function2[SystemError, T, Any]): Unit = js.native

  def findById(id: ObjectId): Query[T] = js.native

  def findById(id: ObjectId, callback: js.Function2[SystemError, T, Any]): Unit = js.native

  def find(query: js.Any): Query[js.Array[T]] = js.native

  def find(query: js.Any, callback: js.Function2[SystemError, js.Array[T], Any]): Unit = js.native

}

/**
  * Model Singleton
  * @author lawrence.daniels@gmail.com
  */
object Model {
  
  /**
    * Model CRUD Mix-in
    */
  @js.native
  trait CRUD[T] extends js.Object {

    def remove(): js.Promise[Unit] = js.native

    def remove(callback: js.Function1[SystemError, Any]): Unit = js.native

    def save(): js.Promise[Unit] = js.native

    def save(callback: js.Function1[SystemError, Any]): Unit = js.native

    def update(): js.Promise[Unit] = js.native

    def update(callback: js.Function1[SystemError, Any]): Unit = js.native

  }

  ///////////////////////////////////////////////////////////////////
  //    Implicit Classes
  ///////////////////////////////////////////////////////////////////

  /**
    * Model Enrichment
    * @param model the given [[Model]]
    */
  implicit class ModelEnrichment[A](val model: Model[A]) extends AnyVal {

    @inline
    def apply(): A with CRUD[A] = create()

    @inline
    def create(): A with CRUD[A] = model.New[A with CRUD[A]]()

  }

}