package helios

case class Point(x: Double, y: Double, z: Double):

  def +(v: Vector): Point =
    Point(x + v.x, y + v.y, z + v.z)

  def -(other: Point): Vector =
    Vector(x - other.x, y - other.y, z - other.z)

end Point
