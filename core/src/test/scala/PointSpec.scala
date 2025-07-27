package helios

import instances.given_Eq_Point

import cats.kernel.laws.discipline.EqTests
import cats.syntax.all.*
import org.scalacheck.Arbitrary
import weaver.FunSuite
import weaver.discipline.Discipline

object PointSpec extends FunSuite with Discipline:

  test("Should be able to construct a Point"):
    val point = Point(4.3, -4.2, 3.1)
    expect(point.x === 4.3)
    expect(point.y === -4.2)
    expect(point.z === 3.1)

end PointSpec
