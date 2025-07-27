package helios
package instances

import cats.Eq

val Epsilon = 0.00001

given Eq[Double] = (x: Double, y: Double) => (x - y).abs < Epsilon
