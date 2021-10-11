# Lecture: Midterm Review

Topics

* Projects
    * Flood It: time complexity, Java collection classes
    * Segment Intersection: BST and AVL
* Arrays, array algorithms e.g. search, rotate, Java's ArrayList
* Linked lists, doubly linked lists, Java's LinkedList
* Abstract data types
    * interfaces
    * iterators
* Time complexity
* Binary Search Trees
* AVL Trees (balanced BSTs)

## Big-O, big-Ω, big-Θ

Def. f ∈ O(g) iff exists c k st. for all n >= k. f(n) <= c g(n)

Simplest example

        f(n) = n

        g(n) = n²

        f(n) in O(g(n))

        choose k = 0, c = 1

        for all n ≥ 0, n ≤ n² 

Example that requires a more interesting choice of k.

        f(n) = n + 100

        g(n) = n²

        n   | n + 100 | n²
        --- | ------- | ----
        1   |  101    | 1
        2   |  102    | 4
        10  |  110    | 100
        11  |  111    | 121

        choose k = 11, c = 1
        for all n ≥ 11, n + 100 ≤ n²

Example that requires a more interesting choice of c.

        f(n) = 3 n

        g(n) = n

        if we try c=1, no choice of k will work.

        choose c = 3, then any reasonable k will work

        for all n ≥ 0, 3n ≤ 3n.

big-Ω is the set of all functions that grow at least as
fast as the given one. For example,

        n² ∈ Ω(n)

        2n ∈ Ω(n)

        n/2 ∈ Ω(n)

        log(n) ∉ Ω(n)

big-Θ is the set of all functions that grow at the same
  rate as the given one.

  n ∈ Θ(n)

  100n ∈ Θ(n)

  n² ∉ Θ(n)

  log(n) ∉ Θ(n)


Ex. 3.1-1

prove that max(f(n),g(n)) ∈ Θ(f(n) + g(n))

Recall:

    Θ(h(n)) = { f(n) | \exists c₁ c₂ k. \forall n ≥ k.
                                    0 ≤ c₁ g(n) ≤ f(n) ≤ c₂ g(n) }

To clarify:

    max(f(n),g(n))(n) = f(n) if f(n) ≥ g(n)
                        g(n) if g(n) > f(n)

* Part 1. (Non-negative)

   nts. 0 ≤ c₁ (f(n) + g(n))
   Because f(n) and g(n) are asymp. non-negative, there is a k
   where f(n) ≥ 0 and g(n) ≥ 0 for all n ≥ k.
   So we can choose c₁ any number greater than 0.

* Part 2. (The lower bound)

   nts. c₁ (f(n) + g(n)) ≤ max(f(n),g(n))
   We have f(n) + g(n) ≤ 2 \, max(f(n),g(n))
   then divide both side by 2
   1/2 (f(n) + g(n)) ≤ max(f(n),g(n))
   so we choose c₁ = 1/2.

* Part 3. (The upper bound)

   nts. max(f(n),g(n)) ≤ c₂ g(n)

   We have f(n) ≤ f(n) + g(n) for all n ≥ k
   and    g(n) ≤ f(n) + g(n)  for all n ≥ k, 
   so     max(f(n), g(n)) ≤ f(n) + g(n) for all n ≥ k.
   We choose c₂ to be 1.

## Binary Trees

traversals: 

* pre-order: current, left, right
* in-order: left, current, right
* post-order; left, right, current

first method

last method

next method

    if right child, return right's first
    else find ancestor that comes next wrt. inorder

previous method (mirror of next)


## Binary Search Trees

Why are BST's good?

The Binary-Search-Tree Property:
For every node x in the tree,

1) if node y is in the left subtree of x, then y.key ≤ x.key, and
2) if node z is in the right subtree of x, then x.key ≤ z.key.

Example tree:

                     _____ 25____
                    /            \
                 __15__           50
                /      \         /
               10       22      35
              /  \             / 
             4    12          31 

search

insert

remove

## AVL Trees, balancing and rotations

AVL Property: children differ in height by at most 1.

Example AVL Tree:

                  5 h=2
                 / \
            h=0 1   8 h=1
                     \
                      11 h=0

Not an AVL tree:

                  5 h=3  (node 5 breaks the AVL property)
                 / \
            h=0 1   8 h=2  (node 8 breaks the AVL property, -1 vs. 1)
                     \
                      11 h=1
                     /
                    9 h=0

How to maintain the AVL property when inserting?

insert: 10 20 30 15 12

         10

Insert 20:

         10
          \
           20

Insert 30:

         10
          \
           20 h=1
            \
             30 h=0

Node 1 is not AVL! (-1 vs 1)
Rotate 1 to the left.

           20
          / \
         10  30

In general:

                   y                  x
                  / \    right(y)    / \
                 x   C  -------->   A   y
                / \     <--------      / \
               A   B     left(x)      B   C

Insert 15:

           20
          /  \
         10   30
          \
           15

Insert 12:

           20
          /  \
         10   30
          \
           15
           /
          12

Node 10 is not AVL! The right child is left heavy, so
rotate child (15) to the right.

           20
          /  \
         10   30
          \
           12
            \
             15

Then rotate 10 to the left

                   20
                  / \
                 12  30
                /  \
              10    15
