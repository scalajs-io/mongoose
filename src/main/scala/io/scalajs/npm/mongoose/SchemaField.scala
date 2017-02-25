package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Schema Field Definition
  * @param `type`  the [[SchemaFieldType field type]]
  * @param default an optional default value
  * @param c       an optional ???
  * @param `match` an optional regular expression constraint
  * @param min     an optional minimum value
  * @param max     an optional maximum value
  * @param index   an optional index indicator
  */
@ScalaJSDefined
class SchemaField[A](val `type`: SchemaFieldType[A],
                     val default: js.UndefOr[A] = js.undefined,
                     val c: js.UndefOr[Boolean] = js.undefined,
                     val `match`: js.UndefOr[js.RegExp] = js.undefined,
                     val min: js.UndefOr[A] = js.undefined,
                     val max: js.UndefOr[A] = js.undefined,
                     val index: js.UndefOr[Boolean] = js.undefined) extends js.Object

/**
  * Schema Field Singleton
  * @author lawrence.daniels@gmail.com
  */
object SchemaField {

  /**
    * Creates a new schema field definition
    * @param `type`  the [[SchemaFieldType field type]]
    * @param default an optional default value
    * @param c       an optional ???
    * @param `match` an optional regular expression constraint
    * @param min     an optional minimum value
    * @param max     an optional maximum value
    * @param index   an optional index indicator
    */
  def apply[A](`type`: SchemaFieldType[A],
               default: js.UndefOr[A] = js.undefined,
               c: js.UndefOr[Boolean] = js.undefined,
               `match`: js.UndefOr[js.RegExp] = js.undefined,
               min: js.UndefOr[A] = js.undefined,
               max: js.UndefOr[A] = js.undefined,
               index: js.UndefOr[Boolean] = js.undefined): SchemaField[A] = {
    new SchemaField[A](
      `type` = `type`,
      default = default,
      c = c,
      `match` = `match`,
      min = min,
      max = max,
      index = index
    )
  }

}

/**
  * Represents a schema field type
  */
@js.native
trait SchemaFieldType[+T] extends js.Object