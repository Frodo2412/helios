package helios
package math

import java.math.MathContext
import java.math.RoundingMode

import algebra.instances.BigDecimalAlgebra
import algebra.ring.Field
import cats.Eq
import cats.Show

opaque type Number = BigDecimal

object Number:

  // Use a very high-precision MathContext to minimize rounding error and improve lawfulness while permitting non-terminating divisions.
  private val mc: MathContext               = new MathContext(100, RoundingMode.HALF_EVEN)
  private val Epsilon: java.math.BigDecimal = new java.math.BigDecimal("1e-12")

  given Eq[Number] =
    (x, y) =>
      val jx    = x.bigDecimal
      val jy    = y.bigDecimal
      val diff  = jx.subtract(jy, mc).abs()
      val denom = jx.abs().max(jy.abs()).max(java.math.BigDecimal.ONE)
      diff.divide(denom, mc).compareTo(Epsilon) <= 0

  given Show[Number] = Show.fromToString[Number]

  // Stable, named instance to avoid ambiguous/recursive implicit resolution when needed explicitly
  val field: Field[Number] = new BigDecimalAlgebra(mc)

  given Field[Number] = field

end Number
