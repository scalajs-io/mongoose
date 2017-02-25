package io.scalajs.npm.mongoose

import io.scalajs.RawOptions
import io.scalajs.nodejs.events.IEventEmitter

import scala.scalajs.js

/**
  * Mongoose Connection
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait Connection extends IEventEmitter {

  /**
    * Gets mongoose options
    * @param key the given key
    * @return the associated value
    */
  def get(key: String): js.Any = js.native

  /**
    * Models defined on the mongoose instance are available to all connection created by the same mongoose instance.
    * @param name       the model name or class extending Model
    * @param schema     the schema
    * @param collection the name (optional, inferred from model name)
    * @param skipInit   indicates whether to skip initialization (defaults to false)
    * @return the model
    */
  def model[A](name: String,
               schema: Schema = js.native,
               collection: String = js.native,
               skipInit: Boolean = js.native): Model[A] = js.native

  /**
    * Returns an array of model names created on this instance of Mongoose.
    * @return an array of model names
    */
  def modelNames(): js.Array[String] = js.native

  /**
    *
    * @param host
    * @param database
    * @param port
    * @param options
    */
  def open(host: String, database: String, port: Int, options: RawOptions = js.native): Unit = js.native

  /**
    * Declares a global plugin executed on all Schemas.
    * @param callback the  plugin callback
    * @param options  the optional options
    */
  def plugin(callback: js.Function, options: RawOptions = js.native): Unit = js.native

  /**
    * Sets mongoose options
    * @param key   the options key
    * @param value the the value to set or function
    */
  def set(key: String, value: js.Any): Unit = js.native

}
