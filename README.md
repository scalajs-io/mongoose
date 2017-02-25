Mongoose API for Scala.js
================================
[mongoose](https://www.npmjs.com/package/mongoose) - Mongoose MongoDB ODM.

### Description

Mongoose is a MongoDB object modeling tool designed to work in an asynchronous environment.

**NOTE**: This is a work-in-progress binding. It requires further development and is not yet functional.

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
import io.scalajs.npm.mongoose._
import io.scalajs.npm.mongoose.Mongoose.Schema.Types._
import scalajs.js

val CommentSchema = Schema(
    "name" -> SchemaField(`type` = String, default = "John Doe"),
    "age" -> SchemaField(`type` = Number, min = 18, c = true),
    "bio" -> SchemaField(`type` = String, `match` = js.RegExp("[a-z]")),
    "date" -> SchemaField(`type` = Date, default = js.Date.now),
    "buff" -> Buffer
)

// define a mutator
CommentSchema.path("name").set[String](_.toUpperCase)

// define a middleware function
CommentSchema.pre("save", { (next, _, _, _) =>
    console.log(CommentSchema.get("name"))
    next()
})

// register the model
val CommentModel = Mongoose.model("Comment", CommentSchema)

// create an instance of the model
val comment = CommentModel.create[Comment]()
comment.age = 21
comment.bio = "Lover of life"
comment.date = js.Date.now()

// persist the data object
comment.save { err =>
  console.error(JSON.stringify(err))
}

@js.native
trait Comment extends js.Object {
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
