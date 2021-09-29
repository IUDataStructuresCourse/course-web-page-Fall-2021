import java.util.ArrayList;
import java.util.function.Predicate;

public class MergeSort {

    /*
      Returns the number of elements in the half-open range [begin, end).
      Time complexity: O(n)
     */
    public static <E>
    int distance(Iterator<E> begin, Iterator<E> end) {
        int n = 0;
        for (Iterator<E> i = begin.clone(); !i.equals(end); i.advance()) {
            ++n;
        }
        return n;
    }

    /*
     Copies the elements in the range [begin,end) into the range [result, result.advance(n))
     where n = distance(begin, end).
     Returns an iterator for the position result.advance(n).
     Time complexity: O(n)
     */
    public static <E>
    Iterator<E> copy(Iterator<E> begin, Iterator<E> end, Iterator<E> result) {
        Iterator<E> out = result.clone();
        for (Iterator<E> i = begin.clone(); !i.equals(end); i.advance()) {
            out.set(i.get());
            out.advance();
        }
        return out;
    }

    /*
      Create an array of the specified size, with all the elements initialized to `null`.
     */
    public static <E>
    ArrayList<E> make_array(int size) {
        ArrayList<E> array = new ArrayList<>(size);
        for (int i = 0; i != size; ++i) {
            array.add(null);
        }
        return array;
    }

    /*
     Precondition: the input ranges [begin1,end1) and [begin2,end2) are sorted.
     Let n be the number of elements in the first range and m be the number of 
     elements in the second range.
     The elements from both ranges are written to the range [result, result.advance(n+m)) 
     in such a way that the result is in sorted order according to `Comparable`.
     Returns an iterator for the position result.advance(n+m).
     */
    public static <E extends Comparable<? super E>>
    Iterator<E> merge(Iterator<E> begin1, Iterator<E> end1,
                      Iterator<E> begin2, Iterator<E> end2,
                      Iterator<E> result) {
        Iterator<E> b1 = begin1.clone();
        Iterator<E> b2 = begin2.clone();
        while (! b1.equals(end1) && ! b2.equals(end2)) {
            if (b1.get().compareTo(b2.get())<=0){
                result.set(b1.get());
                b1.advance();
            }
            else{
                result.set(b2.get());
                b2.advance();
            }
	    result.advance();
        }
        while (! b1.equals(end1)){
            result.set(b1.get());
            b1.advance();
            result.advance();
        }
        while (! b2.equals(end2)){
            result.set(b2.get());
            b2.advance();
            result.advance();
        }
	return result;
    }
    
    /*
    The `begin` and `end` iterators represent the half-open range
    `[begin,end)` of a sequence. After the call to `sort`, the range
    `[begin,end)` contains the same elements as before, but sorted
    from low to high according to the `Comparable` ordering.
     */
    public static <E extends Comparable<? super E>>
    void sort(Iterator<E> begin, Iterator<E> end) {
	length = distance(begin,end);
        if (length > 1) {
            Iterator<E> middle = begin.clone();
            middle.advance(length / 2);

            sort(begin, middle);
            sort(middle, end);

            ArrayList<E> tmp = make_array(length);
            ArrayListIterator i = new ArrayListIterator(tmp,0);
            Iterator<E> i_start = i.clone();
            Iterator<E> i_end = merge(begin,middle,middle,end,i);
            copy(i_start,i_end,begin);
        }
    }
}

