package helios
package math

import algebra.ring.Ring
import cats.Eq

opaque type Number = Long

object Number:

  given Eq[Number] = Eq.fromUniversalEquals[Number]

  given Ring[Number] = new Ring[Number]:

    def zero: Number = 0

    def one: Number = 1

    def plus(x: Number, y: Number): Number = x + y

    def times(x: Number, y: Number): Number = x * y

    override def minus(x: Number, y: Number): Number = x - y

    def negate(x: Number): Number = -x

  end given

end Number
