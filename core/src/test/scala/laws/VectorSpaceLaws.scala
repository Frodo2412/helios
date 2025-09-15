package helios
package laws

import cats.kernel.CommutativeGroup
import cats.kernel.Eq
import cats.kernel.laws.CommutativeGroupLaws
import cats.syntax.all._
import helios.math.VectorSpace
import org.scalacheck.Prop
import org.scalacheck.Prop._

/**
 * Laws for VectorSpace type class
 *
 * Note: Vector addition laws come from CommutativeGroup[V]
 * and scalar arithmetic laws come from Field[S] (via VectorSpace).
 * Here we only test the interaction laws via the provided instance.
 */
trait VectorSpaceLaws[V, S] extends CommutativeGroupLaws[V]:

  given vs: VectorSpace[V, S]

  given eqV: Eq[V]

  given eqS: Eq[S]


  // Vector space specific laws (scalar multiplication laws)
  def scalarMultiplicationCompatibility(a: S, b: S, v: V): Prop =
    vs.scale(a, vs.scale(b, v)) === vs.scale(vs.times(a, b), v)

  def scalarMultiplicationIdentity(v: V): Prop =
    vs.scale(vs.one, v) === v

  def scalarMultiplicationDistributivityOverVectorAddition(a: S, u: V, v: V): Prop =
    vs.scale(a, (u |+| v)) === (vs.scale(a, u) |+| vs.scale(a, v))

  def scalarMultiplicationDistributivityOverScalarAddition(a: S, b: S, v: V): Prop =
    vs.scale(vs.plus(a, b), v) === (vs.scale(a, v) |+| vs.scale(b, v))

  def scalarZeroAnnihilation(v: V): Prop =
    vs.scale(vs.zero, v) === vs.empty

  def vectorZeroAnnihilation(a: S): Prop =
    vs.scale(a, vs.empty) === vs.empty

end VectorSpaceLaws

object VectorSpaceLaws:

  def apply[V, S](using vs0: VectorSpace[V, S], eqV0: Eq[V], eqS0: Eq[S]): VectorSpaceLaws[V, S] =
    new VectorSpaceLaws[V, S]:
      given vs: VectorSpace[V, S] = vs0

      given S: CommutativeGroup[V] = vs0

      given eqV: Eq[V] = eqV0

      given eqS: Eq[S] = eqS0

end VectorSpaceLaws