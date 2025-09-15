package helios
package laws

import cats.kernel.Eq
import helios.math.HilbertLieAlgebra
import helios.math.InnerProductSpace
import helios.math.LieAlgebra
import org.scalacheck.Arbitrary
import org.scalacheck.Prop
import org.typelevel.discipline.Laws
import org.typelevel.discipline.Predicate
import org.typelevel.scalaccompat.annotation.nowarn

/** Discipline tests for HilbertLieAlgebra laws
  *
  * Parents:
  *   - InnerProductSpace[V, S] laws (via InnerProductSpaceTests)
  *   - LieAlgebra[V, S] laws (via LieAlgebraTests)
  */
trait HilbertLieAlgebraTests[V, S] extends InnerProductSpaceTests[V, S] with LieAlgebraTests[V, S]:

  def hlaLaws: HilbertLieAlgebraLaws[V, S]

  def hilbertLieAlgebra(using
      hla: HilbertLieAlgebra[V, S],
      eqV: Eq[V],
      eqS: Eq[S],
      arbV: Arbitrary[V],
      arbS: Arbitrary[S]
  ): RuleSet =
    new RuleSet {
      override def name: String = "hilbertLieAlgebra"

      override def bases: Seq[(String, Laws#RuleSet)] = Seq(
        "scalarField" -> field,
        "vectorGroup" -> vectorGroup.commutativeGroup
      )

      override def parents: Seq[RuleSet] = Seq(innerProductSpace, lieAlgebra)

      override def props: Seq[(String, Prop)] = Nil
    }

end HilbertLieAlgebraTests

object HilbertLieAlgebraTests:

  def apply[V: Eq, S](using
      HilbertLieAlgebra[V, S],
      InnerProductSpace[V, S],
      LieAlgebra[V, S]
  )(using arb: Arbitrary[S], eqS: Eq[S]): HilbertLieAlgebraTests[V, S] =
    new HilbertLieAlgebraTests[V, S]:
      def hlaLaws: HilbertLieAlgebraLaws[V, S] = HilbertLieAlgebraLaws[V, S]
      def ipLaws: InnerProductSpaceLaws[V, S]  = InnerProductSpaceLaws[V, S]
      def laLaws: LieAlgebraLaws[V, S]         = LieAlgebraLaws[V, S]
      def laws: VectorSpaceLaws[V, S]          = VectorSpaceLaws[V, S]

      def Arb: Arbitrary[S] = arb

      import algebra.ring.AdditiveMonoid

      def pred: Predicate[S] = (a: S) => Eq[S].neqv(a, AdditiveMonoid[S].zero)

      @nowarn("msg=deprecated")
      val nonZeroLaws: algebra.laws.GroupLaws[S] = new algebra.laws.GroupLaws[S] {
        def Arb: Arbitrary[S]        = Arbitrary(Arbitrary.arbitrary[S](arb).filter(pred))
        def Equ: Eq[S] = eqS
      }

    end new

end HilbertLieAlgebraTests
