package helios
package laws

import scala.annotation.nowarn

import algebra.ring.AdditiveMonoid
import cats.kernel.Eq
import helios.math.LieAlgebra
import org.scalacheck.Arbitrary
import org.scalacheck.Prop
import org.typelevel.discipline.Laws
import org.typelevel.discipline.Predicate

/** Discipline tests for LieAlgebra laws
  *
  * Parents:
  *   - VectorSpace[V, S] laws (via VectorSpaceTests)
  */
trait LieAlgebraTests[V, S] extends VectorSpaceTests[V, S]:

  def laLaws: LieAlgebraLaws[V, S]

  def lieAlgebra(using
      la: LieAlgebra[V, S],
      eqV: Eq[V],
      arbV: Arbitrary[V],
      arbS: Arbitrary[S]
  ): RuleSet =
    new RuleSet {
      override def name: String = "lieAlgebra"

      override def bases: Seq[(String, Laws#RuleSet)] = Nil

      override def parents: Seq[RuleSet] = Seq(vectorSpace)

      override def props: Seq[(String, Prop)] = Seq(
        "alternating"            -> org.scalacheck.Prop.forAll((v: V) => laLaws.alternating(v)),
        "skew-symmetry"          -> org.scalacheck.Prop.forAll((u: V, v: V) => laLaws.skewSymmetry(u, v)),
        "jacobi"                 -> org.scalacheck.Prop.forAll((u: V, v: V, w: V) => laLaws.jacobi(u, v, w)),
        "left linearity scalar"  -> org.scalacheck.Prop.forAll((a: S, u: V, v: V) =>
          laLaws.leftLinearityScalar(a, u, v)
        ),
        "left linearity add"     -> org.scalacheck.Prop.forAll((u: V, w: V, v: V) => laLaws.leftLinearityAdd(u, w, v)),
        "right linearity scalar" -> org.scalacheck.Prop.forAll((a: S, u: V, v: V) =>
          laLaws.rightLinearityScalar(a, u, v)
        ),
        "right linearity add"    -> org.scalacheck.Prop.forAll((u: V, v: V, w: V) => laLaws.rightLinearityAdd(u, v, w))
      )
    }

end LieAlgebraTests

object LieAlgebraTests:

  def apply[V: Eq, S](using
      LieAlgebra[V, S]
  )(using arb: Arbitrary[S]): LieAlgebraTests[V, S] =
    new LieAlgebraTests[V, S]:
      def laws: VectorSpaceLaws[V, S]  = VectorSpaceLaws[V, S]
      def laLaws: LieAlgebraLaws[V, S] = LieAlgebraLaws[V, S]

      def Arb: Arbitrary[S] = arb

      def pred: Predicate[S] = (a: S) => Eq[S].neqv(a, AdditiveMonoid[S].zero)

      @nowarn("msg=deprecated")
      val nonZeroLaws: algebra.laws.GroupLaws[S] = new algebra.laws.GroupLaws[S] {
        def Arb: Arbitrary[S] = Arbitrary(Arbitrary.arbitrary[S](arb).filter(pred))

        def Equ: Eq[S] = Eq[S]
      }

end LieAlgebraTests
