package io.scalajs.npm.mongoose

import org.scalatest.FunSpec

/**
  * Mongoose Tests
  * @author lawrence.daniels@gmail.com
  */
class MongooseTest extends FunSpec {

  describe("Mongoose") {

    it("should ???") {
      /*
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
      */
    }

    it("should support middleware functions") {
      /*
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
      })*/
    }

  }

}
