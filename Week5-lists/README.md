# List Utility Methods

## List Methods

### Sublists and element access

+ xs.length = returns number of elements
+ xs.last = list's last element, exception if empty
+ xs.init = new list that drops last element of xs (dual of last)
+ xs.take(n) = first n elemns of xs, or just xs if it's shorter than n
+ xs.drop(n) = rest of the list after n elements (dual of take)
+ xs(n) = (or xs.apply(n)) elemnt of xs at index n

### Creating new lists

+ xs ++ ys = concatenate xs and ys
+ xs.reverse = reverse order
+ xs.updated(n,x) = list containing same elements as xs, but replace element with index n with x

### Finding elements

+ xs.indexOf(x) = the index of the first elemnt in xs equal to x, or -1 if x doesn't appear
+ xs.contains(x) = boolean indicating whether element appears in list

### Implementation of Concatenation

```scala

def concat[T[(xs: List[T], ys: List[T]) = xs {
    case Nil      => ys
    case z :: zs  => z :: concat(zs, ys)
    }

```

### Implementatin of Reverse

```scala

def reverse[T](xs: List[T]) = xs match {
    case Nil     => xs
    case y :: ys => reverse(ys) ::: List(y)
}


```

### Implementation of removeAt

```scala

def removeAt[T](xs: List[T], n: Int) = (xs take n) ::: (xs drop n+1)

```

## Pairs and Tupes

### Sorting lists faster

+ we'll use *merge sort* (faster than insertion sort)
  + if list contains 1 or less elements, it's already sorted
  + else
    + separate list into two sub lists, each containing ~half elemtns of original list
    + sort the two sublists
    + merge the two sorted sublists into a single sorted list

```scala
  
def msort(xs: List[Int]): List[Int] = {
    val n = xs.length/2

    def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) => if (x < y) x :: merge(xs1,ys) else y :: merge(xs, ys1)
    }

    if (n==0) xs
    else {
        val (fst,snd) = xs.splitAt(n)
        merge(msort(fst),msort(snd))
    }
}

```

## Parametrization 

### making msort more general

```scala
  
def msort[T](xs: List[T])(lt: (T,T) => Boolean): List[T] = {
    val n = xs.length/2

    def merge[T](xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) => if (lt(x,y)) x :: merge(xs1,ys) else y :: merge(xs, ys1)
    }

    if (n==0) xs
    else {
        val (fst,snd) = xs.splitAt(n)
        merge(msort(fst)(lt),msort(snd)(lt))
    }
}

```

+ note: you can also use built in scala math.Ordering.[T](x,y) function instead of writing your own ordering function ```lt```

### using an implicit for the ordering function

```scala
 
 // TODO: this is code from the lecture but doesn't compile, why? 
def msort[T](xs: List[T])(implmicit ord: math.Ordering[T]): List[T] = {
    val n = xs.length/2

    def merge[T](xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) => if (ord.lt(x,y)) x :: merge(xs1,ys) else y :: merge(xs, ys1)
    }

    if (n==0) xs
    else {
        val (fst,snd) = xs.splitAt(n)
        merge(msort(fst),msort(snd))
    }
}

```

### Rules for implicit parameters

+ if a function takes an implicit of type ```T```:
  + the compiler will search for a def that:
    + is marked ```implicit```
    + has a type compatible with ```T```
    + is visible at the point of the function call, or di defined in a companion object associated with ```T```

+ if there is a single (most specific) definition, it will be taken as actual arg for the implicit
+ else its an error

## Higher order List functions

### Map

```scala

abstract class List[T] { ...
	def map[U](f: T => U): List(U) = this match {
		case Nil     => this
		case x :: xs => f(x) :: xs.map(f)
	}
}

```

+ In reality the scala map function is more complicated
  1. because it's tail recursive
  2. becuase it works for arbirtrary collections, not just lists
+ but this is good enough for an understanding of how it works

### Filter

 ```scala

abstract class List[T] { ...
	def filter(p: T => Boolean): List(U) = this match {
		case Nil     => this
		case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
	}
}

```
	}
}

### Variations of Filter

+ ```xs.filterNot(p)``` = Same as ```xs.filter(x => !p(x))```
+ ```xs.partition(p)``` = Same as ```(xs.filter(p(x)), xs.filterNot(p(x))```, but computed in one traversal of the list
+ ```xs.takeWhile(p)``` = longest prefix of list ```xs``` consisting of elements that satisfy predicate ```p```
+ ```xs.dropWhile(p)``` = remainder of list ```xs``` after any leading elemnts satisfying ```p``` have beeen removed
+ ```xs.span(p)```      = Same as ```(xs.takeWhile(p(x)), xs.dropWhile(p(x))```, but computed in one traversal of the list

## Fold / Reduce Combinators

### foldLeft

```(List(x1...xn).foldLeft(z)(op)) = (...(z op x1)... op ...) op xn

```
            op
           /  \
         op   xn
        /  \ 
      op   x2
     /  \
    z   x1
```

### Reduce

+ reduce is just a special case of foldLeft defined for specific types

### Implementations of reduceLeft and foldLeft

```scala

abstract class List[T] { ...
	def reduceLeft(op: (T,T) => T): T = this match {
		case Nil     => throw new Error("Nil.reduceLeft")
		case x :: xs => (xs.foldLeft(x))(op)
	}

	def foldLeft[U](z: U)(op: (U,T) => U) : U = this match {
		case Nil     => z
		case x :: xs => (xs.foldLeft(op(z,x))(op))
	}
}

```

### Implementations of foldRight and reduceRight

+ duals of foldLeft and reduceLeft
+ Implementation is pretty much the same except they start at the end of the list by doing 

```scala

case x :: xs => op(x, (xs.foldRight(z)(op)))
``` 

for the last line of the fold function instead of 

```scala

case x :: xs => (xs.foldLeft(op(z,x))(op))
```
