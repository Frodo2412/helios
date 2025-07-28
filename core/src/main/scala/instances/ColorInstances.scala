package helios
package instances

import cats.Eq

given Eq[Color] = (c1: Color, c2: Color) =>
  Eq[Double].eqv(c1.red, c2.red) &&
    Eq[Double].eqv(c1.green, c2.green) &&
    Eq[Double].eqv(c1.blue, c2.blue)
