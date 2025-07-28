package helios

case class Vector(x: Double, y: Double, z: Double):

  def +(other: Vector): Vector =
    Vector(x + other.x, y + other.y, z + other.z)

  def -(other: Vector): Vector =
    Vector(x - other.x, y - other.y, z - other.z)

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

  def dot(other: Vector): Double = x * other.x + y * other.y + z * other.z

  def *(other: Vector): Double = dot(other)

end Vector

object Vector:

  val Zero: Vector = Vector(0.0, 0.0, 0.0)

  val UnitX: Vector = Vector(1.0, 0.0, 0.0)

  val UnitY: Vector = Vector(0.0, 1.0, 0.0)

  val UnitZ: Vector = Vector(0.0, 0.0, 1.0)

end Vector
