# Homework 1

Submit your homework solution on Canvas (as text file, pdf, etc.).

## Correctness of `find_first_true`

Prove that your implementation of the `find_first_true` function is
correct, which is to say, `find_first_true(A, begin, end)` must return
the position of the first `true` element in the specified half-open
interval. In other words, find the smallest index `i` such that `A[i]`
is `true`, `begin <= i`, and `i < end`.  If there are no `true`
elements in the interval, then `find_first_true` must return the `end`
position of the interval.  As part of the proof, come with with a loop
invariant, state it precisely, and provide detailed reasons for why

1. the loop invariant is true before the loop begins,
2. why the loop invariant is true at the end of a hypothetical
   iteration, assuming it was true at the beginning of the
   hypothetical iteration,
3. the loop invariant combined with the negation of the loop condition
   implies the correctness of `find_first_true`.

## Chapter 1 Textbook Exercises

1.11 a b (proofs about Fibonacci numbers and summations)

1.12  (proofs about sumations)


## Chapter 2 Textbook Exercises

2.1 (function growth rates)

2.2 (reasoning about big-O)

2.5 (reasoning about big-O)

2.7 part I (big-O for example loops)

2.24 (analysis of fast exponentiation)

2.31 (about binary search)
