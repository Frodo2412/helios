package helios
package math

import math.Number.given
import math.Vector3.given

import algebra.laws.RingLaws
import algebra.ring.Ring
import cats.Show
import cats.kernel.laws.discipline.{CommutativeGroupTests, EqTests}
import cats.syntax.all.*
import org.scalacheck.Arbitrary
import weaver.FunSuite
import weaver.discipline.Discipline

object Vector3Spec extends FunSuite with Discipline:

  given Arbitrary[Number] =
    Arbitrary(Arbitrary.arbitrary[Int].map(Ring[Number].fromInt))

  given Arbitrary[Number => Number] =
    Arbitrary(Arbitrary.arbitrary[Number].map(x => (y: Number) => Ring[Number].plus(x, y)))

  given [T: Arbitrary]: Arbitrary[Vector3[T]] = Arbitrary {
    for {
      x <- Arbitrary.arbitrary[T]
      y <- Arbitrary.arbitrary[T]
      z <- Arbitrary.arbitrary[T]
    } yield Vector3(x, y, z)
  }

  given [T: Show]: Show[Vector3[T]] = Show.fromToString[Vector3[T]]

  //  checkAll("Eq[Vector3[Number]", EqTests[Vector3[Number]].eqv)
  //  checkAll("CommutativeGroup[Vector]", CommutativeGroupTests[Vector3[Number]].commutativeGroup)

end Vector3Spec
