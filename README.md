Mongoose API for Scala.js
================================
[mongoose](https://www.npmjs.com/package/mongoose) - Mongoose MongoDB ODM.

### Description

Mongoose is a MongoDB object modeling tool designed to work in an asynchronous environment.

**NOTE**: This is a work-in-progress binding. 

### Build Dependencies

* [SBT v0.13.16](http://www.scala-sbt.org/download.html)

### Build/publish the SDK locally

```bash
 $ sbt clean publish-local
```

### Running the tests

Before running the tests the first time, you must ensure the npm packages are installed:

```bash
$ npm install
```

Then you can run the tests:

```bash
$ sbt test
```

### Examples

```scala
import io.scalajs.JSON
import io.scalajs.nodejs._
import io.scalajs.npm.mongodb.doc
import io.scalajs.npm.mongoose._

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scalajs.js

// define the type-safe schema trait
@js.native
  trait ContactLike extends js.Object {
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[buffer.Buffer] = js.native
}

// define the schema
val contactSchema = {
    import Mongoose.Schema.Types._
    Schema[ContactLike](
        "name" -> SchemaField(`type` = String, default = "John Doe"),
        "age" -> SchemaField(`type` = Number, min = 18, c = true),
        "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
        "date" -> SchemaField(`type` = Date, default = js.Date.now),
        "buff" -> Buffer)
}

// register the model
val Contacts = Mongoose.model("Contact", contactSchema)

// create and populate an instance of the model
val newContact = Contacts()
newContact.name = "John Doe"
newContact.age = 21
newContact.bio = "Lover of life"
newContact.date = js.Date.now()

for {
    // connect to the database
    _ <- Mongoose.connectAsync("mongodb://localhost:27017/test").future
    
    // save the contact
    saved <- newContact.save().toFuture
    _ = println(s"saved contact: ${JSON.stringify(saved)}")
    
    // retrieve the contact by ID
    contactId = saved._id.orNull
    contactById <- Contacts.findById(contactId).exec().toFuture
    _ = println(s"contact-by-Id: ${JSON.stringify(contactById)}")
    
    // update the contact
    updateResult <- saved.increment().update(doc("name" -> "John Travolta", "age" -> 63)).toFuture
    _ = println(s"updated contact: ${JSON.stringify(saved)}")
    _ = Assert.ok(updateResult.nModified == 1 && updateResult.isOk, JSON.stringify(updateResult))
    
    // retrieve a contact via where clause
    oneContactWhere <- Contacts.findOne(doc()).where("age").gt(60).exec().toFuture
    _ = println(s"one-contact-where: ${JSON.stringify(oneContactWhere)}")
    
    // delete the contact
    deleted <- oneContactWhere.remove().toFuture
    _ = println(s"deleted contact: ${JSON.stringify(deleted)}")
} {
    println("Done.")
}
```

### Artifacts and Resolvers

To add the `Mongoose` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs.npm" %%% "mongoose" % "0.4.2"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```
