# Data and Abstraction

### Week 3

This week, we'll cover traits, and we'll learn how to organize classes into hierarchies. We'll cover the hierarchy of standard Scala types, and see how to organize classes and traits into packages. Finally, we'll touch upon the different sorts of polymorphism in Scala.

## Class Heirarchies

### Abstract Classes
+ ```abstract class``` can contain members which are missing a definition while non abstract classes cannot
  + functions in the subclasses of the abstract class must *implement* the undefined functions in the superclass
  + it is also possible to *redefine* a non-abstract definition in a sublass by using ```override def```

### Persistent Data Structures
+ When we make changes to a data structure such as a binary tree, we don't mutate it. We simply add another tree in parallel to the one that already existed with the additional data that we wanted to add. Thus the original tree stays put or *persists*.
+ We implement an immutable binary tree in [IntSets.sc](https://github.com/markostam/coursera-scala-1/blob/master/objsets/src/main/scala/IntSets.sc)

### Classes vs. Objects
+ **Scala Classes**: any user-defined class extends another class. 
  + If no superclass is given, the standard class ```Object``` in the Java package ```java.lang``` is assumed.
  + the direct or indirect superclasses of a class C are called *base classes*
    + base classes of ```NonEmpty``` in IntSets worksheet are ```IntSet``` and ```Object```
+ **Objects:** When creating a class that will only be used once, we use an ```object``` rather than a ```class``. This creates a *singleton object.* 
  + for an ```object``` we don't need to use ```new``` when instantiating it, just the name of the object itself e.g. ```val a = Obj``` instead of ```val a = new Obj```
+ **Standalone Scala Programs:** It's possible to create standalone scala apps using ```object```'s and the ```main``` function which takes an array of strings ```args: Array[String]``` as its arguments.

## How Classes Are Organized

### Packages
+ Classes and objects are organized in packages
+ to place a class or object inside a package, use a package clause at top of file
    ```scala
    package progfun.examples
    
    object Hello{...}
    ```
  will place the ```Hello``` object into the ```progfun.examples``` package.
+ you can then refer to ```Hello``` by it's *fully qualified name* ```progfun.examples.Hello```
+ for example to run it: ```$ scala progfun.examples.Hello```
+ then you can also import classes from the same package
+ Some entities are automatically imported in any scala prog
  + all members of package ```scala```
  + all members of ```java.lang```
  + all members of singleton object ```scala.Predef```
  + e.g. ```scala.Int```, ```scala.Boolean```, ```java.lang.Object```, ```scala.Predef.require```, ```scala.Predef.asser```, etc...
  + Standard Scala library at [www.scala-lang.org/api/current](www.scala-lang.org/api/current)
  
### Traits
+ in java as well as scala a class can only have one superclasss - *single inheritance*
+ if a class has several natural supertypes to which it wants to inherit code, you can use a ```trait```
+ A ```trait``` is declared like an abstract class just with ```trait``` instead of ```abstract class```
+ ```trait```s can extend unrelated classes arbitrarily
+ The only thing you can have in a ```class``` that you can't in a ```trait``` and that is *value parameters* e.g. ```Rational(numerator: Int, denominator: Int)``` from the [previous week](https://github.com/markostam/coursera-scala-1/blob/master/funsets/src/main/scala/funsets/rationals.sc)
+ Scala Class Heirarchy:
  + basically it forks numeric from non-numeric data types from superclass ```scala.Any``` to ```scala.AnyVal``` for numeric and ```scala.AnyRef``` for other.
  ![Scala Class Heirarchy](https://github.com/markostam/coursera-scala-1/blob/master/objsets/img/scala_class_heirarchy.png)
  + ```Any```: base type of all types
    + Methods: ```==```, ```!=```, ```equals```, ```hashCode```, ```toString```
  + ```AnyRef``` base type of all reference objects, Alias of ```java.lang.Object```
  + ```AnyVal``` the base of all primitive types
  + ```Nothing``` is at the bottom of the heirarcy. it's a subtype of every other type.
    + signals abnormal termination
    + element type of empty collections aka ```List()``` == ```List(Nothing)``` == ```Nil```
    + useful for error checking when you don't need to return anything
  + ```null``` is the subtype of all ```scala.ref``` types. Incompatible with ```AnyVal```

## Polymorphism
### Type paramaterization
+ classes as well as methods can have types as parameters
+ we explore this by implementing a classic functional data structure known as a cons-list in the [IntList.sc](https://github.com/markostam/coursera-scala-1/blob/master/objsets/src/main/scala/IntList.sc) worksheet
+ cons list = immutable linked list constructed from two building blocks
  + ```Nil``` the empty list
  + ```Cons``` a cell containing an element and the remainder of the list
  + e.g. ```List(1,2,3)``` = ```1 :: (2 :: (3 :: Nil))```

### Type Erasure
+ types are only relevent for the compiler, but not relevant for the execution of the program
+ type parameters do not affect evaluation in Scala
+ we can assume all type params and args are removed before evaluating program
+ other languages that do this include Java, Scala, Haskell, ML, OCaml
+ languages that don't include c++, c#, f#

### Polymorphism
+ in programming it means *a function can be applied to args of many types*
  + the type can have instances of many types
+ two principle forms:
  + *subtyping*: isntances of a subclass that can be passed to a base class
    + pass a ```list``` either a ```Nil``` or a ```Cons```
    + originally found in imperative languages
  + *generics*: instances of a function or class created by a type of parametrization.
    + creates a list of ints or list of doubles or list of list of booleans.
    + traditionally found in functional languages

