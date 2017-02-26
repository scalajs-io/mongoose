Mongoose API for Scala.js
================================
[mongoose](https://www.npmjs.com/package/mongoose) - Mongoose MongoDB ODM.

### Description

Mongoose is a MongoDB object modeling tool designed to work in an asynchronous environment.

**NOTE**: This is a work-in-progress binding. 

### Build Dependencies

* [ScalaJs.io v0.3.x](https://github.com/scalajs-io/scalajs.io)
* [SBT v0.13.13](http://www.scala-sbt.org/download.html)

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

// register the model
val Comments = Mongoose.model[CommentLike]("Comment", commentSchema)

// create an instance of the model
val comment = Comments()
comment.name = "John Doe"
comment.age = 21
comment.bio = "Lover of life"
comment.date = js.Date.now()

for {
    // connect to the database
    _ <- Mongoose.connectAsync("mongodb://localhost:27017/test").future
    
    // make sure there are no pre-existing comments
    deletes <- Comments.remove(doc()).toFuture
    _ = println(s"deletes: ${JSON.stringify(deletes)}")
    
    // save the comment
    saved <- comment.save().toFuture
    _ = println(s"saved comment: ${JSON.stringify(saved)}")
    
    // retrieve the comment(s)
    comments <- Comments.find(doc()).exec().toFuture
    _ = println(s"comments: ${JSON.stringify(comments)}")
    
    // update the comment
    result <- {
        saved.name = "John Travola"
        saved.age = 63
        saved.update().toFuture
    }
    _ = println(s"updated comment: ${JSON.stringify(saved)}")
    _ = Assert.ok(result.nModified == 1 && result.isOk, JSON.stringify(result))
    
    // delete the comment
    deleted <- saved.remove().toFuture
    _ = println(s"deleted comment: ${JSON.stringify(deleted)}")
} {
  println("Done.")
}

@js.native
trait CommentLike extends js.Object {
    var name: String = js.native
    var age: js.UndefOr[Int] = js.native
    var bio: js.UndefOr[String] = js.native
    var date: js.UndefOr[Double] = js.native
    var buff: js.UndefOr[buffer.Buffer] = js.native
}
```

### Artifacts and Resolvers

To add the `Mongoose` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs.npm" %%% "mongoose" % "4.8.1"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```
