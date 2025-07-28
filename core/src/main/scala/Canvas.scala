package helios

case class Canvas(width: Int, height: Int, pixels: Array[Color]):

  def set(x: Int, y: Int, color: Color): Canvas =
    val index = y * width + x
    this.copy(pixels = pixels.updated(index, color))

  def get(x: Int, y: Int): Color =
    val index = y * width + x
    pixels(index)

end Canvas

object Canvas:

  def apply(width: Int, height: Int): Canvas =
    new Canvas(width, height, Array.fill(width * height)(Color.Black))

end Canvas
