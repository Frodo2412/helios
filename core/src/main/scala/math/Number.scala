package helios
package math

import algebra.ring.Field
import algebra.instances.bigDecimal.*
import cats.Eq

opaque type Number = BigDecimal

object Number:

  given Eq[Number] = Eq.fromUniversalEquals[Number]

  given Field[Number] = bigDecimalAlgebra

end Number
