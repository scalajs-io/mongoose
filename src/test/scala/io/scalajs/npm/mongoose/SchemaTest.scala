package io.scalajs.npm.mongoose

import org.scalatest.FunSpec

import scala.scalajs.js
import scala.scalajs.js.RegExp

/**
  * Schema Test
  * @see http://mongoosejs.com/docs/schematypes.html
  * @author lawrence.daniels@gmail.com
  */
class SchemaTest extends FunSpec {

  describe("Schema") {

    it("should support schema declarations") {
      import Mongoose.Schema.Types._

      val blogSchema = Schema(
        "title" -> String,
        "author" -> String,
        "body" -> String,
        "comments" -> js.Array(js.Dictionary("body" -> String, "date" -> Date)),
        "date" -> SchemaField(`type` = Date, default = js.Date.now),
        "hidden" -> Boolean,
        "meta" -> js.Dictionary(
          "votes" -> Number,
          "favs" -> Number
        ))

      val blog = Mongoose.model("Blog", blogSchema)
      info(s"blog => $blog")
    }


    it("should support complex schema types") {
      import Mongoose.Schema.Types._

      val commentSchema = Schema(
        "name" -> SchemaField(`type` = String, default = "hahaha"),
        "age" -> SchemaField(`type` = Number, min = 18, index = true),
        "bio" -> SchemaField(`type` = String, `match` = RegExp("[a-z]")),
        "date" -> SchemaField(`type` = Date, default = js.Date.now()),
        "buff" -> Buffer
      )

      val comment = Mongoose.model("Comment", commentSchema)
      info(s"comment => $comment")
    }

  }

}
