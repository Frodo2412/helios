package helios
package instances

import cats.Eq

given Eq[Point] = (p1: Point, p2: Point) =>
  Eq[Double].eqv(p1.x, p2.x) &&
    Eq[Double].eqv(p1.y, p2.y) &&
    Eq[Double].eqv(p1.z, p2.z)
