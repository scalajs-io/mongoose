Mongoose API for Scala.js
================================
This is a Scala.js type-safe binding for [mongoose](https://www.npmjs.com/package/mongoose)

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
import io.scalajs.nodejs._
import io.scalajs.npm.mongoose._
import scalajs.js

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