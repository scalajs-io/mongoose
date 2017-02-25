package io.scalajs.npm.mongoose

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Schema Field Definition
  * @param `type`
  * @param default
  * @param c
  * @param `match`
  * @param min
  * @param index
  */
@ScalaJSDefined
class SchemaField(val `type`: SchemaFieldType,
                  val default: js.Any = js.undefined,
                  val c: js.UndefOr[Boolean] = js.undefined,
                  val `match`: js.UndefOr[js.RegExp] = js.undefined,
                  val min: js.UndefOr[Int] = js.undefined,
                  val index: js.UndefOr[Boolean] = js.undefined) extends js.Object

/**
  * Schema Field Singleton
  * @author lawrence.daniels@gmail.com
  */
object SchemaField {

  def apply(`type`: SchemaFieldType,
            default: js.Any = js.undefined,
            c: js.UndefOr[Boolean] = js.undefined,
            `match`: js.UndefOr[js.RegExp] = js.undefined,
            min: js.UndefOr[Int] = js.undefined,
            index: js.UndefOr[Boolean] = js.undefined): SchemaField = {
    new SchemaField(
      `type` = `type`,
      default = default,
      c = c,
      `match` = `match`,
      min = min,
      index = index
    )
  }

}

/**
  * Represents a Schema Field Type
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait SchemaFieldType extends js.Object

