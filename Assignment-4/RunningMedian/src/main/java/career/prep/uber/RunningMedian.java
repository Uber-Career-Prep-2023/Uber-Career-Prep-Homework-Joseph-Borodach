package career.prep.uber;

import java.util.PriorityQueue;

/**
 * I'll admit: I did this problem recently.
 *
 * Approaches:
 * 1. Brute Force:
 *   - Use an ArrayList.
 *   - For each number, add it to the end of the list and sort the list.
 *   Complexities on a per-operation basis:
 *      Time: O(log n), logarithmic.
 *      Space: O(1), constant.
 *   Overall program complexity:
 *      Time: O(n log n), linearithmic.
 *      Space: O(n), linear.
 * 2. Two Priority Queues: Min & Max
 *   Complexities on a per-operation basis:
 *      Time: O(log n), logarithmic.
 *      Space: O(1), constant.
 *   Overall program complexity:
 *      Time: O(n log n), linearithmic.
 *      Space: O(n), linear.
 * 3. Use binary search in a growing array to locate insertion location.
 * 4. Self-Balancing Binary Search Trees: Use self-balancing binary search trees, such as AVL tree or Red-Black tree, to efficiently maintain the sorted order of the numbers. This approach can achieve a time complexity of O(n log n) and a space complexity of O(n).
 * 5. Use Bucket Sort.
 *
 * This implementation took approximately 15 minutes to complete.
 */
public class RunningMedian {
    private PriorityQueue<Integer> minQueue;
    private PriorityQueue<Integer> maxQueue;

    /**
     * Constructs a RunningMedian object.
     * Initializes the min-heap and max-heap priority queues.
     */
    public RunningMedian() {
        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>((x, y) -> y - x);
    }

    /**
     * Adds a new number to the running median calculation and returns the updated median.
     *
     * @param num the new number to be added
     * @return the updated running median
     */
    public double solveIt(int num) {
        if (maxQueue.isEmpty() || num <= maxQueue.peek()) {
            maxQueue.offer(num);
        } else {
            minQueue.offer(num);
        }

        if (maxQueue.size() > minQueue.size() + 1) {
            int numToSwap = maxQueue.poll();
            minQueue.offer(numToSwap);
        }
        else if (maxQueue.size() < minQueue.size()) {
            int numToSwap = minQueue.poll();
            maxQueue.offer(numToSwap);
        }

        // Take the average of the two middle numbers.
        if (minQueue.size() == maxQueue.size()) {
            return (minQueue.peek() + maxQueue.peek()) / 2.0;
        }
        return maxQueue.peek();
    }
}