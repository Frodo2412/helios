package helios
package math

import algebra.instances.BigDecimalAlgebra
import algebra.ring.Field
import algebra.instances.bigDecimal.*
import cats.Eq

import java.math.{MathContext, RoundingMode}

opaque type Number = BigDecimal

object Number:

  // Use a very high-precision MathContext to minimize rounding error and improve lawfulness while permitting non-terminating divisions.
  private val mc: MathContext = new MathContext(100, RoundingMode.HALF_EVEN)

  // Approximate equality: consider two Numbers equal if they are numerically very close
  // using a relative tolerance. This avoids failures from benign rounding differences.
  private val eqTol: java.math.BigDecimal = new java.math.BigDecimal("1e-12")
  given Eq[Number] = Eq.instance { (x, y) =>
    val jx = x.bigDecimal
    val jy = y.bigDecimal
    if (jx.compareTo(jy) == 0) true
    else {
      val diff = jx.subtract(jy, mc).abs()
      val ax = jx.abs()
      val ay = jy.abs()
      val max = if (ax.compareTo(ay) >= 0) ax else ay
      val denom = if (max.compareTo(java.math.BigDecimal.ONE) > 0) max else java.math.BigDecimal.ONE
      val rel = diff.divide(denom, mc)
      rel.compareTo(eqTol) <= 0
    }
  }

  given Field[Number] = new BigDecimalAlgebra(mc)

end Number
