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
