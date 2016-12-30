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
  wil place the ```Hello``` object into the ```progfun.examples``` package.
