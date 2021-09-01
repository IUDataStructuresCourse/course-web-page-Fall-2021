# Lecture: More Algorithm Analysis

> A manager went to the master programmer and showed him the
> requirements document for a new application. The manager asked the
> master: ''How long will it take to design this system if I assign five
> programmers to it?''
> ''It will take one year,'' said the master promptly.
> ''But we need this system immediately or even sooner! How long will it
> take if I assign ten programmers to it?''
> The master programmer frowned. ''In that case, it will take two years.''
> ''And what if I assign a hundred programmers to it?''
> The master programmer shrugged. ''Then the design will never be
> completed,'' he said.
>
> -- Tao of Programming

**Example** 4x versus x²

1. Is 4x ≲ x²?
2. Is x² ≲ 4x?
   
e.g. x + 10 versus x

1. Is x + 10 ≲ x?
2. Is x ≲ x + 10?

**Example** Let's show that (n² + n + 10) ∈ O(n²).
So we need to find a constant c such that

n² + n + 10 ≤ c n²

	when n is large.
We divide both sides by n².

1 + 1/n + 10/n² ≤ c

When n is large, the terms 1/n and 10/n² get very small, so we need to
choose something a bit bigger than 1, so we choose c = 2.

Next we need to find a constant k such that
n² + n + 10 ≤ 2 n² when n is greater than k.
We compute a handful of values of these functions.

| n   | n² + n + 10  | 2n²  |
| --- | ------------ | ---- |
| 1   | 12           |  2   |
| 2   | 16           |  8   |
| 3   | 22           | 18   |
| 4   | 30           | 32   |
| 5   | 40           | 50   |
| 6   | 52           | 72   |

We see that from n=4 and onwards, 2n² is greater than n² + n + 10, so
we choose k = 4. We have some emprical evidence that we've made the
correct choices, but to know for sure, we prove the following theorem.

**Theorem** ∀ n ≥ 4, n² + n + 10 ≤ 2 n².

Proof. We proceed by induction on n.
* Base case (n = 0) The theorem is trivially true
    because it's not true that 0 ≥ 4.
* Induction case. Let n be any integer such that n ≥ 4
    and n² + n + 10 ≤ 2 n² (IH). We need to show that

		(n+1)² + (n+1) + 10 ≤ 2 (n+1)²

	which we can rearrange to the following, separating out parts that
	match the induction hypothesis (IH) from the rest.

		(n² + n + 10) + (2n + 2) ≤ 2n² + (4n + 2)

	Thanks to the IH, it suffices to show that 

		2n + 2 ≤ 4n + 2

	which is true because n ≥ 4.


## Student exercise

Show that 2 log n ∈ O(n / 2). 

Hint: experiment with values of n that are powers of 2 because it is
easy to take the log of them:

	log(2) = 1
	log(4) = 2
	log(8) = 3
	...

## Reasoning about asymptotic upper bounds

* Polynomials:
    If f(n) = cᵢ nⁱ + ... + c₁ n¹ + c₀, 
    then f ≲ nⁱ.
* Addition:
    If f₁ ≲ g and f₂ ≲ g,
    then f₁ + f₂ ≲ g.
* Multiplication:
    If f₁ ≲ g₁ and f₂ ≲ g₂,
    then f₁ × f₂ ≲ g₁ × g₂.
* Reflexivity:
    f ≲ f
* Transitivity:
    f ≲ g and g ≲ h implies f ≲ h

* Example: anagram detection

    Two words are **anagrams** of each other if they contain the same
    characters, that is, they are a rearrangement of each other.

    examples: mary <-> army, silent <-> listen, doctor who <-> torchwood

    For the following algorithms, what's the time complexity? space
    complexity?

    * Algorithm 0:
      Generate all permutations of the first word.
      This is O(n!) time and O(n) space.

    * Algorithm 1:
      For each character in the first string
      check it off in the second string.
      This is O(n²) time and O(n) space.

    * Algorithm 2:
      Sort both strings then 
      compare the sorted strings for equality
      This is O(n lg n) time and O(1) space.

    * Algorithm 3:
      Count letter occurences in both words and then compare
          the number of occurences of each letter.
      This is O(n) time and O(k) space
      (where k is the number of characters in the alphabet).

* Review of upper bound: big-O

    f ∈ O(g) iff ∃ k c, ∀ n ≥ k, f(n) ≤ c g(n).

* Practice analyzing the time complexity of an algorithm:

    Insertion Sort

        public static void insertion_sort(int[] A) {
            for (int j = 1; j != A.length; ++j) {
                int key = A[j];
                int i = j - 1;
                while (i >= 0 && A[i] > key) {
                    A[i+1] = A[i];
                    i -= 1;
                }
                A[i+1] = key;
            }
        }

    What is the time complexity of insertion_sort?
    Answer:
    * inner loop is O(n)
    * outer loop is O(n)
    * so the entire algorithm is O(n²)

* Time Complexity of Java collection operations

    * LinkedList
        * add: O(1)
        * get: O(n)
        * contains: O(n)
        * remove: O(1)

    * ArrayList
        * add: O(1)
        * get: O(1)
        * contains: O(n)
        * remove: O(n)

* Common complexity classes:

    * O(1)                        constant
    * O(log(n))                   logarithmic
    * O(n)                        linear
    * O(n log(n))
    * O(n²), O(n^3), etc.         polynomial
    * O(2^n), O(3^n), etc.        exponential
    * O(n!)                       factorial

* Lower bounds

    **Definition** (Omega) For a given function g(n), we define **Ω(g)** as
    the set of functions that grow at least as fast a g(n):

    f ∈ Ω(g) iff ∃ k c, ∀ n ≥ k, 0 ≤ c g(n) ≤ f(n).

    **Notation** f ≳ g means f ∈ Ω(g).

* Tight bounds

    **Definition** (Theta) For a given function g(n), **Θ(g)** is the set
    of functions that grow at the same rate as g(n):
    
    f ∈ Θ(g) iff ∃ k c₁ c₂, ∀ n ≥ k, 0 ≤ c₁ g(n) ≤ f(n) ≤ c₂ g(n).

    We say that g is an *asymptotically tight bound* for each function
    in Θ(g).

    **Notation** f ≈ g means f ∈ Θ(g)

* Reasoning about bounds.

    * Symmetry
      f ≈ g  iff g ≈ f
    * Transpose symmetry
      f ≲ g iff g ≳ f

* Relationships between Θ, O, and Ω.

     * Θ(g) ⊆ O(g)
     * Θ(g) ⊆ Ω(g)
     * Θ(g) = Ω(g) ∩ O(g)

* Example: Merge Sort

    Divide and conquer!
    
    Split the array in half and sort the two halves.
    
    Merge the two halves.
    
        private static int[] merge(int[] left, int[] right) {
           int[] A = new int[left.length + right.length];
           int i = 0;
           int j = 0;
           for (int k = 0; k != A.length; ++k) {
               if (i < left.length
                   && (j ≥ right.length || left[i] <= right[j])) {
                  A[k] = left[i];
                  ++i;
               } else if (j < right.length) {
                  A[k] = right[j];
                  ++j;
               }
           }
           return A;
        }

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

