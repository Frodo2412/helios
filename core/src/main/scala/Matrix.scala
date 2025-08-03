package helios

import cats.Eq

trait Matrix[T]:

  def apply(row: Int, column: Int): T

  def size: Int

end Matrix

object Matrix:

  // TODO: Make this more type safe
  def apply[T](elements: T*): Matrix[T] =
    new Matrix[T]:

      def size: Int = Math.sqrt(elements.length).toInt

      def apply(row: Int, column: Int): T = elements(row * size + column)

    end new

  given [T: Eq]: Eq[Matrix[T]] =
    (x: Matrix[T], y: Matrix[T]) =>
      x.size == y.size &&
        (0 until x.size).forall:
          row =>
            (0 until x.size).forall:
              column =>
                Eq[T].eqv(x(row, column), y(row, column))

end Matrix
