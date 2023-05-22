package career.prep.uber;

import java.util.NoSuchElementException;

/**
 * @author Joseph Borodach
 *
 * The design and overview specs are the same as specified in the Heap class.
 */
public class PriorityQueue {
    /**
     * @param <String>
     * @param <Integer>
     */
    public class Pair<String, Integer> {

        private String str;
        private int value;

        Pair(String str, int value) {
            this.str = str;
            this.value = value;
        }
    }

    private int n;
    private Pair<String, Integer>[] arr;

    public PriorityQueue() {
        this(1);
    }

    /**
     * Constructs an empty heap with the specified initial capacity.
     * @param initialCapacity the initial capacity of the heap.
     * @throws if initialCapacity is less than zero.
     */
    public PriorityQueue(final int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity cannot be negative.");
        }
        arr = new Pair[initialCapacity + 1];
        n = 0;
    }

    /**
     * Note:
     *      I would have thought that it would have made more sense to return the string here
     *      because otherwise this excersice is only underthehood since we are also storing the string.
     *      However, the user does not get to leverage the string.
     *      - After thinking about this further, I made another top method which return the string because that made more sense to me.
     * Returns the smallest element (top) in the heap.
     * @return the smallest element in the heap
     * @throws NoSuchElementException if the heap is empty
     * @time complexity: O(1) (constant time)
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return arr[1].value;
    }

    /**
     * Returns the smallest element (top) in the heap.
     * @return the smallest element in the heap
     * @throws NoSuchElementException if the heap is empty
     * @time complexity: O(1) (constant time)
     */
    public Pair<String, Integer> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return arr[1];
    }

    /**
     * What should happen if the string is empty?
     *      - Normally, I would throw an IAE, but unless we are returning the string in the top method it might not be necessary.
     *
     * Adds the new pair to the heap in the appropriate position.
     * @param x the string, key associated with the integer weight and value.
     * @param weight the integer value associated with the string, key.
     * @throws IllegalArgumentException if the string is null.
     * @time complexity: O(log n) (logarithmic time)
     */
    public void insert(String x, int weight) {
        if (x == null) {
            throw new IllegalArgumentException("String cannot be null.");
        }
        // Double the array size if necessary.
        if (n == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[++n] = new Pair<>(x, weight);
        swim(n);
    }

    /**
     * Removes the smallest element (top) from the heap.
     * @throws NoSuchElementException if the heap is empty
     * @time complexity: O(log n) (logarithmic time)
     */
    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        exch(1, n--);
        sink(1);

        // Erase the element (optional)
        arr[n + 1] = null;

        // Shrink the array if necessary.
        if (n > 0 && n == (arr.length - 1) / 4) {
            resize(arr.length / 2);
        }
    }

    /*****************
     * Helper Methods.
     *****************/
    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Resizes the array to the specified capacity.
     * @param capacity the new capacity of the array
     * @time complexity: O(capacity + n) = O(capacity)
     */
    private void resize(int capacity) {
        if (capacity <= n) {
            throw new IllegalArgumentException("New capacity must be larger than original number of elements.");
        }
        Pair[] temp = new Pair[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    /**
     * Moves the element at index k up the heap until it reaches a parent less than itself.
     * @param k the index of the element to be moved
     */
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            // swap child with parent
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * Moves the element at index k down the heap until it reaches the appropriate position.
     * @param k the index of the element to be moved
     */
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Exchanges the elements at the specified indices in the array.
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void exch(int i, int j) {
        Pair temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Compares the elements at the specified indices in the array.
     * @param i the index of the first element
     * @param j the index of the second element
     * @return true if the element at index i is greater than the element at index j, false otherwise
     */
    private boolean greater(int i, int j) {
        return arr[i].value > arr[j].value;
    }
}