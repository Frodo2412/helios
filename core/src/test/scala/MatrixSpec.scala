package helios

import cats.syntax.all.*
import weaver.FunSuite

object MatrixSpec extends FunSuite:

  test("Given a list of 16 elements, when Matrix#apply, it should create a 4x4 matrix"):
    val matrix = Matrix(
      1, 2, 3, 4,
      5.5, 6.5, 7.5, 8.5,
      9, 10, 11, 12,
      13.5, 14.5, 15.5, 16.5
    )

    expect(matrix(0, 0) === 1) and
      expect(matrix(0, 3) === 4) and
      expect(matrix(1, 0) === 5.5) and
      expect(matrix(1, 2) === 7.5) and
      expect(matrix(2, 2) === 11) and
      expect(matrix(3, 0) === 13.5) and
      expect(matrix(3, 2) === 15.5)

end MatrixSpec
