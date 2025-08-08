package helios
package math

import algebra.laws.RingLaws
import algebra.ring.Ring
import cats.kernel.laws.discipline.EqTests
import org.scalacheck.Arbitrary
import weaver.FunSuite
import weaver.discipline.Discipline

object NumberSpec extends FunSuite with Discipline:

  given Arbitrary[Number] =
    Arbitrary(Arbitrary.arbitrary[Int].map(Ring[Number].fromInt))

  given Arbitrary[Number => Number] =
    Arbitrary(Arbitrary.arbitrary[Number].map(x => (y: Number) => Ring[Number].plus(x, y)))

  checkAll("Eq[Number]", EqTests[Number].eqv)
  checkAll("Ring[Number]", RingLaws[Number].ring)

end NumberSpec
