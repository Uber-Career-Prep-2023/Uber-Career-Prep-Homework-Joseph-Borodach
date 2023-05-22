package career.prep.uber;

import java.util.NoSuchElementException;

/**
 * @author Joseph Borodach
 *
 * Question 2: Write a min heap class according to the following API using an array as the underlying data structure.
 *      (A max heap has the same implementation; you simply need to flip the direction of the comparators.)
 *      For simplicity, you can assume that the heap holds integers rather than generic comparables.
 *
 * Note: This is the same idea as creating a priority queue for just integers.
 *
 * Clarifications:
 *      You must use an array.
 *
 * Design:
 *      The issue is you never know how many elements will be added to the heap.
 *      Accordingly, you have to make the heap such that you can grow the array if it gets too large.
 *      When the array is full, will create an array 2x the size and copy the numbers over
 *
 * Approaches:
 * 1. Brute force solution: Add the number to the end of the array and sort it using Arrays.sort().
 *    - Insertion and removal time would be O(n).
 *    - This approach is not recommended as it has poor performance.
 *
 * 2. Using binary search:
 *    a. Maintain a pointer to the last index used and keep the array sorted in descending order.
 *       - Insertion:
 *         - Perform binary search to find the correct position and shift all numbers greater than the current one to the right.
 *         - Time complexity: O(log n + n) = O(n) (logarithmic for binary search and linear for shifting elements)
 *         - This approach has poor runtime for insertion when the inserted element is the greatest number.
 *       - Deletion from the top:
 *         - Zero out the rightmost number and move the right pointer to the left by one index.
 *         - Time complexity: O(1) (constant time)
 *
 *    b. Maintain pointers to the first and last indexes used.
 *       - Insertion:
 *         - Use binary search to locate the position where the element should be inserted.
 *         - Shift numbers accordingly:
 *           - If both pointers have reached the ends, grow the array.
 *           - If either pointer has reached its end, shift the numbers on the other side.
 *           - If there are fewer elements on the left, shift the left numbers over by one.
 *           - Otherwise, shift the right numbers one index to the right.
 *         - Time complexity: Complicated but generally O(log n + n/2) = O(log n) (logarithmic for binary search)
 *           - Shifting should not require more than half of the elements to be shifted if elements are added to the middle during array growth.
 *       - Deletion from the top:
 *         - Zero out the leftmost number and move the left pointer to the right by one index.
 *         - Time complexity: O(1) (constant time)
 *
 * 3. Using a binary tree:
 *    - Insertion: Insert an element at the end of the right pointer and swim it up until it reaches a parent less than itself.
 *      - Time complexity: O(log n) (logarithmic time as it needs to compare with parent nodes)
 *    - Deletion from the top: Remove the number at index 1 and sink down the element.
 *      - Time complexity: O(log n) (logarithmic time as it needs to sink the number down to the bottom of the tree)
 */
public class Heap {
    private int n;
    private int[] arr;

    /**
     * Constructs an empty heap with initial capacity 1.
     */
    public Heap() {
        this(1);
    }

    /**
     * Constructs an empty heap with the specified initial capacity.
     * @param initialCapacity the initial capacity of the heap.
     * @throws if initialCapacity is less than zero.
     */
    public Heap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity cannot be negative.");
        }
        arr = new int[initialCapacity + 1];
        n = 0;
    }

    /**
     * Returns the smallest element (top) in the heap.
     * @return the smallest element in the heap
     * @throws NoSuchElementException if the heap is empty
     * @time complexity: O(1) (constant time)
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return arr[1];
    }

    /**
     * Adds an integer to the heap in the appropriate position.
     * @param x the integer to be inserted
     * @time complexity: O(log n) (logarithmic time)
     */
    public void insert(int x) {
        // Double the array size if necessary.
        if (n == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[++n] = x;
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
        arr[n + 1] = Integer.MAX_VALUE;

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
        int[] temp = new int[capacity];
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
        int temp = arr[i];
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
        return arr[i] > arr[j];
    }
}