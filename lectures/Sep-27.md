## AVL Trees (Adelson-Velskii and Landis, 1962)

**Definition** The AVL Invariant: the height of two child subtrees may
only differ by 1.

Examples of trees that are AVL:

              o               o          o         o
             /               / \        / \       / \
            o               o   o      o   o     o   o
                                          /     /     \
                                         o     o       o

Examples of trees that are **not** AVL:

        o      o              o
       /        \            / \
      o          o          o   o
       \          \        / \
        o          o      o   o
                               \
                                o

If an insertion would violate this invariant, then the tree
is rebalanced to restore the invariant.

Punch line: search, insert, remove are all O(log(n)).

Red-black trees are an alternative:
AVL is faster on lookup than red-black trees but slower on
insertion and removal because it is more rigidly balanced.

## How can we maintain the AVL invariant during insert? (remove is similar)

1. Do the normal BST insert.

2. Fix the AVL property if needed.

    We may need to fix problems along the entire path
    from the point of insertion on up to the root.

Example insertion and rebalancing:

                 41
               /    \
             20      \
           /    \     65
          11     29  /
                /   50
              26

          insert(23) ==>

                 41
               /    \
             20      \
           /    \     65
          11     29  /
                /   50
              26
             /
           23

Node 29 breaks the AVL invariant.

### Tree Rotation

                    y                         x
                   / \    right_rotate(y)    / \
                  x   C  --------------->   A   y
                 / \     <-------------        / \
                A   B     left_rotate(x)      B   C

This preserves the BST property and the in-order ordering.

A x B y C  =  A x B y C

Insert example: let's use rotation to fix up our insert(23) example:

                       29
                      /    right_rotate(29)
                    26     ---------------->    26
                   /                           /  \
                 23                          23    29

However, in different situations, the way in which we use tree
rotation is different. So let's look at more situations.

* Insert example: insert(55)

                      41
                    /    \
                  20      65
                 /  \     /
                11   29  50
                     /
                   26

    So 65 breaks the AVL invariant, and we have a zig-zag:

                   65
                  /
                50
                  \
                   55

    A right rotation at 65 gives us a zag-zig, we're not making progress!

                  65(y)                        50(x)
                 /        right_rotate(65)       \
                50(x)     ---------------->      65(y)
                  \                              /
                   55(B)                       55(B)

    Instead, let's try a left rotate at 50:

                  65                           65
                 /        left_rotate(50)     /
                50(x)     --------------->  55(y)
                  \                         /
                   55(y)                 50(x)

    This looks familiar, now we can rotate right. 

                      65(y)                        55(x)
                     /      right_rotate(65)       /  \
                   55(x)    --------------->    50(A)  65(y)
                  /
                50(A)

### Student question

starting with an empty AVL tree, insert 

    14, 17, 11, 7, 4, 53, 13, 12, 8
        
Solution:

after insert 14, 17, 11, 7:

                       14
                      /  \
                    11    17
                   /
                  7

insert 4:

                       14
                      /  \
                    11    17
                   /
                  7
                 /
                4

Node 11 doesn't satisfy AVL.

rotate_right(11)

                    14
                   /  \
                  7    17
                 / \
                4   11

insert 54, 13, 12:

                       14
                      /  \
                     7    17
                    / \     \
                   4   11    54
                        \
                         13
                        /
                      12

Node 11 doesn't satisfy AVL.

        rotate_right(13)

                   11
                    \
                     12
                       \
                       13

        rotate_left(11)

                       14
                      /  \
                     7    17
                    / \    \
                   4   12   54
                      /  \
                     11   13

insert 8:

                           14
                          /  \
                         7    17
                        / \    \
                       4   12   54
                          /  \
                         11   13
                        /
                       8

Node 7 doesn't satisfy AVL. There's a zig-zag. 

        rotate_right(12)

                           14
                          /  \
                         7    17
                        / \    \
                       4   11   54
                          /  \
                         8    12
                               \
                               13

        left_rotate(7)

                           14
                          /  \
                         11   17
                        /  \    \
                       7   12   54
                      / \    \
                     4   8   13

* Algorithm for fixing AVL property

    From the changed node on up  (there can be several AVL violations)
    * update the heights
    * suppose x is the lowest node that is not AVL
    * if height(x.left) ≤ height(x.right)
            
        1. if height(x.right.left) ≤ height(x.right.right)
                
            let k = height(x.right.right)

                        x k+2                                y ≤k+2
                       / \          left_rotate(x)          / \
                   ≤k A   y k+1     ===============>  ≤k+1 x   C k
                         / \                              / \
                    ≤k B   C k                      ≤k A   B ≤k
                
        2. if height(x.right.left) > height(x.right.right)
                
            let k = height(x.right.left)

                  k+2 x                               y k+1
                     / \                            /   \
                k-1 A   z k+1    R(z), L(x)      k x     z k
                       / \      =============>    / \   / \
                    k y   D k-1              k-1 A   B C   D k-1
                     / \
                    B   C <k

    - if height(x.left) > height(x.right)
        
        1. if height(x.left.left) < height(x.left.right)  (note: strictly less!)
                
            let k = height(x.left.right)

                        x k+2                                 z k+1
                       / \                                  /   \
                  k+1 y   D k-1      L(y), R(x)          k y     x k
                     / \            =============>        / \   / \
                k-1 A   z k                              A   B C   D    <k
                       / \
                      B   C <k
                
        2. if height(x.left.left) ≥ height(x.left.right)  (note: greater-equal!)
                
            let k = height(l.left.left)

                      x k+2                                  y k+1
                     / \         right_rotate(x)            / \
                k+1 y   C k-1    ===============>        k A   x k+1
                   / \                                        / \
                k A   B ≤k                                ≤k B   C k-1
                
* Student group work

    Given the following AVL Tree, delete the node with key 8 and
    restore the AVL property using tree rotations as described above.
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
  line, sorted by where they cross the sweep line when they
  are first added to the BST.
* We maintain the invariant that each segment in the BST does not
  intersect with the next and previous segments in the BST.
* When you add a segment to the BST, check whether it intersects with
  the next and previous segments in the BST. If it does, stop.
* When you remove a segment from the BST, check whether the
  segment before it intersects with the segment after it.

We'll need fast membership testing, insert, remove, and next/previous.

## Motivation for balanced BSTs

Recall that search time is O(h), where h is the height of the tree.

Definition of height

    int compute_height(Node n) {
        if (n == null) {
                return -1;
        } else {
                int hl = compute_height(n.left);
                int hr = compute_height(n.right);
                return 1 + Math.max(hl, hr);
        }
    }

Example tree with heights in brackets:

                 41[3]
               /       \
            20[2]       65[1]
           /    \        /
         11[0]  29[1] 50[0]
          /
        26[0]

The problem of unbalanced trees

        o
         \
          o
           \
            o
             \
              o
               \
                o
                 \
                  o
                   \
                    o

height = n

vs.

              o
             / \
            /   \
           o     o
          / \   / \
         o   o o   o

height = log(n)


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
    therefore
    N(h) > 2^{h/2}

Now let's solve for h in terms of N(h). We take the log of both sides

    log(N(h)) > log((2^{h/2})) = h/2 log(2) = h/2
    so
    2 log(N(h)) > h

N(h) is the minimum number of nodes for a AVL tree of height h,
so n > N(h) and we have:

    2 log(n) > 2 log(N(h)) > h

so h in O(log(n)).


## AVL Sort

* insert n items: O(n log(n))
* in-order traversal: O(n)
* overall time complexity: O(n log(n))


## ADT's that can be implemented by AVL Trees

* priority queue:
  insert, delete, min

* ordered set:
  insert, delete, min, max, after, before
