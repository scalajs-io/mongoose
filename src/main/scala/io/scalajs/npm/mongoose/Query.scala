package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

/**
  * Mongoose Query
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("mongoose", "Query")
class Query[T]() extends QueryExecutable[T] {

  def where(field: String, value: String | js.RegExp = js.native): QueryOperator[T] = js.native

}

/**
  * Mongoose Query Operator
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait QueryOperator[T] extends QueryExecutable[T] {

  def eq(value: js.Any): this.type = js.native

  def gt(value: js.Any): this.type = js.native

  def gte(value: js.Any): this.type = js.native

  def lt(value: js.Any): this.type = js.native

  def lte(value: js.Any): this.type = js.native

  def ne(value: js.Any): this.type = js.native

}

/**
  * Mongoose Query Executor
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait QueryExecutable[T] extends js.Object {

  def exec(): js.Promise[T] = js.native

  def exec(callback: js.Function2[MongooseError, T, Any]): Unit = js.native

}