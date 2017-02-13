# Week 1: Functions & Evaluation

+ "In this week, we'll learn the difference between functional imperative programming. We step through the basics of Scala; covering expressions, evaluation, conditionals, functions, and recursion."
+ The following notes are from the [scala cheat sheet](https://github.com/lampepfl/progfun-wiki/blob/gh-pages/CheatSheet.md):

## Evaluation Rules

+ Call by value: evaluates the function arguments before calling the function
+ Call by name: evaluates the function first, and then evaluates the arguments if need be

```scala

def example = 2      // evaluated when called
val example = 2      // evaluated immediately
lazy val example = 2 // evaluated once when needed

def square(x: Double)    // call by value
def square(x: => Double) // call by name
def myFct(bindings: Int*) = { ... } // bindings is a sequence of int, containing a varying # of arguments

```

