package io.scalajs.npm.mongoose

import io.scalajs.JSON
import io.scalajs.npm.mongoose.MongooseTest.Comment
import org.scalatest.FunSpec

import scala.scalajs.js

/**
  * Mongoose Tests
  * @author lawrence.daniels@gmail.com
  */
class MongooseTest extends FunSpec {

  describe("Mongoose") {

    it("should support middleware functions") {
      import Mongoose.Schema.Types._

      info("Define the schema")
      val CommentSchema = Schema(
        "name" -> SchemaField(`type` = String, default = "John Doe"),
        "age" -> SchemaField(`type` = Number, min = 18, c = true),
        "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
        "date" -> SchemaField(`type` = Date, default = js.Date.now),
        "buff" -> Buffer
      )

      info("Define a mutator")
      CommentSchema.path("name").set[String](_.toUpperCase)

      info("Define a middleware function")
      CommentSchema.pre("save", { (next, _, _, _) =>
        info(CommentSchema.get("name").toString)
        next()
      })

      info("Register the model")
      val CommentModel = Mongoose.model("Comment", CommentSchema)

      info("Create an instance of the model")
      val comment = CommentModel.create[Comment]()
      comment.age = 21
      comment.bio = "Lover of life"
      comment.date = js.Date.now()

      info(s"instance = ${JSON.stringify(comment)}")

      info("Persist the data object")
      comment.save { err =>
        alert(JSON.stringify(err))
      }
    }

  }

}

/**
  * Mongoose Test Companion
  * @author lawrence.daniels@gmail.com
  */
object MongooseTest {

  import io.scalajs.nodejs.buffer.Buffer

  @js.native
  trait Comment extends js.Object {
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[Buffer] = js.native
  }

}