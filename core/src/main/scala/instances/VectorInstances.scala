package helios
package instances

import cats.Eq

given Eq[Vector] = (v1: Vector, v2: Vector) =>
  Eq[Double].eqv(v1.x, v2.x) &&
    Eq[Double].eqv(v1.y, v2.y) &&
    Eq[Double].eqv(v1.z, v2.z)
