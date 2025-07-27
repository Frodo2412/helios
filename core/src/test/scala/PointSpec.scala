package helios

import instances.given

import cats.syntax.all.*
import weaver.FunSuite
import weaver.discipline.Discipline

object PointSpec extends FunSuite with Discipline:

  test("Should be able to construct a Point"):
    val point = Point(4.3, -4.2, 3.1)
    expect(point.x === 4.3)
    expect(point.y === -4.2)
    expect(point.z === 3.1)

  test("Should be able to add a point to a vector"):
    val point = Point(3, -2, 5)
    val vector = Vector(-2, 3, 1)
    val result = point + vector
    expect(result === Point(1, 1, 6))
    
  test("Should be able to subtract two points"):
    val point1 = Point(3, -2, 5)
    val point2 = Point(-2, 3, 1)
    val result = point1 - point2
    expect(result === Vector(5, -5, 4))

end PointSpec
