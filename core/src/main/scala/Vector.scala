package helios

case class Vector(x: Double, y: Double, z: Double):

  def toTuple: (Double, Double, Double, Double) =
    (x, y, z, 0.0)

end Vector
