package io.scalajs.npm

import io.scalajs.util.ScalaJsHelper._

import scala.scalajs.js

/**
  * mongoose package object
  * @author lawrence.daniels@gmail.com
  */
package object mongoose {

  implicit class SchemaEnrichment(val schema: Schema) extends AnyVal {

    @inline
    def path(label: String): SchemaProperty = schema.dynamic.path(label).asInstanceOf[SchemaProperty]

    @inline
    def pre[A](label: String, value: js.Function1[js.Function0[Any], Any]): SchemaProperty = schema.dynamic.pre(label).asInstanceOf[SchemaProperty]

  }

}
