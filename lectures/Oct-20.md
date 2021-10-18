# Lecture: Binomial Heaps

## Binomial Trees

Recall the binomial coefficient, n choose k, written 

    ( n )
    ( k )

which is the number of ways to choose k elements from a set of size n.
For example, there are three ways to choose 2 elements from a set of
size 3. For {a,b,c} we have {a,b}, {a,c}, {b,c}.  In general, we have:

    ( n )  =    n! / ( k!(n - k)! )
    ( k )


    ( 3 )  = 3! / ( 2!(3 - 2)! ) = 6 / 2 = 3
    ( 2 )
    

Recall Pascal's Triangle
    
                   0=k  (diagonals)
    n=0          1 / 1
              ----/-- /
      1        1 / 1 / 2
            ----/---/-- /
      2      1 / 2 / 1 / 3
           ---/---/---/-- /
      3    1 / 3 / 3 / 1 / 4
         ---/---/---/---/--
      4  1 / 4 / 6 / 4 / 1 
    

From the triangle it is easy to see that each cell is the sum of
the two cells diagonally above it:

    ( n ) = ( n-1 ) + ( n-1 )
    ( k )   ( k-1 )   ( k   )


Def. A **binomial tree** Bn is a tree whose root has n children, where
the first child is B{n-1}, the second is B{n-2}, ..., on down to
the last child, which is B0.

     B0   B1   B2   B3
     o    o    o     o---\
          |    |\    | \  \
          B0   B1 B0 B2 B1 B0

Consider the number of nodes at each depth within a binomial tree.

    depth
    0    o 1  o 1  o   1     _o    1
              |    |\      _/ |\
    1         o 1  o o 2  o   o o  3
                   |      |\  |
    2              o   1  o o o    3
                          |
    3                     o        1

So the name binomial tree comes from there being n choose k nodes at
depth k of tree Bn.

Each binomial tree Bn can be formed by taking two trees of B{n-1}
and putting one of them as a child of the other's root.

    B2           B3
	o                _o
	|\      B2     _/ |\
	o o  ∪  o  =  o   o o
	|       |\    |\  |
	o       o o   o o o
			|	  |
			o	  o

Turning them on their side and aligning by depth:

    1     1 1       1 2 1
    +
      1     1 1       1 2 1
    =
    1 1   1 2 1     1 3 3 1

The height of a binomial tree Bn is n.  binomial tree Bn has 2^n
nodes.  So height = log nodes. It's balanced!

Student exercise: draw B4. how many nodes does it have?  How many
nodes at each depth?  Solution:

    depth
    0    o---\--\    1
         | \  \  \
    1    o   o  o  o 4
         |   |  |
    2    ooo oo o    6
         |   |
    3    ooo o       4
         |
    4    o           1


Def. A **binomial heap** Bn is a binomial tree where each node has a
key and they satisfy the max-heap property.


## Binomial Forests

Def. A **binomial forest** is a collection of binomial trees.

	class BinomialHeap<K extends Comparable<K>> {
		K key;
		int height;
		ListNode<BinomialHeap<K>> children;
		BiPredicate<K,K> lessEq;
		...
	}

	class ListNode<T> {
		T data;
		ListNode<T> next;
		...
	}

We can implement a priority queue with a forest of binomial heaps.
It's a forest to allow for numbers of nodes that are not powers
of 2. We'll store the forest in order of *increasing* height and we
will not allow two trees of the same height.  The forest is
represented as a cons-list (nested pairs ending in None).

	public class BinomialQueue<K> {
		ListNode<BinomialHeap<K>> forest;
		BiPredicate<K,K> lessEq;
		...
	}

Binomial queues support an efficient union operation, as well as
insert and extract_max. To accomplish the union operation, we'll need
to know how to merge two binomial forests, which in turn will need a
way to link two binomial trees of the same height into one tree.

If two binomial trees have the same height, linking them is easy.
Just make one of the trees the first child of the other.  Pick the one
with the larger key to be on top to maintain the max heap property.

	Bk  U  Bk =  B{k+1}
	B2  U  B2 =  B3

	o       o      o______
	|\      |\     |   \  \
	o o  U  o o =  o    o  o
	|       |      |\   |
	o       o      o o  o
				   |
				   o

	// @precondition this.height == other.height
	BinomialHeap<K> link(BinomialHeap<K> other) {
		if (lessEq.test(other.key, this.key)) {
			ListNode<BinomialHeap<K>> kids = new ListNode<>(other, this.children);
			return new BinomialHeap<>(this.key, this.height + 1, kids);
		} else {
			ListNode<BinomialHeap<K>> kids = new ListNode<>(this, other.children);
			return new BinomialHeap<>(other.key, other.height + 1, kids);
		}
	}

Now, when merging two binomial forests, we'll also need a function
that inserts a single tree into a forest.  This is like the algorithm
for long-hand binary addition where each k is a digit.

### Insert Operation

Insert tree

	o
	|
	o

into forest

	B0 B1 B2
	o  o  o
	   |  |\
	   o  o o
		  |
		  o

First we link the two B1's:

	o   o   o
	| U | = |\
	o   o   o o
			|
			o

and we continue with the insert, which forces us to link the new B2
with the other B2 to get B3 (see above), so we get the forest:

	B0 B3


You will implement the insert method in lab.

	static <K extends Comparable<K>> ListNode<BinomialHeap<K>>
	insert(BinomialHeap<K> n, ListNode<BinomialHeap<K>> ns);

In the `BinomialQueue`, we use `insert` to implement `push` as follows.

	public void push(K key) {
		this.forest = insert(new BinomialHeap<>(key,0,null), this.forest, lessEq);
	}

### Merge Operation

The merge function takes two binomial forests, ordered by increasing
height, and turns them into a single forest.  This algorithm is
analogous to the merge of merge sort.  Examples:

	merge [B0 B2] with [B1 B4] = [B0,B1,B2,B4],
	merge [B0 B1] with [B1 B4] = [B0 B2 B4].

You will implement `merge` in lab.

	ListNode<BinomialHeap<K>>
	merge(ListNode<BinomialHeap<K>> ns1, ListNode<BinomialHeap<K>> ns2);

### extract_max

1. In the forest, find the tree with the largest key
2. Remove that tree, but merge all of its children into the forest.
   This requires reversing the children so that they are
   ordered by increasing height.

Here's the code:

	public K extract_max() {
		BinomialHeap<K> max = find_max(this.forest);
		this.forest = remove(max, this.forest);
		ListNode<BinomialHeap<K>> kids = reverse(max.children, null);
		this.forest = merge(this.forest, kids);
		return max.key;
	}

**Student exercise**: implement `find_max`.  It takes a forest and
returns the smallest of all the roots.
