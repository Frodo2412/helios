package helios

import fs2.Pure

case class Canvas(width: Int, height: Int, _pixels: Array[Color]):

  def set(x: Int, y: Int, color: Color): Canvas =
    val index = y * width + x
    this.copy(_pixels = _pixels.updated(index, color))

  def get(x: Int, y: Int): Color =
    val index = y * width + x
    _pixels(index)

  def pixels: fs2.Stream[Pure, Color] = fs2.Stream.emits(_pixels)

end Canvas

object Canvas:

  def apply(width: Int, height: Int): Canvas =
    new Canvas(width, height, Array.fill(width * height)(Color.Black))

end Canvas
