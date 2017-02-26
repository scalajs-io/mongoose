package io.scalajs.npm.mongoose

import io.scalajs.JSON
import io.scalajs.nodejs.{Assert, buffer, process}
import io.scalajs.npm.mongodb.doc
import io.scalajs.npm.mongoose.MongooseTest.ContactLike
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
    val contactSchema = {
      import Mongoose.Schema.Types._
      Schema[ContactLike](
        "name" -> SchemaField(`type` = String, default = "John Doe"),
        "age" -> SchemaField(`type` = Number, min = 18, c = true),
        "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
        "date" -> SchemaField(`type` = Date, default = js.Date.now),
        "buff" -> Buffer
      )
    }

    // define the model
    val Contacts = Mongoose.model("Contact", contactSchema)

    it("should support CRUD operations") {

      val newContact = Contacts()
      newContact.name = "John Doe"
      newContact.age = 21
      newContact.bio = "Lover of life"
      newContact.date = js.Date.now()

      // connect to MongoDB, and perform CRUD on the contact
      withMongoURL { url =>
        info(s"Connecting to '$url'...")
        Mongoose.connectAsync(url).future foreach { conn =>
          val outcome = for {
          /*
        // make sure there are no pre-existing contacts
          deleteResult <- Contacts.remove(doc()).toFuture
          _ = info(s"delete results: ${JSON.stringify(deleteResult)}")
          _ = if (deleteResult.n > 0) info(s"${deleteResult.n} document(s) deleted")
          */

          // save the contact
            saved <- newContact.save().toFuture
            _ = info(s"saved contact: ${JSON.stringify(saved)}")

            // retrieve the contact by ID
            contactById <- Contacts.findById(saved._id.orNull).exec().toFuture
            _ = info(s"contact-by-Id: ${JSON.stringify(contactById)}")

            // update the contact
            updateResult <- saved.increment().update(doc("name" -> "John Travolta", "age" -> 63)).toFuture
            _ = info(s"updated contact: ${JSON.stringify(saved)}")
            _ = Assert.ok(updateResult.nModified == 1 && updateResult.isOk, JSON.stringify(updateResult))

            // retrieve using where
            contactByWhere <- Contacts.findOne(doc()).where("age").gt(20).exec().toFuture
            _ = info(s"contact-by-where: ${JSON.stringify(contactByWhere)}")

            // delete the contact
            deleted <- contactByWhere.remove().toFuture
            _ = info(s"deleted contact: ${JSON.stringify(deleted)}")
          } yield deleted

          outcome onComplete {
            case Failure(e) =>
              alert(e.getMessage)
              conn.close { err =>
                alert(err.message)
                process.exit(0)
              }
            case Success(_) =>
              info("Done.")
              conn.close { err =>
                alert(err.message)
                process.exit(0)
              }
          }
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
  trait ContactLike extends js.Object {
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[buffer.Buffer] = js.native
  }

}