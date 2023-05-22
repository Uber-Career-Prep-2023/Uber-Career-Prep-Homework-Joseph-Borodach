package career.prep.uber;

import java.util.NoSuchElementException;

/**
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
 *      1. Brute force solution: Add the number to the end of the array, hit "Arrays.sort"
 *          - Insertion and removal time would O(n).
 *          - I assumed that this is not what an interview would want to be implemented.
 *
 *      Note: I only wrote the documentation for approach 2 because I forgot about approach 3.
 *          [You can skip to approach 3.]
 *
 *      2. Using binary search: From here there are fundamentally 2 different possible approaches:
 *          a. Have a pointer to the last index used.
 *              Maintain the array sorted in descending order.
 *              Insert:
 *                  - Perform binary search, and shift all numbers greater than the current one to the right.
 *                  - time: O(log n + n) = O(n), linear.
 *                      - [O(log n) is for the binary search and O(n) is for shifting the elements.
 *                      - This runtime is the issue, its terrible and is the reason why we need approach "b".
 *                      - When: If the element inserted is the greatest number.
 *              Delete from top:
 *                  - Zero out the right most number and move the right pointer to the left one index.
 *                  - time: O(1), constant.
 *          b. Have pointers to the first and last indexes used.
 *              Insert:
 *                  - Use binary search to locate where the element should be inserted.
 *                  - Shift numbers:
 *                      - If both pointers have reached the ends, grow array.
 *                      - If either pointer has reached its end, shift the numbers of the other side.
 *                      - If there are fewer elements to the left, then shift the left numbers over one.
 *                      - Otherwise, shift the right numbers over one index to the right.
 *                  - time: Complicated
 *                      - O(log n + n/2) = O(log n)
 *                      - We should never need to shift more than half of the elements as long as when we grow the array, we add the elements to the middle of the array.
 *              Delete from top:
 *                  - Zero out the left most number and move the left pointer to the right one index.
 *                  - time: O(1), constant.
 *      3. Use a binary tree
 *          Insertion: Insert an element at the end of the right pointer
 *              Swim the child up until it arrives at a parents less than itself.
 *              time: O(log n), linear, because need to compare itself to O(log n) parent nodes.
 *          Remove top: Remove the number at index 1 and sink down the element.
 *              time: O(log n), linear, because need to sink the number down to the bottom of the tree.
 */
public class Heap {
    private int n;
    private int[] arr;

    public Heap() {
        this(1);
    }

    /**
     * @param initialCapacity
     * "+ 1" because the numbers will start add index 1
     */
    public Heap(int initialCapacity) {
        arr = new int[initialCapacity + 1];
        n = 0;
    }

    /**
     * The smallest element is at index 1, and the largest element is at index n.
     * returns the min (top) element in the heap.
     * @throws NoSuchElementException if heap is empty.
     * @time: O(1), constant.
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return arr[1];
    }

    /**
     * adds int x to the heap in the appropriate position
     * @param x
     * @time: O(log n), logarithmic.
     */
    public void insert(int x) {
        // double array size if necessary.
        if (n == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[++n] = x;
        swim(n);
    }

    /**
     * removes the min (top) element in the heap
     * @throws NoSuchElementException if heap is empty.
     * @time: O(log n), logarithmic.
     */
    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        exch(1, n--);
        sink(1);

        // erase the element - this is not critical
        arr[n + 1] = Integer.MAX_VALUE;

        // Shrink array
        if (n > 0 && n == (arr.length - 1) / 4) {
            resize(arr.length / 2);
        }
    }

    /*****************
     * Helper Methods.
     *****************/
    /**
     * @return if the heap is empty.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @param capacity to resize the array to.
     * @time: O(capacity + n) = O(capacity).
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

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            // swap child with parent
            exch(k/2, k);
            k = k/2;
        }
    }

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

    private void exch(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean greater(int i, int j) {
        return arr[i] > arr[j];
    }
}