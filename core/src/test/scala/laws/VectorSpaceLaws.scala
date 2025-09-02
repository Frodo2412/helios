//package helios
//package laws
//
//import algebra.ring.CommutativeRing
//import cats.kernel.{CommutativeGroup, Eq}
//import cats.syntax.all.*
//import helios.math.VectorSpace
//import org.scalacheck.Prop
//import org.scalacheck.Prop.*
//
///**
// * Laws for VectorSpace type class
// *
// * Note: Vector addition laws are inherited from CommutativeGroup[V]
// * and scalar arithmetic laws are inherited from CommutativeRing[S].
// * We only need to test the vector space specific laws that relate
// * scalar multiplication to the vector and scalar structures.
// */
//trait VectorSpaceLaws[V, S]:
//
//  given vs: VectorSpace[V, S]
//  given eqV: Eq[V]
//  given eqS: Eq[S]
//
//  import VectorSpace.*
//
//  // Vector space specific laws (scalar multiplication laws)
//  def scalarMultiplicationCompatibility(a: S, b: S, v: V): Prop =
//    (a * (b * v)) === (vs.times(a, b) * v)
//
//  def scalarMultiplicationIdentity(v: V): Prop =
//    (vs.one *: v) === v
//
//  def scalarMultiplicationDistributivityOverVectorAddition(a: S, u: V, v: V): Prop =
//    (a *: (u + v)) === ((a *: u) + (a *: v))
//
//  def scalarMultiplicationDistributivityOverScalarAddition(a: S, b: S, v: V): Prop =
//    ((vs.plus(a, b)) *: v) === ((a *: v) + (b *: v))
//
//  def scalarZeroAnnihilation(v: V): Prop =
//    (vs.zero *: v) === vs.empty
//
//  def vectorZeroAnnihilation(a: S): Prop =
//    (a *: vs.empty) === vs.empty
//
//end VectorSpaceLaws
//
//object VectorSpaceLaws:
//
//  def apply[V, S](using vs0: VectorSpace[V, S], eqV0: Eq[V], eqS0: Eq[S]): VectorSpaceLaws[V, S] =
//    new VectorSpaceLaws[V, S]:
//      given vs: VectorSpace[V, S] = vs0
//      given eqV: Eq[V] = eqV0
//      given eqS: Eq[S] = eqS0
//
//end VectorSpaceLaws