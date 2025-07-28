package helios

case class Vector(x: Double, y: Double, z: Double):

  def +(that: Vector): Vector =
    Vector(x + that.x, y + that.y, z + that.z)

  def -(that: Vector): Vector =
    Vector(x - that.x, y - that.y, z - that.z)

  def unary_- : Vector =
    Vector(-x, -y, -z)

  def *(scalar: Double): Vector =
    Vector(x * scalar, y * scalar, z * scalar)

  def /(scalar: Double): Vector =
    if (scalar == 0) throw new ArithmeticException("Division by zero")
    Vector(x / scalar, y / scalar, z / scalar)

  def magnitude: Double =
    Math.sqrt(x * x + y * y + z * z)

  def normalize: Vector =
    if this == Vector.Zero
    then throw new ArithmeticException("Cannot normalize a zero vector")
    else Vector(x / magnitude, y / magnitude, z / magnitude)

  def dot(that: Vector): Double = x * that.x + y * that.y + z * that.z

  def *(that: Vector): Double = dot(that)

  def cross(that: Vector): Vector =
    Vector(
      this.y * that.z - this.z * that.y,
      this.z * that.x - this.x * that.z,
      this.x * that.y - this.y * that.x
    )

  // TODO: Think of a better symbol for cross product.
  infix def x(that: Vector): Vector = cross(that)

end Vector

object Vector:

  val Zero: Vector = Vector(0.0, 0.0, 0.0)

  val UnitX: Vector = Vector(1.0, 0.0, 0.0)

  val UnitY: Vector = Vector(0.0, 1.0, 0.0)

  val UnitZ: Vector = Vector(0.0, 0.0, 1.0)

end Vector
