package helios

case class Point(x: Double, y: Double, z: Double):

  def toTuple: (Double, Double, Double, Double) =
    (x, y, z, 1.0)

end Point
