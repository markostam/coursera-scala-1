package idealized.scala

abstract class Boolean {
  // if (cond) t else e == cond.ifThenElse(t,e)
  def ifThenElse[T](t: => T, e: => T): T

  // & : if condition is true, we return whatever the other val is
  // else false
  def & (x: => Boolean) : Boolean  = ifThenElse(x, falseI)
  // | : if first condition is true, whole expression must be true
  // else output depends on 2nd input
  def | (x: => Boolean) : Boolean  = ifThenElse(trueI, x)
  // opposite of test condition basically
  def unary_! : Boolean            = ifThenElse(falseI, trueI)

  def == (x: => Boolean) : Boolean = ifThenElse(x, x.unary_!)
  def != (x: => Boolean) : Boolean = ifThenElse(x.unary_!, x)

  def < (x: => Boolean) : Boolean  = ifThenElse(falseI, x)

}

object trueI extends Boolean {
  // if true we just return first arg
  def ifThenElse[T](t: => T, e: => T) = t
}

object falseI extends Boolean {
  // if false we just return the 2nd
  def ifThenElse[T](t: => T, e: => T) = t
}