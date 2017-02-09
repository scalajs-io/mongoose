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
