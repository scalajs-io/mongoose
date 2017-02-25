package io.scalajs.npm.mongoose

import io.scalajs.JSON
import io.scalajs.nodejs.buffer
import io.scalajs.npm.mongodb.doc
import io.scalajs.npm.mongoose.MongooseTest.Comment
import org.scalatest.FunSpec

import scala.scalajs.js

/**
  * Mongoose Tests
  * @author lawrence.daniels@gmail.com
  */
class MongooseTest extends FunSpec with MongoDBTestSupport {

  describe("Mongoose") {

    it("should support middleware functions") {
      import Mongoose.Schema.Types._

      withMongoURL { url =>
        info("Connecting to MongoDB...")
        val conn = Mongoose.connect(url)

        Mongoose.Promise = js.Dynamic.global.global.Promise
        //Mongoose.Promise = io.scalajs.nodejs.Module.module.require("bluebird")

        info("Define the schema")
        val commentSchema = Schema(
          "name" -> SchemaField(`type` = String, default = "John Doe"),
          "age" -> SchemaField(`type` = Number, min = 18, c = true),
          "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
          "date" -> SchemaField(`type` = Date, default = js.Date.now),
          "buff" -> Buffer
        )

        info("Define a mutator")
        commentSchema.path("name").set[String](_.toUpperCase)

        info("Define a middleware function")
        commentSchema.pre("save", { (next, _, _, _) =>
          info(commentSchema.get("name").toString)
          next()
        })

        info("Register the model")
        val commentModel = Mongoose.model[Comment]("Comment", commentSchema)

        info("Create an instance of the model")
        val comment = commentModel.create()
        comment.age = 21
        comment.bio = "Lover of life"
        comment.date = js.Date.now()

        info(s"model instance: ${JSON.stringify(comment)}")

        info("Persist the data object")
        comment.save()

        info("Retrieve a model by ID")
        commentModel.find(doc("_id" -> comment.id)).exec().`then`({ comments =>
          info(s"comments: ${JSON.stringify(comments)}")
        })
      }
    }

  }

}

/**
  * Mongoose Test Companion
  * @author lawrence.daniels@gmail.com
  */
object MongooseTest {

  @js.native
  trait Comment extends js.Object {
    var id: String = js.native
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[buffer.Buffer] = js.native
  }

}