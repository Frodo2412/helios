package helios

import instances.given

import cats.Show
import cats.syntax.all.*
import org.scalacheck.Arbitrary
import weaver.FunSuite

object VectorSpec extends FunSuite:

  given Arbitrary[Vector] = Arbitrary {
    for {
      x <- Arbitrary.arbitrary[Double]
      y <- Arbitrary.arbitrary[Double]
      z <- Arbitrary.arbitrary[Double]
    } yield Vector(x, y, z)
  }

  given Show[Vector] = Show.fromToString[Vector]

  // TODO: Some of these tests can be turned into property-based tests because this is a pure mathematical structure.

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
    val vector = Vector(1, -2, 3)
    expect(-vector === Vector(-1, 2, -3))

  test("Should be able to multiply a Vector by a scalar"):
    val vector = Vector(1, -2, 3)
    expect(vector * 3.5 === Vector(3.5, -7.0, 10.5)) and expect(vector * 0.5 === Vector(0.5, -1.0, 1.5))

  test("Should be able to divide a Vector by a scalar"):
    val vector = Vector(1, -2, 3)
    expect(vector / 2.0 === Vector(0.5, -1.0, 1.5))

  test("Given a unitary vector, when #magnitude, it should return 1.0"):
    val x = Vector(1, 0, 0)
    val y = Vector(0, 1, 0)
    val z = Vector(0, 0, 1)
    expect(x.magnitude === 1.0) and expect(y.magnitude === 1.0) and expect(z.magnitude === 1.0)

  test("Given a vector, when #magnitude, it should return the correct magnitude"):
    val vector = Vector(1, 2, 3)
    expect(vector.magnitude === Math.sqrt(14)) and expect((-vector).magnitude === Math.sqrt(14))

  test("Given a zero vector, when #magnitude, it should return 0.0"):
    val zeroVector = Vector(0, 0, 0)
    expect(zeroVector.magnitude === 0.0)

  test("Given a vector, when #normalize, it should return a unit vector"):
    expect(Vector(4, 0, 0).normalize === Vector(1, 0, 0)) and
      expect(Vector(1, 2, 3).normalize.magnitude === 1.0)
    
  test("Given two vectors, when #dot, it should return the correct dot product"):
    val v = Vector(1,2,3)
    val u = Vector(2,3,4)
    expect(v.dot(u) === 20.0)
    
end VectorSpec
