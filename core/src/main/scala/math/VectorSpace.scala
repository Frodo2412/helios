package helios
package math

import algebra.ring.Field
import cats.kernel.CommutativeGroup

/**
 * A vector space over a field F is a set V together with two operations:
 * - Vector addition: V × V → V (forms a commutative group)
 * - Scalar multiplication: F × V → V (action of the field on the vector space)
 * 
 * Mathematical definition:
 * - (V, +) forms a commutative group (associative, commutative, identity, inverse)
 * - F is a field (we use CommutativeRing as a close approximation)
 * - Scalar multiplication satisfies:
 *   1. Compatibility: a * (b * v) = (a * b) * v
 *   2. Identity: 1 * v = v
 *   3. Distributivity over vector addition: a * (u + v) = a * u + a * v
 *   4. Distributivity over scalar addition: (a + b) * v = a * v + b * v
 */
trait VectorSpace[V, S] extends CommutativeGroup[V] with Field[S]:
  
  /** Scalar multiplication: S × V → V */
  def scale(scalar: S, vector: V): V

end VectorSpace

object VectorSpace:
  
  def apply[V, S](using vs: VectorSpace[V, S]): VectorSpace[V, S] = vs

end VectorSpace