package helios
package math

import cats.kernel.CommutativeGroup
import cats.syntax.all.*
import cats.{Eq, Show}

case class Vector3[T](x: T, y: T, z: T)

object Vector3:

  given [T: Eq]: Eq[Vector3[T]] = (u: Vector3[T], v: Vector3[T]) =>
    u.x === v.x && u.y === v.y && u.z === v.z

  given [T: Show]: Show[Vector3[T]] = Show.fromToString[Vector3[T]]

  given [T: CommutativeGroup]: CommutativeGroup[Vector3[T]] with

    private val zero: T = CommutativeGroup[T].empty

    override val empty: Vector3[T] = Vector3(zero, zero, zero)

    override def combine(u: Vector3[T], v: Vector3[T]): Vector3[T] =
      Vector3(u.x |+| v.x, u.y |+| v.y, u.z |+| v.z)

    override def inverse(u: Vector3[T]): Vector3[T] =
      Vector3(u.x.inverse(), u.y.inverse(), u.z.inverse())

  end given

end Vector3
