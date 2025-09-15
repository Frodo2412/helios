package helios
package laws

import math.LieAlgebra

import cats.kernel.laws.CommutativeGroupLaws
import cats.kernel.Eq
import cats.syntax.all.*
import org.scalacheck.Prop
import org.scalacheck.Prop.*

/**
 * Laws for LieAlgebra type class (over a field S with vectors V)
 *
 * Focuses on the canonical Lie algebra axioms:
 *  - Alternating / skew-symmetry: [v, v] = 0 and [u, v] = -[v, u]
 *  - Jacobi identity: [u,[v,w]] + [v,[w,u]] + [w,[u,v]] = 0
 *  - Bilinearity in each argument (scalar linearity and additivity)
 */
trait LieAlgebraLaws[V, S] extends CommutativeGroupLaws[V]:

  given la: LieAlgebra[V, S]
  given eqV: Eq[V]

  // Alternating: [v, v] = 0
  def alternating(v: V): Prop =
    la.bracket(v, v) === la.empty

  // Skew-symmetry: [u, v] = -[v, u]
  def skewSymmetry(u: V, v: V): Prop =
    la.bracket(u, v) === la.inverse(la.bracket(v, u))

  // Jacobi identity: [u,[v,w]] + [v,[w,u]] + [w,[u,v]] = 0
  def jacobi(u: V, v: V, w: V): Prop =
    val term1 = la.bracket(u, la.bracket(v, w))
    val term2 = la.bracket(v, la.bracket(w, u))
    val term3 = la.bracket(w, la.bracket(u, v))
    la.combine(la.combine(term1, term2), term3) === la.empty

  // Bilinearity: left argument
  def leftLinearityScalar(a: S, u: V, v: V): Prop =
    la.bracket(la.scale(a, u), v) === la.scale(a, la.bracket(u, v))

  def leftLinearityAdd(u: V, w: V, v: V): Prop =
    la.bracket(la.combine(u, w), v) === la.combine(la.bracket(u, v), la.bracket(w, v))

  // Bilinearity: right argument
  def rightLinearityScalar(a: S, u: V, v: V): Prop =
    la.bracket(u, la.scale(a, v)) === la.scale(a, la.bracket(u, v))

  def rightLinearityAdd(u: V, v: V, w: V): Prop =
    la.bracket(u, la.combine(v, w)) === la.combine(la.bracket(u, v), la.bracket(u, w))

end LieAlgebraLaws

object LieAlgebraLaws:

  def apply[V, S](using la0: LieAlgebra[V, S], eqV0: Eq[V]): LieAlgebraLaws[V, S] =
    new LieAlgebraLaws[V, S]:
      given la: LieAlgebra[V, S] = la0
      given S: cats.kernel.CommutativeGroup[V] = la0
      given eqV: Eq[V] = eqV0

end LieAlgebraLaws
