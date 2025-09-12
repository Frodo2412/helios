package helios
package laws

import math.InnerProductSpace

import cats.kernel.laws.CommutativeGroupLaws
import cats.kernel.{CommutativeGroup, Eq}
import cats.syntax.all.*
import org.scalacheck.Prop
import org.scalacheck.Prop.*

/**
 * Laws for InnerProductSpace type class (over a field S with vectors V)
 *
 * This focuses on the inner product axioms (for real fields):
 *  - Symmetry: ⟨u, v⟩ = ⟨v, u⟩
 *  - Linearity in first argument:
 *      ⟨a·u, v⟩ = a⟨u, v⟩ and ⟨u + w, v⟩ = ⟨u, v⟩ + ⟨w, v⟩
 *  - Positive-definiteness (definiteness part, without order):
 *      ⟨0, 0⟩ = 0 and (⟨v, v⟩ = 0) ⇒ v = 0
 */
trait InnerProductSpaceLaws[V, S] extends CommutativeGroupLaws[V]:

  given ips: InnerProductSpace[V, S]
  given eqV: Eq[V]
  given eqS: Eq[S]

  // Symmetry (for real inner product spaces)
  def symmetry(u: V, v: V): Prop =
    ips.inner(u, v) === ips.inner(v, u)

  // Linearity in first argument
  def linearityScalar(a: S, u: V, v: V): Prop =
    ips.inner(ips.scale(a, u), v) === ips.times(a, ips.inner(u, v))

  def linearityAdd(u: V, w: V, v: V): Prop =
    ips.inner(u |+| w, v) === ips.plus(ips.inner(u, v), ips.inner(w, v))

  // Definiteness aspects that don't require ordering
  def zeroIsZero: Prop =
    ips.inner(ips.empty, ips.empty) === ips.zero

  def definiteness(v: V): Prop =
    // If ⟨v, v⟩ == 0 then v must be the zero vector (avoid ScalaCheck discards)
    if Eq[S].eqv(ips.inner(v, v), ips.zero) then Prop(Eq[V].eqv(v, ips.empty)) else Prop(true)

end InnerProductSpaceLaws

object InnerProductSpaceLaws:

  def apply[V, S](using ips0: InnerProductSpace[V, S], eqV0: Eq[V], eqS0: Eq[S]): InnerProductSpaceLaws[V, S] =
    new InnerProductSpaceLaws[V, S]:
      given ips: InnerProductSpace[V, S] = ips0
      given S: CommutativeGroup[V] = ips0
      given eqV: Eq[V] = eqV0
      given eqS: Eq[S] = eqS0

end InnerProductSpaceLaws