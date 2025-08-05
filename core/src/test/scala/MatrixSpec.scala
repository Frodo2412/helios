package helios

import Matrix.given

import algebra.instances.all.*
import cats.syntax.all.*
import weaver.FunSuite
import weaver.discipline.Discipline

object MatrixSpec extends FunSuite with Discipline:

  test("Given a list of 16 elements, when Matrix#apply, it should create a 4x4 matrix"):
    val matrix = Matrix.square(
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

  test("Given a list of 4 elements, when Matrix#apply, it should create a 2x2 matrix"):
    val matrix = Matrix.square(
      -3, 5,
      1, -2
    )

    expect(matrix(0, 0) === -3) and
      expect(matrix(0, 1) === 5) and
      expect(matrix(1, 0) === 1) and
      expect(matrix(1, 1) === -2)

  test("Given a list of 9 elements, when Matrix#apply, it should create a 3x3 matrix"):
    val matrix = Matrix.square(
      -3, 5, 0,
      1, -2, -7,
      0, 1, 1
    )

    expect(matrix(0, 0) === -3) and
      expect(matrix(1, 1) === -2) and
      expect(matrix(2, 2) === 1)

  test("Given two matrices, when multiplying them, it should return the correct result"):
    val matrixA = Matrix.square(
      1, 2, 3, 4,
      5, 6, 7, 8,
      9, 8, 7, 6,
      5, 4, 3, 2
    )

    val matrixB = Matrix.square(
      -2, 1, 2, 3,
      3, 2, 1, -1,
      4, 3, 6, 5,
      1, 2, 7, 8
    )

    val expected = Matrix.square(
      20, 22, 50, 48,
      44, 54, 114, 108,
      40, 58, 110, 102,
      16, 26, 46, 42
    )

    expect(matrixA * matrixB === expected)

end MatrixSpec
