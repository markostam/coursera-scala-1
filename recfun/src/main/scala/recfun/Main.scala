package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      // tail recursive solution
      @annotation.tailrec
      def createRow(r: Int, cur: List[Int] = List(1)) : List[Int] = {
        // tail recursively creates the r'th row of pascal's triangle
        val paddedR = 0 +: cur :+ 0
        val nextR = paddedR.sliding(2).toList.map(_.sum)
        if (r-1 == 0) nextR 
        else {
          val n = r-1
          createRow(n,nextR)
        }
      }
      if (r == 0) 1 else createRow(r)(c)
    }


    def pascal(c: Int, r: Int): Int = {
      // naive recursive solution
      if (c == 0 | c == r) 1
      else{
        pascal(r-1,c-1) + pascal(r-1,c)
      }
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = chars.foldLeft(0){
      case (0, ')') => return false
      case (x, '(') => x + 1
      case (x, ')') => x - 1
      case (x,  _ ) => x
    } == 0

    def balance2(chars: List[Char]): Boolean = {
      def balancef(c: Int, chars2: List[Char]) : Int = {
        (c, chars2) match {
          case (0, ')'::_) => return 1
          case (x, '('::t) => balancef(x + 1, t)
          case (x, ')'::t) => balancef(x - 1, t)
          case (x,  _ ::t) => balancef(x, t)
          case (x, List()) => x
        }
      }
      balancef(0, chars) == 0
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

  }
