# Lecture: Linked lists and Streams

> "The Tao gave birth to machine language. Machine language gave birth
> to the assembler. The assembler gave birth to the compiler. Now
> there are ten thousand languages. Each language has its purpose,
> however humble. Each language expresses the Yin and Yang of
> software. Each language has its place within the Tao.  But do not
> program in COBOL if you can avoid it."
>
> --The Tao of Programming

* Overview of the lecture
    * Announcements
    * Linked lists
    * Abstract data types (ADTs): e.g. Stream

* Lab about streams.
   
* Problem: removing from an array is slow. One must shift all
  the following elements to the left.
  
        [1|2|3|4]
        => (remove 2)
        [1|3|4|-]

    need to track number of elements separately from array length
      
    waste of space
      
    time complexity is O(n)

* Linked structures

        array:
        [1|2|3|4] 
        vs.
        linked list:
        [1]->[2]->[3]->[4]-///

        class Node {
           Node(int d, Node n) { data = d; next = n; }
           int data;
           Node next;
        }

    erasing is O(1):
    to erase node [3], change next pointer of node `[2]`

        [1]->[2]  [3]->[4]-///
              \-------/

        static void remove_next(Node n) {
            if (n.next == null) {
                return;
            } else {
                n.next = n.next.next;
            }
        }
    
    inserting in the middle is O(1)

        L 
        |
        V
        [42]->[7]->[3]
        insert_after(L, 10):
        [42]->[10]->[7]->[3]

        static void insert_after(Node n, int d) {
            Node m = new Node(d, n.next);
            n.next = m;
        }

* What's the disadvantage of linked lists? answer: space

* Pattern: Forward Traversal of a List
        
        for (Node n = begin; n != end; n = n.next) {
           ... n.data ...
        }

    where `begin` and `end` are nodes and specify a half-open interval
    within a linked list.

    To process an entire list, `begin` is the head of the list and
    `end` is null.

Example sum of squares on linked list

    int sum_of_squares_list(Node begin) {
	    int total = 0;
        for (Node n = begin; n != null; n = n.next) {
           total += n.data * n.data;
        }
	    return total;
	}

Example sum of squares on array

    int sum_of_squares_array(int[] array) {
	    int total = 0;
        for (int n = begin; n != array.length; ++n) {
           total += n.data * n.data;
        }
	    return total;
	}

Linked lists and arrays can both be accessed through the Stream interface.
Java interfaces are a Java-specific way to express *Abstract Data Types*.

Here's how to implement sum of squares on a Stream.

    int sum_of_squares_stream(Stream<Integer> s) {
	    return s.map(x -> x * x).reduce(0, (x,y) -> x + y);
	}

Map: apply a function `f` to every element of the stream

    [a, b, c, d]
	=>
	[f(a), f(b), f(c), f(d)]

Reduce: combine all the elements of the stream using a function `g`, 
starting at an initial value `init`.

    [a, b, c, d]
	=>
	g(a, g(b, g(c, g(d, init))))

If the stream is empty, the result of reduce is `init`.

    []
	=>
	init

We can then implement the list and array functions using the stream version.

    int sum_of_squares_list(LinkedList<Integer> xs) {
	    return sum_of_squares_stream(xs.stream());
    }

    int sum_of_squares_array(ArrayList<Integer> xs) {
	    return sum_of_squares_stream(xs.stream());
    }

Looking under the hood, `map` could be implemented as follows in terms
of the Stream operations `findFirst()`, `skip(1)`, `concat`, and `of`.

	class Stream<T> {
	  ...
	  Stream<R> map(Function<T, R> f) {
		Optional<R> o = this.findFirst();
		if (o.empty() {
		  return Stream.empty();
		} else {
		  return Stream.concat(Stream.of( f(o.get()) ), this.skip(1));
		}
	  }
	  ...
	}
