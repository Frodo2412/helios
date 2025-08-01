package helios

// TODO: would I rather use 0-255 range?
case class Color(red: Double, green: Double, blue: Double):

  def +(that: Color): Color = Color(red + that.red, green + that.green, blue + that.blue)

  def -(that: Color): Color = Color(red - that.red, green - that.green, blue - that.blue)

  def *(scalar: Double): Color = Color(red * scalar, green * scalar, blue * scalar)

  def *(that: Color): Color = Color(red * that.red, green * that.green, blue * that.blue)

end Color

object Color:

  val Black: Color = Color(0.0, 0.0, 0.0)

end Color
