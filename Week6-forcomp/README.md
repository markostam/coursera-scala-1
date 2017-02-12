# More Data Structures

## Other Sequences

### Vectors

+ A vector is a trie of 32-d Arrays
  + if it's less than 32 in length, it's just an Array
  + 6 levels will give you 32^6 elements, and that's about as high as it can go
  + access time is log32(N) where N is size of vector

+ Pretty good for bulk operations -> maps or folds
  + you do this in chunks of 32, which corresponds well to a cache line
  + locality for vector accesses are fast
+ * When to use Vectors vs Lists? *
  + if you have bulk operations such as ```map``` ```fold``` or ```filter``` vectors are better
  + if you have recursive structure when you are looking at the head or tail, Lists are better
+ * Vectors don't have ```::``` operations instead they have *
  + ```+:``` which adds elemnt to left of Vector
  + ```:+``` which adds elemenf to Right of Vector
+ In order to append a value to a Vector, you have to create a new 32 elemnt array for each level in the tree
  + this is because you are operating on an immutable tree structure 

### Collection Heirarchy

+ Common base class of ```List``` and ```Vector``` is ```Seq```, the class of all Sequences
+ ```Seq``` itself is a sublclass of ```Iterable```
+ ```Array``` and ```String``` can also implicitly be converted to a ```Seq```

### Ranges

+ Sequence of evenly spaced integers

```scala

val r: Range = 1 until 5 //1,2,3,4
val r: Range = 1 to 5 // 1,2,3,4,5
1 to 10 by 3 // 1,4,7,10
6 to 1 by -2 // 6,4,2

```

### More Sequence operations

+ ```xs.exists(p)``` = boolean indicating whether p(x) is true for ANY values in xs
+ ```xs.forall(p)``` = boolean indicating whether p(x) is true for ALL values in xs
+ ```xs.zip(ys)``` = Sequence of tuples drawn from corresponding elements of xs and ys
+ ```xs.unip``` = dual of ```zip```, returns tuple of two lists drawn from the respective first and second half of the tupes in list xs
+ ```xs.flatMap(f)``` = applies ```f``` to all elements of ```xs``` and concatenates results

## For-expressions

``` for ( p <- persion if p.age > 20 ) yield p.name ```
equivalent to
``` persions.filter(_.age > 20).map(_.name)```

### multiline for uses braces instead of parentheses

```scala

for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i+j)
    } yield (i,j)

```

## Sets

+ set *** don't keep duplicates***, and they are ***unordered***
+ can use most sequence operations like ```map``` and ```filter```
