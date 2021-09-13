# Lecture: Iterators, Merge Sort, Stack, Queue, Set

## Java Generics, a quick reminder

Classes and methods may have *type parameters*, written inside the symbols `<` and `>`.

The body of a class and method may refer to the type parameters
anywhere you would use other types (variable declarations, etc.).

	class Node<T> {
	  T data;
	  Node<T> next;
	}

When using the class, choose a *type argument* for each parameter.
Here we choose `Integer` for the parameter `T`.

	Node<Integer> n = new Node<Integer>(42, null); 

Caveats: 

* You can't instantiate a generic with built-in types (e.g. `int`, `boolean`).
* You can't use `new` on a type parameter (e.g. `new T()`).
* You can't call methods on things whose type is a type parameter such as `T` (e.g. `data.foo()`).

## Java Interfaces, a quick reminder

An interface acts as an intermediary between data structures and algorithms.

An interface specifies some methods that are common to several
data structures and that are needed by one or more algorithms.

Example: angles, degrees, and radians.

	interface Angle<T> {
		Angle sum(Angle other);
		...
	}

	class Degree implements Angle<Degree> {
		int degree;
		Degree sum(Degree other) { return (degree + other.degree) % 360;
	}

	class Radian implements Angle<Radian> {
		float radian;
		Radian sum(Radian other) { ... }
	}

## Iterator interface

An iterator represents a position within a sequence.

	public interface Iterator<T> {
		// The get() method returns the element at the current position.
		T get();
		// The set() method writes the element e into the current position of the sequence.
		void set(T e);
		// The advance() method moves the iterator to the next position.
		void advance();
		// Advance `n` times.
		void advance(int n);
		// The equals() method tests whether this iterator is at the same position
		// as the other iterator.
		boolean equals(Iterator<T> other);
		// The clone() method creates a new iterator at the same position as this iterator.
		Iterator<T> clone();
	}

## Example: Linear Search using the Iterator interface

    public static <E>
    Iterator<E> find_first_if(Iterator<E> begin, Iterator<E> end, E x, Predicate<E> p) {
        Iterator<E> i = begin.clone();
        for (; !i.equals(end) & !p.test(i.get()); i.advance()) { }
        return i;
    }

## Linked List implemetation of Iterator

	public class LinkIterator<T> implements Iterator<T> {
		Node<T> node;
		LinkIterator(Node<T> p) { node = p; }
		public T get() { return node.data; }
		public void set(T x) { node.data = x; }
		public void advance() { node = node.next; }
		public void advance(int n) {
			for (; n > 0; --n) {
				advance();
			}
		}
		public boolean equals(Iterator<T> other) {
			return node == ((LinkIterator<T>)other).node;
		}
		public LinkIterator<T> clone() {
			return new LinkIterator<T>(node);
		}
	}

## Student Exercise: implement a generic `equal_ranges` function

    public static <E>
    boolean equal_ranges(Iterator<E> begin1, Iterator<E> end1, 
	                     Iterator<E> begin2, Iterator<E> end2) {
        Iterator<E> i = begin1.clone(), j = begin2.clone();
        for (; ! i.equals(end1) && ! j.equals(end2); i.advance(), j.advance())
            if (! i.get().equals(j.get()))
                return false;
        return i.equals(end1) && j.equals(end2);
    }

## Student Exercise: implement an Iterator for arrays.

	class ArrayIterator<T> implements Iterator<T> {
		private T[] array;
		private int pos;
		public ArrayIterator(T[] a, int p) { array = a; pos = p; }
		public T get() { return array[pos]; }
		public void set(T x) { array[pos] = x; }
		public void advance() { ++pos; }
		public void advance(int n) { pos += n; }
		public ArrayIterator<T> clone() { return new ArrayIterator<T>(array,pos); }
		public boolean equals(Iterator<T> other) {
			ArrayIterator<T> i = (ArrayIterator<T>) other;
			return pos == i.pos;
		}
		public String toString() {
			return "ArrayIterator(" + String.valueOf(pos) + ")";
		}
	}

## Lab: MergeSort

Divide and conquer!

1. Split the array in half and sort the two halves.
2. Merge the two halves.

Implementation of Merge Sort:

	public static int[] merge_sort(int[] A) {
	   if (A.length > 1) {
		   int middle = A.length / 2;
		   int[] L = merge_sort(Arrays.copyOfRange(A, 0, middle));
		   int[] R = merge_sort(Arrays.copyOfRange(A, middle, A.length));
		   return merge(L, R);
	   } else {
		   return A;
	   }
	}

Implementaiton of Merge

	private static int[] merge(int[] left, int[] right) {
	   int[] tmp = new int[left.length + right.length];
	   int i = 0, j = 0, k = 0;
	   while (i != left.length && j != right.length) {
		   if (left[i] <= right[j]) {
			  tmp[k] = left[i];
			  ++i; ++k;
		   } else {
			  tmp[k] = right[j];
			  ++j; ++k;
		   }
	   }
	   k = copy_range(left, i, left.length, tmp, k);
	   k = copy_range(right, j, right.length, tmp, k);
	   return tmp;
	}

Implementation of Copy Range

	private static int
	copy_range(int[] src, int begin, int end, int[] dest, int out) {
		for (int i = begin; i != end; ++i, ++out) {
			dest[out] = src[i];
		}
		return out;
	}

What's the time complexity?

Recursion tree:

			   c*n                    = c*n
			 /     \
			/       \
	   c*n/2        c*n/2             = c*n
	   /  \         /   \
	  /    \       /     \
	c*n/4  c*n/4  c*n/4  c*n/4        = c*n
	...

Height of the recursion tree is log(n).

So the total work is c\, n\, log(n).

Time complexity is O(n \, log(n)).


## Stack (LIFO)

analogy: stack of pancakes

	interface Stack<E> {
		void push(E d);
		E pop();
		E peek();
		boolean empty();
	}

Example uses: 

* reverse an array
* check for matching parentheses (with multiple kinds: square, round, curly)
* parsing (e.g. HTML)
* depth-search search
* procedure call stack

## Queue (FIFO)

Analogy: checkout line at a grocery store 

	interface Queue<E> {
		E dequeue();
		void enqueue(E e);
		E front();
		boolean empty();
	}

Example uses: 

* breadth-first search, 
* requests to a shared resource (e.g., printer),
* interupt handling inside an OS kernel, 
* buffering to handle asynchronous communiation, such as interprocess IO,
	disk access, etc.

Implementation: 

* array
* doubly-linked list

## Set

Like a set in mathematics. A collection of elements where the ordering
of the elements is not important, only membership matters.
`Set` ignores duplicates.

	interface Set {
	   void insert(int e);
	   void remove();
	   boolean member(int e);
	   boolean empty();
	   Set union(Set other);
	   Set intersect(Set other);
	   Set difference(Set other);
	}

Implementations of `Set` are the topic of several future lectures.

