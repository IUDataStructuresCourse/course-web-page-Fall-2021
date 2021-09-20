# Lecture: Binary Search Trees (BSTs)

## Code Review of Merge Sort

[MergeSort Submission](./MergeSort/MergeSort1.java)

[MergeSort Submission](./MergeSort/MergeSort3.java)

[MergeSort Submission](./MergeSort/MergeSort4.java)

## Quiz

1. To prove that 4n + 20 ∈ 𝑂(n log n), select all of the following
choices for c and k in the definition of big-O that are
appropriate. (The log is base 2.)

	𝑐=1,𝑘=32     yes         4*32 + 20 = 148 <= 160 = 32 * 5 = 1 * 32 log 32
	𝑐=2,𝑘=16     yes         4*16 + 20 = 84 <= 128 = 2 * 16 * 4
	𝑐=1,𝑘=16     no          4*16 + 20 = 84 > 64 = 1 * 16 * 4 
	𝑐=3,𝑘=4      no          4*4 + 20 = 36 > 24 = 3 * 4 * 2
 
2. What is the time complexity of the following flood function in
terms of the total number of tiles on the board?

		static void flood(WaterColor color,
								  LinkedList flooded_list,
								  Tile[][] tiles,
								  Integer board_size) {
			HashSet flooded = new HashSet<>(flooded_list);
			for (int i = 0; i != flooded_list.size(); ++i) {
				Coord c = flooded_list.get(i);
				for (Coord n : c.neighbors(board_size)) {
					if (!flooded.contains(n)
							&& tiles[n.getY()][n.getX()].getColor() == color) {
						flooded_list.add(n);
						flooded.add(n);
					}
				}
			}
		}
		
	Answer: O(n^2)

3. Consider the following implementation of Binary Search that uses a while loop.

		static int search_loop(int A[], int key) {
			int low = 0;
			int high = A.length;
			int mid = 0;
			int result = -1;
			while (low != high && result == -1) {
				mid = (low + high) / 2;
				if (A[mid] < key)
					low = mid + 1;
				else if (A[mid] > key)
					high = mid;
				else {
					result = mid;
				}
			}
			return result;
		}

	This Binary Search algorithm is suppose to return -1 if the key is not
	in the array and return the index of the key if the key is in the
	array.

	Which of the following is the correct loop invariant for the while loop?

    yes:
	
		(result == -1
		 && !contains(A, key, 0, low)
		 && !contains(A, key, high, A.length))
		|| A[result] == key

    no:
	
		result == -1 || A[result] == key

    no:
	
		low <= mid && mid < high

    no:
	
		!contains(A, key, 0, low)
		 && !contains(A, key, high, A.length)


## Binary Search Trees

Idea: use binary trees to implement the Set interface, such that doing
a search for an element is like doing binary search.

The **Binary-Search-Tree Property**:
For every node x in the tree,
1. if node y is in the left subtree of x, then y.data < x.data, and
2. if node z is in the right subtree of x, then x.data < z.data.

(We are considering BSTs that do not allow duplicates.)

We can also use BSTs to implement the Map interface (aka. "dictionary").

	interface Map<K,V> {
	   V get(K key);
	   V put(K key, V value);
	   V remove(K key);
	   boolean containsKey(K key);
	}


## Binary Search Tree and Node Classes

`BinarySearchTree` is like `BinaryTree` (has a `root`) but also
has a `lessThan` predicate for comparing elements.

	class BinarySearchTree<K> {
		Node root;
		BiPredicate<K, K> lessThan;
		...
		class Node {
			T data;
			Node left, right, parent;
			...
        }
	}

We define `Node` as a class nested inside `BinarySearchTree` for
convenience.


## `find`, `search`, and `contains` methods of `BinarySearchTree`.

Example: Search for 6, 9, 15 in the following tree:

		  8
		/   \
	   /     \
	  3       10
	 / \        \
	1   6       14
	   / \     /
	  4   7   13

Find the node with the specified key, or if there is none, the parent of
where such a node would be.

	protected Node find(K key, Node curr, Node parent) {
		if (curr == null)
			return parent;
		else if (lessThan(key, curr.data))
			return find(key, curr.left, curr);
		else if (lessThan(curr.data, key))
			return find(key, curr.right, curr);
		else
			return curr;
	}

	public Node search(K key) {
		Node n = find(key, root, null);
		if (n != null && n.data.equals(key))
			return n;
		else
			return null;
	}

	public boolean contains(K key) {
		return search(key) != null;
	}

What is the time complexity? answer: O(h), where h is the
height of the tree

## **Student in-class exercise**: 

`insert` into a binary search tree using the `find` method.

Return the inserted node, or null if the key is already in the tree.




Solution:

	public Node insert(K key) {
		Node n = find(key, root, null);
		if (n == null){
			root = new Node(key);
			return root;
		} else if (lessThan(key, n.data)) {
			Node x = new Node(key);
			n.left = x;
			return x;
		}  else if (lessThan(n.data, key)) {
			Node x = new Node(key);
			n.right = x;
			return x;
		} else
			return null;
	}

What is the time complexity? answer: O(h) where h is the height of the tree.


## Remove node z

* Case 1: (no left child)

		  |              |
		z=o              A
		   \       ==>
			A

* Case 2: (no right child)

			|            |
		  z=o            A
		   /       ==>
		  A

* Case 3: Two children

			 |
		   z=o
			/ \
		   A   B

	The main idea is to replace z with the node after z, which
	is the first node y in subtree B.

	Two cases to consider:

	Case a) B is y

			 |                  |
		   z=o        ==>       y
			/ \                / \
		   A   y              A   C
				\
				 C

	Case b) B is not y (y is properly inside B)

			 |                  |
		   z=o        ==>       y
			/ \                / \
		   A   B             A    B
			  ...                ...
			   |                  |
			   y                  C
				\
				 C      

	What is the time complexity? answer: O(h) where h is the height

Solution for `remove`:

	public void remove(T key) {
	   root = remove_helper(root, key);
	}

	private Node remove_helper(Node n, int key) {
		if (n == null) {
			return null;
		} else if (lessThan(key, n.data)) { // remove in left subtree
			n.left = remove_helper(n.left, key);
			return n;
		} else if (lessThan(n.data, key)) { // remove in right subtree
			n.right = remove_helper(n.right, key);
			return n;
		} else { // remove this node
			if (n.left == null) {
				return n.right;
			} else if (n.right == null) {
				return n.left;
			} else { // two children, replace with first of right subtree
				Node min = n.right.first();
				n.data = min.data;
				n.right = n.right.delete_first(); // another helper function
				return n;
			}
		}
	}
