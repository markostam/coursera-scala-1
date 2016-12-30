import java.util.NoSuchElementException

/* the cons val in parameters is equivalent to:
class Cons(_head: Int, _tail: IntList) extends IntList {
  val head = _head
  val tail = _tail
}
where _head and _tail are otherwise unused names
 */

trait List[T]{
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

// cons two
class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  def isEmpty = false
  override def toString = head + " :: " + tail
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil has no head")
  def tail: Nothing = throw new NoSuchElementException("Nil has no tail")
  override def toString = "Nil"
}

// creates singleton List element
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

// returns nth element of a list
def nth[T](n: Int, xs: List[T]) : T = {
  if (xs.isEmpty) throw new IndexOutOfBoundsException
  if (n == 0) xs.head
  else nth(n - 1, xs.tail)
}

val a = new Cons(1, singleton(3))

nth(1,a)


