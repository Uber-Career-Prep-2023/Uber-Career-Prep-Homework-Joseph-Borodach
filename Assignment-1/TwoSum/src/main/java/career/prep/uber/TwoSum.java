package career.prep.uber;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Approach #1: Hash the Running Computation
 * - Time: O(n), or linear, where n is the size of the input array.
 *      1) The array is traversed one time, n
 *      2) There are numerous constant operation which are negligent to the overall runtime of the program, c.
 *      Therefore, n + c == O(n)
 *
 * - Space: O(n), or linear, where each value in the array is unique and therefore requires its own key in the HashMap.
 *
 * - Therefore, is in not place.
 *
 * Approach #2: Space Optimizations
 * - Idea
 *      1) Sort input array.
 *      2) Have left and right pointers.
 *      3) If the current sum is too small, move left.
 *      4) If the current sum is too large, move right.
 *      5) If they are equal, then move pointers which are equal to current pointers.
 *
 * - Time: O(n log n), or linearithmic, where n is the size of the input array.
 *      1) The array is sorted one time, n log n.
 *      2) The array is iterated over, by the left and right pointers, n.
 *      3) There are numerous constant operation which are negligent to the overall runtime of the program, c.
 *      Therefore, n log n + n + c == O(n log n)
 *
 * - Space: O(1), or constant, there is no space taken up in addition to the input array that is not considered a constant addition.
 *
 * - Therefore, is in place.
 *
 * ~30 min to write solution
 * ~10 min to write tests
 */
public class TwoSum {
    private final int[] a;
    private final int k;

    /**
     * @throws IllegalArgumentException if input is null
     * @param nums
     * @param k
     */
    TwoSum(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        this.k = k;
        a = nums;
    }

    /**
     * @return
     */
    public int solveIt() {
        int i = 1;
        if (i == 0) {
            return hash(a, k);
        }
        return sorted(a, k);
    }

    /**
     * Approach #1: Hash the Running Computation
     * @return
     */
    private int hash(int[] a, int k) {
        int count = 0;
        Map<Integer, Integer> sums = new HashMap<>();
        for (int num : a) {
            count += sums.getOrDefault(k - num, 0);
            sums.put(num, sums.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    /**
     * Approach #2: Space Optimizations
     * @return
     */
    private int sorted(int[] a, int k) {
        Arrays.sort(a);
        int count = 0;
        for (int L = 0, R = a.length - 1; L < R; ) {
            int s = a[R] + a[L];
            if (s == k) {
                count++;
                if (L+1 != R && a[R] == a[L]) {
                    count++;
                }
            }
            if (s < k || a[L] == a[L+1]) {
                L++;
            } else {
                R--;
            }
        }
        return count;
    }
}