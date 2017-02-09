package io.scalajs.npm.mongoose

import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.console
import io.scalajs.npm.mongoose.MongooseTest.M
import org.scalatest.FunSpec

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Mongoose Tests
  * @author lawrence.daniels@gmail.com
  */
class MongooseTest extends FunSpec {

  describe("Mongoose") {

    it("should support schema declarations") {
      val blogSchema = new Schema(
        js.Dictionary(
          "title" -> "String",
          "author" -> "String",
          "body" -> "String",
          "comments" -> js.Array(js.Dictionary("body" -> "String", "date" -> "Date")),
          "date" -> js.Dictionary("type" -> "Date", "default" -> js.Date.now),
          "hidden" -> "Boolean",
          "meta" -> js.Dictionary(
            "votes" -> "Number",
            "favs" -> "Number"
          ))
      )

      val blog = Mongoose.model("Blog", blogSchema)
      console.log("blog => ", blog)
    }

    it("should ???") {
      // values are cast:
      val schema = new Schema(js.Dictionary("aNumber" -> field(`type` = Schema.Types.Number, default = 4.815162342)))
      val M = db.model("M", schema)
      val m = new M
      console.log(m.aNumber) // 4.815162342

      // default unique objects for Mixed types:
      val schema2 = new Schema(js.Dictionary("mixed" -> Schema.Types.Mixed))
      schema2.path("mixed").default(() => new js.Object())

      // if we don't use a function to return object literals for Mixed defaults,
      // each document will receive a reference to the same object literal creating
      // a "shared" object instance:
      val schema3 = new Schema(js.Dictionary("mixed" -> Schema.Types.Mixed))
      schema3.path("mixed").default(new js.Object())
      val M = db.model("M", schema)
      val m1 = new M
      m1.mixed.added = 1
      console.log(m1.mixed)
      // { added: 1 }
      val m2 = new M
      console.log(m2.mixed) // { added: 1 }
    }

    it("should support middleware functions") {
      val Comment = new Schema(js.Dictionary(
        "name" -> new SchemaField(`type` = "String", default = "hahaha"),
        "age" -> new SchemaField(`type` = "Number", min = 18, c = true),
        "bio" -> new SchemaField(`type` = "String", `match` = js.RegExp("/[a-z]/")),
        "date" -> new SchemaField(`type` = "Date", default = js.Date.now),
        "buff" -> Buffer
      ))

      // a setter 
      Comment.path("name").set[String](_.toUpperCase)

      // middleware
      Comment.pre("save", { next =>
        notify(Comment.get("email"))
        next()
      })
    }

  }

}

/**
  * Mongoose Test Companion
  * @author lawrence.daniels@gmail.com
  */
object MongooseTest {

  @ScalaJSDefined
  class M(val mixed: js.Object) extends js.Object


  @ScalaJSDefined
  class Comment() extends js.Object

  @ScalaJSDefined
  class BlogSchema extends js.Object

}