package helios
package laws

import scala.annotation.nowarn

import algebra.Eq
import algebra.laws.GroupLaws
import algebra.laws.RingLaws
import algebra.ring.AdditiveMonoid
import cats.kernel.laws.discipline.CommutativeGroupTests
import helios.math.VectorSpace
import org.scalacheck.Arbitrary
import org.scalacheck.Prop
import org.scalacheck.Prop.forAll
import org.typelevel.discipline.Laws
import org.typelevel.discipline.Predicate

/** Discipline tests for VectorSpace laws
  *
  * Includes tests for:
  *   - CommutativeGroup[V] laws (vector addition structure)
  *   - CommutativeRing[S] laws (scalar field structure)
  *   - VectorSpace specific laws (scalar multiplication interaction)
  */
trait VectorSpaceTests[V, S] extends CommutativeGroupTests[V] with RingLaws[S]:

  def laws: VectorSpaceLaws[V, S]

  val scalarField: RingLaws[S]              = this
  val vectorGroup: CommutativeGroupTests[V] = this

  def vectorSpace(using
      vs: VectorSpace[V, S],
      eqV: Eq[V],
      eqS: Eq[S],
      arbV: Arbitrary[V],
      arbS: Arbitrary[S]
  ): RuleSet =
    new RuleSet {

      override def name: String = "vectorSpace"

      override def bases: Seq[(String, Laws#RuleSet)] = Seq(
        "scalarField" -> field,
        "vectorGroup" -> vectorGroup.commutativeGroup
      )

      override def parents: Seq[RuleSet] = Nil

      override def props: Seq[(String, Prop)] = Seq(
        "scalar multiplication compatibility"                       -> forAll(laws.scalarMultiplicationCompatibility _),
        "scalar multiplication identity"                            -> forAll(laws.scalarMultiplicationIdentity _),
        "scalar multiplication distributivity over vector addition" -> forAll(
          laws.scalarMultiplicationDistributivityOverVectorAddition _
        ),
        "scalar multiplication distributivity over scalar addition" -> forAll(
          laws.scalarMultiplicationDistributivityOverScalarAddition _
        ),
        "scalar zero annihilation"                                  -> forAll(laws.scalarZeroAnnihilation _),
        "vector zero annihilation"                                  -> forAll(laws.vectorZeroAnnihilation _)
      )
    }

end VectorSpaceTests

object VectorSpaceTests:

  def apply[V: Eq, S](using VectorSpace[V, S])(using arb: Arbitrary[S], eqS: Eq[S]): VectorSpaceTests[V, S] =
    new VectorSpaceTests[V, S]:

      def laws: VectorSpaceLaws[V, S] = VectorSpaceLaws[V, S]

      def Arb: Arbitrary[S] = arb

      def pred: Predicate[S] = (a: S) => Eq[S].neqv(a, AdditiveMonoid[S].zero)

      @nowarn("msg=deprecated")
      val nonZeroLaws: GroupLaws[S] = new GroupLaws[S] {
        def Arb: Arbitrary[S] = Arbitrary(Arbitrary.arbitrary[S](arb).filter(pred))

        def Equ: Eq[S] = eqS
      }

    end new

end VectorSpaceTests
