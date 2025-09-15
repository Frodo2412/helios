package helios
package math

import algebra.ring.Field
import cats.syntax.all.*
import cats.{Eq, Show}

case class Vector3[T](x: T, y: T, z: T)

object Vector3:

  given [T: Eq]: Eq[Vector3[T]] = (u: Vector3[T], v: Vector3[T]) => u.x === v.x && u.y === v.y && u.z === v.z

  given [T: Show]: Show[Vector3[T]] = Show.fromToString[Vector3[T]]

  given [T: Field]: HilbertLieAlgebra[Vector3[T], T] with

    override val zero: T           = Field[T].zero
    override val one: T            = Field[T].one
    override val empty: Vector3[T] = Vector3(zero, zero, zero)

    // Scalar multiplication
    override def scale(scalar: T, vector: Vector3[T]): Vector3[T] =
      Vector3(Field[T].times(scalar, vector.x), Field[T].times(scalar, vector.y), Field[T].times(scalar, vector.z))

    // Inner product (dot product)
    override def inner(u: Vector3[T], v: Vector3[T]): T =
      val fx = Field[T]
      val a  = fx.times(u.x, v.x)
      val b  = fx.times(u.y, v.y)
      val c  = fx.times(u.z, v.z)
      fx.plus(fx.plus(a, b), c)

    // Field[T] operations
    override def negate(u: T): T      = Field[T].negate(u)
    override def plus(x: T, y: T): T  = Field[T].plus(x, y)
    override def div(x: T, y: T): T   = Field[T].div(x, y)
    override def times(x: T, y: T): T = Field[T].times(x, y)

    // CommutativeGroup[Vector3[T]] operations
    override def combine(x: Vector3[T], y: Vector3[T]): Vector3[T] =
      Vector3(Field[T].plus(x.x, y.x), Field[T].plus(x.y, y.y), Field[T].plus(x.z, y.z))
    override def inverse(a: Vector3[T]): Vector3[T]                = Vector3(negate(a.x), negate(a.y), negate(a.z))

    /** Lie bracket: V × V → V */
    override def bracket(u: Vector3[T], v: Vector3[T]): Vector3[T] =
      Vector3(
        Field[T].minus(Field[T].times(u.y, v.z), Field[T].times(u.z, v.y)),
        Field[T].minus(Field[T].times(u.z, v.x), Field[T].times(u.x, v.z)),
        Field[T].minus(Field[T].times(u.x, v.y), Field[T].times(u.y, v.x))
      )
  end given

end Vector3
