package helios

import PointSpec.{expect, test}

import cats.syntax.all.*
import weaver.FunSuite

object VectorSpec extends FunSuite:

  test("Should be able to construct a Vector"):
    val point = Vector(4.3, -4.2, 3.1)
    expect(point.x === 4.3)
    expect(point.y === -4.2)
    expect(point.z === 3.1)

end VectorSpec
