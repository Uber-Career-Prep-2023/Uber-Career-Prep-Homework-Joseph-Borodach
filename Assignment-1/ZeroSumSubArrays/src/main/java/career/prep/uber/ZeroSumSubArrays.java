package career.prep.uber;

import java.util.Map;
import java.util.HashMap;

/**
 * Approach: Changing size sliding window
 *
 * Time: O(n), or linear, where n is equal to the size of the input array.
 * In all cases (Big O, Theta, and Omega) the alogrithm will iterate over the entire array.
 *
 * Space: O(n), or linear, where n is equal to the size of the HashMap.
 * In the worst case, the ongoing sum is always unique and therefore requires a unique key in the HashMap.
 *
 * Therefore, the algorithm is considered to be in place.
 *
 * Unit tests are in separate test file
 *
 * ~10 min to write solution
 * ~10 min to write tests
 *
 * Notes:
 * a) Originally, I planned a solution which wasn't in place using a queue.
 * b) But then I realized my solution would be quadratic
 * c) I really wanted a constant solution
 */
public class ZeroSumSubArrays {
    private final int[] nums;

    /**
     * @throws IllegalArgumentException if input is null
     * @param nums
     */
    ZeroSumSubArrays(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        this.nums = nums;
    }

    /**
     * @return
     * If input is empty, the for loop won't get off the ground.
     * a) Check if current num equals 0
     * b) Add any fittings cutoffs to the counter
     * c) Add new total to the map of cutoffs
     */
    public int solveIt() {
        int count = 0;
        Map<Integer, Integer> sums = new HashMap<>(Map.of(0, 1));
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            count += sums.getOrDefault(sum, 0);
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
