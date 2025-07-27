package helios

case class Point(x: Double, y: Double, z: Double):

  def +(v: Vector): Point =
    Point(x + v.x, y + v.y, z + v.z)

end Point
