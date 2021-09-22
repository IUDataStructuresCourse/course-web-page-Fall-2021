# Lecture: Correctness, Balanced AVL Trees

## More on loop invariants

Given an array of non-negative integers, return the maximum of the
elements in the array `A`. If the array is empty, return `-1`.

    int find_max(int[] A) {
        int max = -1;
        int i = 0;
        while (i != A.length) {
          if (A[i] > max)
              max = A[i];
          ++i;
        }
        return max;
    }

What's the loop invariant?

## Reasoning about recursive functions

A **precondition** is something that must be true prior
to calling a function.

A **postcondition** is something that will be true after
calling the function.

    int find_max(int[] A, int begin, int end) {
        if (end - begin == 0) {
            return -1;
        } else {
            int mid = begin + (end - begin) / 2;
            int max_left = find_max(A, begin, mid);
            int max_right = find_max(A, mid, end);
            return max(max_left, max_right);
        }
    }

Precondition: 0 <= begin <= end <= A.length
Postcondition: (result == -1 && (begin == end))
               ||
               (result >= 0 && (begin < end)
               && result is the maximum element in [begin,end) of A)

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

## AVL Trees (Adelson-Velskii and Landis, 1962)

The AVL Invariant: the height of two child subtrees may only differ by 1.

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

### Is the AVL invariant enough to keep a BST balanced? (skip?)

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
