package io.scalajs.npm
package mongoose

import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.npm.mongoose.SchemaTest.Thing
import org.scalatest.FunSpec

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * Schema Test
  * @see http://mongoosejs.com/docs/schematypes.html
  * @author lawrence.daniels@gmail.com
  */
class SchemaTest extends FunSpec {

  describe("Schema") {

    it("should support schema declarations") {
      val schema = new Schema()

      // example use

      val Thing = mongoose.model("Thing", schema)

      val m = new Thing
      m.name = "Statue of Liberty"
      m.age = 125
      m.updated = new js.Date
      m.binary = new Buffer(0)
      m.living = false
      m.mixed = { any: { thing: "i want" } }
      m.markModified("mixed")
      m._someId = new mongoose.Types.ObjectId
      m.array.push(1)
      m.ofString.push("strings!")
      m.ofNumber.unshift(1,2,3,4)
      m.ofDates.addToSet(new js.Date)
      m.ofBuffer.pop()
      m.ofMixed = [1, [], "three", { four: 5 }]
      m.nested.stuff = "good"
      m.save(callback)
    }

  }

}

/**
  * Schema Test Companion
  * @author lawrence.daniels@gmail.com
  */
object SchemaTest {

  @ScalaJSDefined
  class Thing extends js.Object {
    var name: String = _
    var binary: Buffer = _
    var living: Boolean = _
    var updated: js.Date = new js.Date() // { type: Date, default: Date.now }
    var age: Int  = _ // { type: Number, min: 18, max: 65 }
    var mixed: Schema.Types.Mixed = _
    var _someId: Schema.Types.ObjectId = _
    var array: js.Array[js.Any] = _
    var ofString: js.Array[String] = _
    var ofNumber: js.Array[Number] = _
    var ofDates: js.Array[js.Date] = _
    var ofBuffer: js.Array[Buffer] = _
    var ofBoolean: js.Array[Boolean] = _
    var ofMixed: js.Array[Schema.Types.Mixed] = _
    var ofObjectId: js.Array[Schema.Types.ObjectId] = _
    var nested: NestedStuff = _ //{ stuff: { type: String, lowercase: true, trim: true } }
  }

  @ScalaJSDefined
  class NestedStuff(var stuff: String)
    extends js.Object
}
