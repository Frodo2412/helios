package helios
package laws

import math.InnerProductSpace

import algebra.ring.AdditiveMonoid
import cats.kernel.Eq
import cats.kernel.laws.discipline.CommutativeGroupTests
import org.scalacheck.{Arbitrary, Prop}
import org.typelevel.discipline.{Laws, Predicate}

import scala.annotation.nowarn

/** Discipline tests for InnerProductSpace laws
  *
  * Parents:
  *   - CommutativeGroup[V] (vector addition)
  *   - Field[S] (scalar field) via RingLaws[S]
  */
trait InnerProductSpaceTests[V, S] extends VectorSpaceTests[V, S]:

  def ipLaws: InnerProductSpaceLaws[V, S]

  def innerProductSpace(using
      ips: InnerProductSpace[V, S],
      eqV: Eq[V],
      eqS: Eq[S],
      arbV: Arbitrary[V],
      arbS: Arbitrary[S]
  ): RuleSet =
    new RuleSet {
      override def name: String = "innerProductSpace"

      override def bases: Seq[(String, Laws#RuleSet)] = Nil

      override def parents: Seq[RuleSet] = Seq(
        vectorSpace
      )

      override def props: Seq[(String, Prop)] = Seq(
        "symmetry"           -> org.scalacheck.Prop.forAll((u: V, v: V) => ipLaws.symmetry(u, v)),
        "linearity (scalar)" -> org.scalacheck.Prop.forAll((a: S, u: V, v: V) => ipLaws.linearityScalar(a, u, v)),
        "linearity (add)"    -> org.scalacheck.Prop.forAll((u: V, w: V, v: V) => ipLaws.linearityAdd(u, w, v)),
        "zero inner zero"    -> ipLaws.zeroIsZero,
        "definiteness"       -> org.scalacheck.Prop.forAll((v: V) => ipLaws.definiteness(v))
      )
    }

end InnerProductSpaceTests

object InnerProductSpaceTests:

  def apply[V: Eq, S](using
      InnerProductSpace[V, S]
  )(using arb: Arbitrary[S], eqS: Eq[S]): InnerProductSpaceTests[V, S] =
    new InnerProductSpaceTests[V, S]:
      def ipLaws: InnerProductSpaceLaws[V, S] = InnerProductSpaceLaws[V, S]
      def laws: VectorSpaceLaws[V, S]         = VectorSpaceLaws[V, S]

      def Arb: Arbitrary[S] = arb

      def pred: Predicate[S] = (a: S) => Eq[S].neqv(a, AdditiveMonoid[S].zero)

      @nowarn("msg=deprecated")
      val nonZeroLaws: algebra.laws.GroupLaws[S] = new algebra.laws.GroupLaws[S] {
        def Arb        = Arbitrary(Arbitrary.arbitrary[S](arb).filter(pred))
        def Equ: Eq[S] = eqS
      }

    end new

end InnerProductSpaceTests
