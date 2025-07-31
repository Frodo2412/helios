package helios

import weaver.{FunSuite, SimpleIOSuite}

object PpmRendererSpec extends FunSuite:

  test("Given a canvas, when rendering to PPM, it should produce a valid PPM header"):
    val canvas = Canvas(5, 3)
    val ppm = PpmRenderer.render(canvas)
    expect(ppm.startsWith("P3\n5 3\n255\n"))

  test("Given a canvas, when rendering to PPM, it should set colors in the correct places"):
    val canvas = Canvas(5, 3)
      .set(0, 0, Color(1.5, 0, 0))
      .set(2, 1, Color(0, 0.5, 0))
      .set(4, 2, Color(-0.5, 0, 1))
    val ppm = PpmRenderer.render(canvas)
    val expected =
      "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
        "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0\n" +
        "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255"
    expect(ppm.body == expected)

  test("Given a canvas with long lines, when rendering to PPM, it should split lines at 70 characters"):
    var canvas = Canvas(10, 2)
    val color = Color(1, 0.8, 0.6)
    for i <- 0 until 10
        j <- 0 until 2 do canvas = canvas.set(i, j, color)
    val ppm = PpmRenderer.render(canvas)
    val expected =
      "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204\n" +
        "153 255 204 153 255 204 153 255 204 153 255 204 153\n" +
        "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204\n" +
        "153 255 204 153 255 204 153 255 204 153 255 204 153"
    expect(clue(ppm.body) == clue(expected))

  test("Given a canvas, when rendering to PPM, it should finish with \\n"):
    val canvas = Canvas(5, 3)
    val ppm = PpmRenderer.render(canvas)
    expect(ppm.endsWith("\n"))

  extension (x: String)
    def body: String = x.linesIterator.drop(3).mkString("\n")

end PpmRendererSpec
