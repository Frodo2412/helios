package helios

import cats.Applicative
import cats.effect.Concurrent
import cats.syntax.all.*
import fs2.{Pure, Stream}


// TODO: See maybe doing tagless final here
trait Canvas(_width: Int, _height: Int):
  self =>

  val width: Int = _width

  val height: Int = _height

  def set(x: Int, y: Int, color: Color): Canvas =
    new Canvas(width, height):

      override def pixels: Stream[Pure, Color] =
        self
          .pixels
          .zipWithIndex
          .map:
            case (prevColor, index) =>
              if index == y * width + x
              then color
              else prevColor

    end new

  def get(x: Int, y: Int): Color =
    pixels
      .drop(y * width + x)
      .compile
      .toList
      .head

  def pixels: fs2.Stream[Pure, Color]

end Canvas

object Canvas:

  def apply(width: Int, height: Int): Canvas =
    new Canvas(width, height):

      override def pixels: fs2.Stream[Pure, Color] =
        Stream.emit(Color.Black).repeatN(this.width.toLong * this.height.toLong)

    end new

end Canvas
