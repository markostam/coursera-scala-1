# Week 4: Types and Pattern Matching

"This week we'll learn about the relationship between functions and objects in Scala; functions *are* objects! We'll zoom in on Scala's type system, covering subtyping and generics, and moving on to more advanced aspects of Scala's type system like variance. Finally, we'll cover Scala's most widely used data structure, Lists, and one of Scala's most powerful tools, pattern matching."

## Pure Object Orientation

###Objects Everywhere

+ a pure OO language is one where every value is an objet. if language is based on classes, each val is a class.
+ at first glance, Scala has some exceptions to pure object orientation (primitive types, functions) but lets look closer...
+ conceptually, ```Int``` or ```Boolean``` don't get special treatment. They are just like other classes defined in package ```Scala```. 
  + (the compiler eventually represents ```scala.Int``` by 32-bit integers and ```scala.Boolean``` by Java Booleans but this is just for efficiency).
  + but we *could* define a Boolean, Integer, or any other primitive from first principles.
+ we write an [abstract class] defining natural numbers and some of their mathematical functions (```zero```,```predecessor```, ```successor```, ```+```, ```-```) using [Peano axioms](https://en.wikipedia.org/wiki/Peano_axioms) from first principles
+ recursive formulation of Peano ```+``` below using recursive successor function ```S``` and a definition of ```0```: 

![](https://wikimedia.org/api/rest_v1/media/math/render/svg/80857d5980826ae352be5a7cd8eb9cb70bdf5843) 

![](https://wikimedia.org/api/rest_v1/media/math/render/svg/8df6ed7e94da6b405e499cd8219faa8ec6f14f68)

## Functions as Objects

+ functions/values are objects in scala as well
+ function type ```A => B``` is an abbreviation for scala.Function[A,B], which is roughly defined as 

```scala
package scala
trait Function1[A,B] {
    def apply(x: A): B
}
```

+ thus *functions are objects with ```apply``` methods*
+ there are currently traits for functions up to 22 parameters

### Anonymous Function Expansion
+ anonymous function ```(x: Int) => x*x``` is expanded to 

```scala
new Function1[Int,Int] {
    def apply(x: Int) = x * x
}
```

+ function call ```f(a,b)``` expands to ```f.apply(a,b)```
+ this parallels idea of *eta-expansion* in the lambda calculus
+ using the idea of functions as objects we can expand a function to take varying numbers of args i.e.

```scala
object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T]()                      = new Nil
}
```

## Subtyping and Generics

+ two principle types of polymorphism:
  + subtyping - associated with OO programming
    + we pass instances of a subtype where a base type is required
  + generics - came from functional programming
    + parametrize types with other types
+ in this session we will look at their interactions. Two main areas:
  + bounds: subject type parameters to subtype constrains
  + variance: finds how paramaterized types behave under subtyping

### Type Bounds

+ '''def assertAllPos[S <: IntSet](r: S) : S
  + ```S``` a subtype of ```intSet```, ```r``` is a variable that is of type ```S``` the subtype, and we return the same type ```S``` 
+ Generally the notation:
  + ```S <: T``` means ```S``` *is a subtype of* ```T```, and
  + ```S >: T``` means ```S``` *is a supertype of* ```T```, or ```T```*is a subtype of* ```S```
  + you can bind a variable from both above and below in a mixed bound

### Covariance

+ Given ```NonEmpty <: IntSet```, is ```List[NonEmpty] <: List[IntSet]```?
+ We call types for which this relationship holds ***covariant*** because their subtyping relationship varies with the type parameter
+ Should all parameterized types be covariant? 
  + **TLDR: No**
    + *Lists are covariant* - roughly because they are immutable
    + *Arrays are not covariant* - roughly because they are mutable

+ Liskov Substitution Principle (Barbara Liskov) tells when a type can be a subtype of another:
  + * If ```A <: B``` then everything one ca do with a value of type ```b``` one should also be able to do with a value of type ```A```
  
## Variance

+ There are actually three possible relationships WRT variance:
  + ```C[A] <: C[B]```    ```C``` is *covariant*
  + ```C[A] >: C[B]```    ```C``` is *contravariant*
  + neither ```C[A]``` nor ```C[B]``` is a subtype of the other    ```C``` is *nonvariant*
+ Scala lets you declare the variance of a type by annontating the type parameter
  + ```C[+A] { ... }``` is *covariant*
  + ```C[-A] { ... }``` is *contravariant*
  + ```C[A] { ... }``` is *nonvariant*

### Variance Checks
+ The Scala compiler will check that there are no problematic combinations when compiling a class with variance annotations:
  + *covariant* type parameters can only appear in method results
  + *contravariant* type parameters can only appear in method parameters
  + *invariant* type parameters can appear anywhere

### Function trait declaration
+ Functions are contravariant in their arg types and covariant in their result type
  
```scala
package scala
trait Function`[-T, +U] {
  def apply(x: T): U 
}
```

### Variance and Lists
+ we give an example of how defining variance can be useful by looking back at the [```IntList```](https://github.com/markostam/coursera-scala-1/blob/master/Week3-objsets/src/main/scala/IntList.sc) worksheet we defined back in [Week 3](https://github.com/markostam/coursera-scala-1/tree/master/Week3-objsets)
  + in our original implementation we defined ```Nil``` as a Class, whereas we would prefer to define it as an Object, since there should only be one ```Nil``` theoretically.
  + we can change this by making the ```List``` trait covariant
  + when we make ```Nil``` an Object, we remove it's params since Objects take no params. Thus we have to change its definition from 
    + ```class Nil[T] extends List[T] { ```
      + to 
    + ```object Nil extends List[Nothing] {```
  + we give it ```Nothing``` as a parameter since ```Nothing``` is a subtype of all classes in Scala
  + then, if we define our ```List``` trait as *covariant* by doing ```trait List[+T]{```, we can give ```List``` any class which is a supertype of ```Nothing```, which by definition is all classes.
