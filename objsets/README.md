# Data and Abstraction

## Class Heirarchies

+ **```abstract class```es** can contain members which are missing a definition while non abstract classes cannot
  + functions in the subclasses of the abstract class must *implement* the undefined functions in the superclass
  + it is also possible to *redefine* a non-abstract definition in a sublass by using ```override def```
+ **Persistent data structures:** When we make changes to a data structure such as a binary tree, we don't mutate it. We simply add another tree in parallel to the one that already existed with the additional data that we wanted to add. Thus the original tree stays put or *persists*.
+ **Scala Classes**: any user-defined class extends another class. 
  + If no superclass is given, the standard class ```Object``` in the Java package ```java.lang``` is assumed.
  + the direct or indirect superclasses of a class C are called *base classes*
    + base classes of ```NonEmpty``` in IntSets worksheet are ```IntSet``` and ```Object```
+ **Objects:** When creating a class that will only be used once, we use an ```object``` rather than a ```class``. This creates a *singleton object.* 
  + for an ```object``` we don't need to use ```new``` when instantiating it, just the name of the object itself e.g. ```val a = Obj``` instead of ```val a = new Obj```
+ **Standalone Scala Programs:** It's possible to create standalone scala apps using ```object```'s and the ```main``` function which takes an array of strings ```args: Array[String]``` as its arguments.

## How Classes Are Organized
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
+ The only thing you can have in a ```class``` that you can't in a ```trait``` and that is *value parameters* e.g. ```Rational(numerator: Int, denominator: Int)```
+ Scala Class Heirarchy: 
![Scala Class Heirarchy](https://github.com/markostam/coursera-scala-1/blob/master/objsets/img/scala_class_heirarchy.png)
  
