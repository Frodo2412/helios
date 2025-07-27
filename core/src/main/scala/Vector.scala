package helios

case class Vector(x: Double, y: Double, z: Double):

  def +(other: Vector): Vector =
    Vector(x + other.x, y + other.y, z + other.z)

  def -(other: Vector): Vector =
    Vector(x - other.x, y - other.y, z - other.z)

end Vector
