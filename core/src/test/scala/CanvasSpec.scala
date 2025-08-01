package helios

import weaver.FunSuite

object CanvasSpec extends FunSuite:

  test("Given a width and height, when Canvas#apply, it should initialize the canvas with every pixel in black"):
    val canvas = Canvas(10, 20)
    expect(canvas.width == 10) and expect(canvas.height == 20) and
      expect(canvas.pixels.forall(_ == Color.Black).compile.toList == List(true))

  test("Given a canvas and a color, when Canvas#set, it should be able to retrieve the color at"):
    val canvas = Canvas(10, 20)
    val color = Color(1, 0, 0)
    val updatedCanvas = canvas.set(2, 3, color)
    expect(updatedCanvas.get(2, 3) == color)

end CanvasSpec
