package io.scalajs.npm

import io.scalajs.util.ScalaJsHelper._

import scala.scalajs.js

/**
  * mongoose package object
  * @author lawrence.daniels@gmail.com
  */
package object mongoose {

  def field(`type`: SchemaFieldType,
            default: js.Any = js.undefined,
            c: js.UndefOr[Boolean] = js.undefined,
            `match`: js.UndefOr[js.RegExp] = js.undefined,
            min: js.UndefOr[Int] = js.undefined,
            index: js.UndefOr[Boolean] = js.undefined): SchemaField = {
    new SchemaField(`type` = `type`, default = default, c = c, `match` = `match`, min = min, index = index)
  }

  def model[T](name: String, schema: Schema): T = null

  implicit class SchemaEnrichment(val schema: Schema) extends AnyVal {

    @inline
    def path(label: String): SchemaProperty = schema.dynamic.path(label).asInstanceOf[SchemaProperty]

    @inline
    def pre[A](label: String, value: js.Function1[js.Function0[Any], Any]): SchemaProperty = schema.dynamic.pre(label).asInstanceOf[SchemaProperty]

  }

}
