package helios
package laws

import cats.kernel.Eq
import helios.math.HilbertLieAlgebra

/** Laws for HilbertLieAlgebra type class.
  *
  * This structure should satisfy both:
  *   - InnerProductSpace laws
  *   - LieAlgebra laws
  */
trait HilbertLieAlgebraLaws[V, S]:

  given hla: HilbertLieAlgebra[V, S]
  given eqV: Eq[V]
  given eqS: Eq[S]

  // Compose the existing law suites for reuse in Tests
  def ipLaws: InnerProductSpaceLaws[V, S] = InnerProductSpaceLaws[V, S](using hla, eqV, eqS)
  def laLaws: LieAlgebraLaws[V, S]        = LieAlgebraLaws[V, S](using hla, eqV)

end HilbertLieAlgebraLaws

object HilbertLieAlgebraLaws:

  def apply[V, S](using hla0: HilbertLieAlgebra[V, S], eqV0: Eq[V], eqS0: Eq[S]): HilbertLieAlgebraLaws[V, S] =
    new HilbertLieAlgebraLaws[V, S]:
      given hla: HilbertLieAlgebra[V, S] = hla0
      given eqV: Eq[V]                   = eqV0
      given eqS: Eq[S]                   = eqS0

end HilbertLieAlgebraLaws
