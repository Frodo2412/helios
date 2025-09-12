package helios
package math

import laws.{InnerProductSpaceTests, VectorSpaceTests}
import math.Number.given
import math.Vector3.given

import cats.kernel.laws.discipline.EqTests
import org.scalacheck.Arbitrary
import weaver.FunSuite
import weaver.discipline.Discipline
import weaver.scalacheck.Checkers

object Vector3Spec extends FunSuite with Discipline with Checkers:

  import NumberSpec.given

  given Arbitrary[Vector3[Number] => Vector3[Number]] = Arbitrary {
    Arbitrary
      .arbitrary[Number => Number]
      .map: f =>
        (v: Vector3[Number]) => Vector3(f(v.x), f(v.y), f(v.z))
  }

  given [T: Arbitrary]: Arbitrary[Vector3[T]] = Arbitrary {
    for {
      x <- Arbitrary.arbitrary[T]
      y <- Arbitrary.arbitrary[T]
      z <- Arbitrary.arbitrary[T]
    } yield Vector3(x, y, z)
  }

  checkAll("Eq[Vector3[Number]]", EqTests[Vector3[Number]].eqv)
  checkAll("InnerProductSpace[Vector3[Number], Number]", InnerProductSpaceTests[Vector3[Number], Number].innerProductSpace)

end Vector3Spec
