package helios

import instances.given

import cats.syntax.all.*
import weaver.FunSuite

object ColorSpec extends FunSuite:

  test("Given valid RGB values, when Color#apply, it should create a Color instance"):
    val color = Color(-0.5, 0.4, 1.7)
    expect(color.red == -0.5) and
      expect(color.green == 0.4) and
      expect(color.blue == 1.7)

  test("Given two colors, when adding, it should add the RGB values"):
    val color1 = Color(0.9, 0.6, 0.75)
    val color2 = Color(0.7, 0.1, 0.25)
    expect(color1 + color2 === Color(1.6, 0.7, 1.0))

  test("Given two colors, when subtracting, it should subtract the RGB values"):
    val color1 = Color(0.9, 0.6, 0.75)
    val color2 = Color(0.7, 0.1, 0.25)
    expect(color1 - color2 === Color(0.2, 0.5, 0.5))

  test("Given a color and a scalar, when multiplying, it should multiply the RGB values"):
    val color = Color(0.2, 0.3, 0.4)
    expect(color * 2.0 === Color(0.4, 0.6, 0.8))

  test("Given two colors, when multiplying, it should multiply the RGB values"):
    val color1 = Color(1, 0.2, 0.4)
    val color2 = Color(0.9, 1, 0.1)
    expect(color1 * color2 === Color(0.9, 0.2, 0.04))

end ColorSpec
