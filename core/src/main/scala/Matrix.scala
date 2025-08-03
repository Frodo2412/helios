package helios

trait Matrix[T]:

  def apply(row: Int, column: Int): T

end Matrix

object Matrix:

  // TODO: Make this more type safe
  def apply[T](elements: T*): Matrix[T] =
    val size = Math.sqrt(elements.length).toInt
    (row: Int, column: Int) => elements(row * size + column)

end Matrix
