# Week 4: Types and Pattern Matching

"This week we'll learn about the relationship between functions and objects in Scala; functions *are* objects! We'll zoom in on Scala's type system, covering subtyping and generics, and moving on to more advanced aspects of Scala's type system like variance. Finally, we'll cover Scala's most widely used data structure, Lists, and one of Scala's most powerful tools, pattern matching."

## Pure Object Orientation

+ a pure OO language is one where every value is an objet. if language is based on classes, each val is a class.
+ at first glance, Scala has some exceptions to pure object orientation (primitive types, functions) but lets look closer...
+ conceptually, ```Int``` or ```Boolean``` don't get special treatment. They are just like other classes defined in package ```Scala```. 
  + (the compiler eventually represents ```scala.Int``` by 32-bit integers and ```scala.Boolean``` by Java Booleans but this is just for efficiency).
  + but we *could* define a Boolean, Integer, or any other primitive from first principles.
+ we write an [abstract class] defining natural numbers and some of their mathematical functinos (```zero```,```predecessor```, ```successor```, ```+```, ```-```) using [Peano axioms](https://en.wikipedia.org/wiki/Peano_axioms) from first principles
