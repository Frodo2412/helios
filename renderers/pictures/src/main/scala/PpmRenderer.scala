package helios

import java.lang.Math.min

object PpmRenderer:

  def render(canvas: Canvas): String =
    PpmHeader(canvas.width, canvas.height) +
      canvas.pixels
        .map(colorToPpmString)
        .grouped(canvas.width)
        .flatMap:
          _.flatMap:
            _.split(" ")
          .grouped(17)
        .map:
          _.mkString(" ")
        .mkString("", "\n", "\n")

  private def PpmHeader(width: Int, height: Int) =
    s"P3\n$width $height\n255\n"

  private def colorToPpmString(color: Color): String =
    val r = (color.red * 255).round.toInt.min(255).max(0)
    val g = (color.green * 255).round.toInt.min(255).max(0)
    val b = (color.blue * 255).round.toInt.min(255).max(0)
    s"$r $g $b"

end PpmRenderer

