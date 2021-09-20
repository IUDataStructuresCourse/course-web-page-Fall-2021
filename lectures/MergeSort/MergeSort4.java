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
        Iterator<E> nIteratorB = begin1.clone();
        Iterator <E> nIteratorE = end1.clone();
        int n = distance(nIteratorB,nIteratorE);
        Iterator<E> mIteratorB = begin2.clone();
        Iterator <E> mIteratorE = end2.clone();
        int m = distance(mIteratorB,mIteratorE);
        //now we know the number of elements in both ranges
        ArrayList<E> temp = make_array(n+m);
        Iterator<E> beginTemp = new ArrayListIterator<E>(temp,0);
        Iterator<E> endTemp = new ArrayListIterator<E>(temp,0);
        int i = 0, j = 0, k = 0; //k no longer needed because we are adding into arraylist
        Iterator<E> begin1clone = begin1.clone();
        Iterator<E> begin2clone = begin2.clone();
        while (i != n && j !=m){ //i != first half length and j != second half length
            int compToValue = begin1clone.get().compareTo(begin2clone.get());
            if (compToValue <= 0){ //compToValue is -1 if a<b and 0 if a=b
                temp.add(begin1clone.get());
                begin1clone.advance();
                endTemp.advance();
                ++i; ++k; //this line only needed to keep track of when i reaches end
            } else{ //compToValue is 1, a>b
                temp.add(begin2clone.get());
                endTemp.advance();
                begin2clone.advance();
                ++j; ++k; //this line only needed to keep track of when j reaches end
            }
        }
        endTemp.advance();
        result = copy(beginTemp,endTemp,result);
        //result = beginTemp;
        //result.advance(n+m);
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
        Iterator<E> begClone = begin.clone();
        Iterator<E> endClone = end.clone();
        int d = distance(begClone,endClone);
        if (d > 1){
            int middle = d/2;
            //make two iterators for the beginning and end of the first half, then one to pass back to sort
            Iterator<E> begin1 = begin.clone();
            Iterator<E> end1 = begin.clone();
            end1.advance(middle);
            sort(begin1,end1);
            Iterator<E> begin2 = begin.clone();
            begin2.advance(middle); //took out +1 because it's a half open interval
            Iterator<E> end2 = end.clone();
            sort(begin2,end2);
            ArrayList<E> resultArray = make_array(d);
            Iterator<E> result = new ArrayListIterator<E>(resultArray,0);
            //Iterator<E> result = begin.clone();
            Iterator<E> sorted = merge(begin1,end1,begin2,end2,result);
            begin = sorted.clone();
            end = sorted.clone();
            end.advance(d);
        }
    }
}
