package helios
package laws

import math.VectorSpace

import algebra.laws.RingLaws
import algebra.ring.CommutativeRing
import cats.kernel.laws.discipline.CommutativeGroupTests
import cats.kernel.{CommutativeGroup, Eq}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Prop}
import org.typelevel.discipline.Laws

/**
 * Discipline tests for VectorSpace laws
 *
 * Includes tests for:
 * - CommutativeGroup[V] laws (vector addition structure)
 * - CommutativeRing[S] laws (scalar field structure)
 * - VectorSpace specific laws (scalar multiplication interaction)
 */
trait VectorSpaceTests[V, S] extends CommutativeGroupTests[V] with RingLaws[S]:

  def laws: VectorSpaceLaws[V, S]

  def vectorSpace(using
                  vs: VectorSpace[V, S],
                  eqV: Eq[V],
                  eqS: Eq[S],
                  arbV: Arbitrary[V],
                  arbS: Arbitrary[S]
                 ): RuleSet =
    new RuleSet {

      override def name: String = "vectorSpace"

      override def bases: Seq[(String, Laws#RuleSet)] = Nil

      override def parents: Seq[RuleSet] = Seq(commutativeGroup, field)

      override def props: Seq[(String, Prop)] = Seq(
        "scalar multiplication compatibility" -> forAll(laws.scalarMultiplicationCompatibility _),
        "scalar multiplication identity" -> forAll(laws.scalarMultiplicationIdentity _),
        "scalar multiplication distributivity over vector addition" -> forAll(laws.scalarMultiplicationDistributivityOverVectorAddition _),
        "scalar multiplication distributivity over scalar addition" -> forAll(laws.scalarMultiplicationDistributivityOverScalarAddition _),
        "scalar zero annihilation" -> forAll(laws.scalarZeroAnnihilation _),
        "vector zero annihilation" -> forAll(laws.vectorZeroAnnihilation _)
      )
    }


end VectorSpaceTests

object VectorSpaceTests:

  def apply[V, S](using VectorSpace[V, S], Eq[V], Eq[S]): VectorSpaceTests[V, S] =
    new VectorSpaceTests[V, S]:
      def laws: VectorSpaceLaws[V, S] = VectorSpaceLaws[V, S]

end VectorSpaceTests