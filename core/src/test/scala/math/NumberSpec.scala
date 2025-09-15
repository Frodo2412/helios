package helios
package math

import algebra.ring.Field
import algebra.ring.Ring
import cats.kernel.laws.discipline.EqTests
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import weaver.FunSuite
import weaver.discipline.Discipline

object NumberSpec extends FunSuite with Discipline:

  given Arbitrary[Number] =
    Arbitrary {
      Gen.double.map(Field[Number].fromDouble)
    }

  given Arbitrary[Number => Number] =
    Arbitrary(Arbitrary.arbitrary[Number].map(x => (y: Number) => Ring[Number].plus(x, y)))

  checkAll("Eq[Number]", EqTests[Number].eqv)

end NumberSpec
