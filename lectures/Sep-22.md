# Lecture: Loop Invariants, Correctness of Recursive Functions

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

