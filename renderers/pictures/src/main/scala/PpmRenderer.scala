package helios

import fs2.{Chunk, Pipe, Pure, Stream}

import java.lang.Math.min

object PpmRenderer:

  private val MaxColorsPerLine = 17 // Each color takes 3 characters and space, so 17 colors fit in 70 characters

  def render(canvas: Canvas): fs2.Stream[Pure, String] =
    ppmHeader(canvas.width, canvas.height) ++
      canvas.pixels
        .through(colorToPpm)
        .through(makeLines(canvas.width))
        .through(splitLines(MaxColorsPerLine))

  private def ppmHeader(width: Long, height: Long): Stream[Pure, String] =
    Stream("P3", s"$width $height", "255")

  private val colorToPpm: Pipe[Pure, Color, String] =
    _.map:
      color =>
        val r = (color.red * 255).round.toInt.min(255).max(0)
        val g = (color.green * 255).round.toInt.min(255).max(0)
        val b = (color.blue * 255).round.toInt.min(255).max(0)
        s"$r $g $b"

  private def makeLines(lineLength: Int): Pipe[Pure, String, String] =
    _.chunkN(lineLength, allowFewer = true)
      .map:
        chunk => chunk.toList.mkString(" ")

  private def splitLines(maxLength: Int): Pipe[Pure, String, String] =
    _.map:
      line =>
        line
          .split(" ")
          .grouped(maxLength)
          .map:
            _.mkString(" ")
          .toSeq
    .flatMap:
      Stream(_ *)

end PpmRenderer

