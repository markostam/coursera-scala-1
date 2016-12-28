
object rationals{

}

val x = new Rational(1,2)
val y = new Rational(5,7)
val z = new Rational(3,2)
x - new Rational(2,3)
x - x - z
y + y
y - y
new Rational(234534) + new Rational(2,23423)
new Rational(1,2) < (new Rational(2,3))
new Rational(25,125)

class Rational (x: Int, y: Int) {
  require(y > 0, "denominator must be positive")
  // secondary constructor, only give nominator
  // assumes denomitor to be 1
  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int) : Int =
    if (b == 0) a else gcd(b, a % b)

  def numer : Int = x
  def denom : Int = y

  def < (that: Rational) =
    numer* that.denom < that.numer * denom
    // can also be written
    // this.numer* that.denom < that.numer * this.denom

  def max (that: Rational) =
    if (this < that) that else this

  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  override def toString = {
    lazy val g = gcd(x,y)
    numer / g + "/" + denom / g
  }

  // to use negative sign as a prefix, we need to give scala
  // a special "unary_" flag to tell it the func is not infix
  // DON'T FORGET that you need a space between the prefix and
  // the : or it will assume the colon is part of func name
  def unary_- : Rational = new Rational(numer * -1, denom)
  // precedence is determined by the function's first character

  def - (that: Rational) : Rational = this + -that

}