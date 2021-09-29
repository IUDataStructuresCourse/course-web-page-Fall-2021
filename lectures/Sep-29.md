# More about Binary Search Trees and AVL Trees

Ran out of time last week to discuss removing a node from a BST.  So
let's go over that first.

## Remove node z from a BST

* Case 1: (no left child)

          |              |
          z              A
           \       ==>
            A

* Case 2: (no right child)

            |            |
            z            A
           /       ==>
          A

* Case 3: Two children

             |
             z
            / \
           A   B

    The main idea is to replace z with the node after z, which
    is the first node y in subtree B.

    Two cases to consider:

    Case a) B is y

             |                  |
             z        ==>       y
            / \                / \
           A   y              A   C
                \
                 C

    Case b) B is not y (y is properly inside B)

             |                  |
             z        ==>       y
            / \                / \
           A   B             A    B
              ...                ...
               |                  |
               y                  C
                \
                 C

    What is the time complexity? answer: O(h) where h is the height

* Student group work

    Given the following AVL Tree, delete the node with key 8 and
    restore the AVL property using tree rotations:
	
                    y                         x
                   / \    right_rotate(y)    / \
                  x   C  --------------->   A   y
                 / \     <-------------        / \
                A   B     left_rotate(x)      B   C
	
    (This example has two nodes that end up violating the AVL
    property.)

                 8
               /   \
              5     10
             / \   / \
            2   6 9   11
           / \   \      \
          1   3   7      12
               \
                4
    Solution: 
    * Step 1: replace node 8 with node 9
            
                   9
                 /   \
                5     10
               / \     \
              2   6     11
             / \   \      \
            1   3   7      12
                 \
                  4

    * Step 2: find lowest node that breaks the AVL property: node 10
    * Step 3: rotate 10 left
      
                   9
                 /   \
                5      11
               / \    /  \
              2   6  10   12
             / \   \
            1   3   7
                 \
                  4

    * Step 4: find lowest node that breaks the AVL property: node 9
    * Step 5: rotate 9 right
      
                  5
                /   \
               /     \
              2        9
             / \     /   \
            1   3   6     11
                 \   \   /  \
                  4   7 10   12


## Introduce the  Segment Intersection Project. Demo the solution.

Given a set of n segments, is their a pair of segments that intersect?

Suppose we have a routine for testing whether 2 segments intersect.

Simplifications:
    * No vertical segments
    * No three-way (or more) intersections

Brute force: test all combinations O(n²)

A better algorithm: Line Sweep
* Sort all the end-points of the line segments from left to
  right (x-axis)
* Move the line sweep from left to right, stopping at each end point.
* Maintain a BST of all the segments that intersect the sweep
  line, sorted by the y-coordinate of where they cross the sweep line when they
  are first added to the BST.
* We maintain the invariant that each segment in the BST does not
  intersect with the next and previous segments in the BST.
* When you add a segment to the BST, check whether it intersects with
  the next and previous segments in the BST. If it does, stop.
* When you remove a segment from the BST, check whether the
  segment before it intersects with the segment after it.

We'll need fast membership testing, insert, remove, and next/previous.


### Is the AVL invariant enough to keep a BST balanced?

We want the height to be O(log(n)) where n is the number of
nodes.

What's the worst case?
Let N(h) be the minimum number of nodes in an AVL tree of height h.
We want to force N(h) to be something like 2^h nodes.

For each node, right has one more in height.

Recurrence formula for N(h):

N(h) = N(h-1) + N(h-2) + 1

Let's figure out a simple lower bound:

    N(h-1) + N(h-2) + 1 > 2 N(h-2)
    
Solving the recurrence

    F(h) = 2 F(h-2)

yields

    F(h) = 2^(h/2)

so

    N(h) > 2^(h/2)

Now let's solve for h in terms of N(h). We take the log of both sides

    log( N(h) ) > log( 2^(h/2) ) = h/2 log(2) = h/2
    so
    2 log(N(h)) > h

N(h) is the minimum number of nodes for a AVL tree of height h,
so n > N(h) and we have:

    2 log(n) > 2 log(N(h)) > h

so h in O(log(n)).


## Using AVL Trees for sorting

* insert n items: O(n log(n))
* in-order traversal: O(n)
* overall time complexity: O(n log(n))


## ADT's that can be implemented by AVL Trees

* ordered set:
  insert, delete, next, previous
  
* priority queue:
  insert, delete, min

