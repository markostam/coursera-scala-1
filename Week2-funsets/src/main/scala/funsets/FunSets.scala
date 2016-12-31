package funsets

/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
    def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): Set = i => i == elem

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set = i => s(i) | t(i)
  
  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set = i => s(i) & t(i)
  
  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set = i => s(i) & !t(i)
  
  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set = i => p(i) & s(i)

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) & !p(a)) false
      else iter(a + 1)
    }
    iter(-bound)
  }
  
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */

    def exists(s: Set, p: Int => Boolean): Boolean = {
      !forall(s, i => !p(i))
    }

    // to work out how to write exists using forall, first
    // write exists as you wrote forall and examine what parts
    // ned to change to get required outputs
    def existsPiecewise(s: Set, p: Int => Boolean): Boolean = {
      def iter(a: Int): Boolean = {
        if (a > bound) false
        else if (s(a) & p(a)) true
        else iter(a + 1)
      }
      iter(-bound)
    }
  
  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: Set, f: Int => Int): Set = {
      val emptySet : Set = i => false
      def iter(a: Int, ns: Set, s: Set): Set = {
        if (a > bound) ns
        else if (s(a) & !ns(f(a))) {
          iter(a + 1, union(ns, singletonSet(f(a))), s)
        }
        else iter(a + 1, ns, s)
      }
      iter(-bound, emptySet, s)
    }
  
  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
