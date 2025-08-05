package helios

import algebra.ring.Ring
import cats.Eq

trait Matrix[T: Ring]:

  self =>

  def apply(row: Int, column: Int): T

  def rows: Int

  def columns: Int

  def size: (Int, Int) = (rows, columns)

  def *(that: Matrix[T]): Matrix[T] =
    new Matrix[T]:

      def rows: Int = self.rows

      def columns: Int = that.columns

      def apply(row: Int, column: Int): T =
        (0 until self.columns).foldLeft(Ring[T].zero):
          (acc, k) =>
            Ring[T].plus(acc, Ring[T].times(self(row, k), that(k, column)))

    end new


end Matrix

object Matrix:

  // TODO: Make this more type safe
  def square[T: Ring](elements: T*): Matrix[T] =
    new Matrix[T]:

      def rows: Int = math.sqrt(elements.size).toInt

      def columns: Int = rows

      def apply(row: Int, column: Int): T = elements(row * columns + column)

    end new

  given [T: Eq]: Eq[Matrix[T]] =
    (x: Matrix[T], y: Matrix[T]) =>
      (for
        row <- 0 until x.rows
        column <- 0 until x.columns
      yield
        Eq[T].eqv(x(row, column), y(row, column)))
        .reduce(_ && _)

end Matrix
