package helios
package math

/** Inner product space over a field S with vectors V.
  *
  * Provides:
  *   - An inner product ⟨·,·⟩: V × V → S
  *   - A vector space structure over S
  *
  * Laws (not enforced by types, but expected of instances):
  *   1. Conjugate symmetry: ⟨u, v⟩ = conj(⟨v, u⟩) — for real fields this is simply symmetry ⟨u, v⟩ = ⟨v, u⟩ 2.
  *      Linearity in the first argument: ⟨a·u + b·w, v⟩ = a⟨u, v⟩ + b⟨w, v⟩ 3. Positive-definiteness: ⟨v, v⟩ ≥ 0 and
  *      ⟨v, v⟩ = 0 iff v = 0
  */
trait InnerProductSpace[V, S] extends VectorSpace[V, S]:

  /** Inner product: V × V → S */
  def inner(u: V, v: V): S

end InnerProductSpace

object InnerProductSpace:

  def apply[V, S](using ips: InnerProductSpace[V, S]): InnerProductSpace[V, S] = ips

end InnerProductSpace
