package io.scalajs.npm.mongoose

import io.scalajs.JSON
import io.scalajs.nodejs.{Assert, buffer}
import io.scalajs.npm.mongodb.doc
import io.scalajs.npm.mongoose.MongooseTest.CommentLike
import org.scalatest.FunSpec

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.util.{Failure, Success}

/**
  * Mongoose Tests
  * @author lawrence.daniels@gmail.com
  */
class MongooseTest extends FunSpec with MongoDBTestSupport {

  describe("Mongoose") {

    // set the promise type
    Mongoose.Promise = js.Dynamic.global.global.Promise

    // define the schema
    val commentSchema = {
      import Mongoose.Schema.Types._
      Schema(
        "name" -> SchemaField(`type` = String, default = "John Doe"),
        "age" -> SchemaField(`type` = Number, min = 18, c = true),
        "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
        "date" -> SchemaField(`type` = Date, default = js.Date.now),
        "buff" -> Buffer
      )
    }

    // define the model
    val Comments = Mongoose.model[CommentLike]("Comment", commentSchema)

    it("should support CRUD operations") {

      val comment = Comments()
      comment.name = "John Doe"
      comment.age = 21
      comment.bio = "Lover of life"
      comment.date = js.Date.now()

      // connect to MongoDB, and perform CRUD on the comment
      withMongoURL { url =>
        info(s"Connecting to '$url'...")
        val outcome = for {
          _ <- Mongoose.connectAsync(url).future

          // make sure there are no pre-existing comments
          deletes <- Comments.remove(doc()).toFuture
          _ = info(s"deletes: ${JSON.stringify(deletes)}")

          // save the comment
          saved <- comment.save().toFuture
          _ = info(s"saved comment: ${JSON.stringify(saved)}")

          // retrieve the comment(s)
          comments <- Comments.find(doc()).exec().toFuture
          _ = info(s"comments: ${JSON.stringify(comments)}")

          // update the comment
          result <- {
            saved.name = "John Travolta"
            saved.age = 63
            saved.update().toFuture
          }
          _ = info(s"updated comment: ${JSON.stringify(saved)}")
          _ = Assert.ok(result.nModified == 1 && result.isOk, JSON.stringify(result))

          // delete the comment
          deleted <- saved.remove().toFuture
          _ = info(s"deleted comment: ${JSON.stringify(deleted)}")
        } yield comments

        outcome onComplete {
          case Failure(e) => alert(e.getMessage)
          case Success(_) =>
        }
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
  trait CommentLike extends js.Object {
    var _id: js.UndefOr[ObjectId] = js.native
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[buffer.Buffer] = js.native
  }

}