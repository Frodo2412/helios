package helios
package math

/** Hilbert Lie algebra over a field S with elements (vectors) V.
  *
  * This structure combines:
  *   - InnerProductSpace[V, S]: provides inner product ⟨·,·⟩ and vector space structure
  *   - LieAlgebra[V, S]: provides Lie bracket [·, ·] and vector space structure
  *
  * Note: Laws for both parent structures are expected to hold for instances.
  */
trait HilbertLieAlgebra[V, S] extends InnerProductSpace[V, S] with LieAlgebra[V, S]

object HilbertLieAlgebra:

  def apply[V, S](using hla: HilbertLieAlgebra[V, S]): HilbertLieAlgebra[V, S] = hla

end HilbertLieAlgebra
