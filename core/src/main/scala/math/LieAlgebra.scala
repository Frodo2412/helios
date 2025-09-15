package helios
package math

/** Lie algebra over a field S with elements (vectors) V.
  *
  * Provides:
  *   - A vector space structure over S (scalar multiplication and abelian group under +)
  *   - A Lie bracket [·, ·]: V × V → V
  *
  * Laws (not enforced by types, but expected of instances):
  *   1. Bilinearity in each argument: [a·u + b·u', v] = a·[u, v] + b·[u', v] and [u, a·v + b·v'] = a·[u, v] + b·[u, v']
  *      2. Alternating/Antisymmetry: [v, v] = 0, which implies [u, v] = -[v, u] 3. Jacobi identity: [u, [v, w]] + [v,
  *      [w, u]] + [w, [u, v]] = 0
  */
trait LieAlgebra[V, S] extends VectorSpace[V, S]:

  /** Lie bracket: V × V → V */
  def bracket(u: V, v: V): V

end LieAlgebra

object LieAlgebra:

  def apply[V, S](using la: LieAlgebra[V, S]): LieAlgebra[V, S] = la

end LieAlgebra
