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
