# Week 2: Higher Order Functions

+ "This week, we'll learn about functions as first-class values, and higher order functions. We'll also learn about Scala's syntax and how it's formally defined. Finally, we'll learn about methods, classes, and data abstraction through the design of a data structure for rational numbers."
+ The following notes are pulled from the [scala cheat sheet](https://github.com/lampepfl/progfun-wiki/blob/gh-pages/CheatSheet.md)

## Higher order functions

These are functions that take a function as a parameter or return functions.

```scala

    // sum() returns a function that takes two integers and returns an integer  
    def sum(f: Int => Int): (Int, Int) => Int = {  
      def sumf(a: Int, b: Int): Int = {...}  
      sumf  
    } 

    // same as above. Its type is (Int => Int) => (Int, Int) => Int  
    def sum(f: Int => Int)(a: Int, b: Int): Int = { ... } 

    // Called like this
    sum((x: Int) => x * x * x)          // Anonymous function, i.e. does not have a name  
    sum(x => x * x * x)                 // Same anonymous function with type inferred

    def cube(x: Int) = x * x * x  
    sum(x => x * x * x)(1, 10) // sum of cubes from 1 to 10
    sum(cube)(1, 10)           // same as above      
    
```

## Currying

Converting a function with multiple arguments into a function with a single argument that returns another function.

```scala

    def f(a: Int, b: Int): Int // uncurried version (type is (Int, Int) => Int)
    def f(a: Int)(b: Int): Int // curried version (type is Int => Int => Int)

```

