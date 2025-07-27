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

end Vector
