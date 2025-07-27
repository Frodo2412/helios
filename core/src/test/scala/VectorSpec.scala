package helios

import instances.given

import cats.syntax.all.*
import weaver.FunSuite

object VectorSpec extends FunSuite:

  test("Should be able to construct a Vector"):
    val point = Vector(4.3, -4.2, 3.1)
    expect(point.x === 4.3) and expect(point.y === -4.2) and expect(point.z === 3.1)

  test("Should be able to add a Vector to another Vector"):
    val vector1 = Vector(3, -2, 5)
    val vector2 = Vector(-2, 3, 1)
    val result = vector1 + vector2
    expect(result === Vector(1, 1, 6))

  test("Should be able to subtract two Vectors"):
    val vector1 = Vector(3, 2, 1)
    val vector2 = Vector(5, 6, 7)
    val result = vector1 - vector2
    expect(result === Vector(-2, -4, -6))

  test("Should be able to get the opposite of a Vector"):
    val vector = Vector(1, 2, 3)
    expect(-vector === Vector(-1, -2, -3))

end VectorSpec
