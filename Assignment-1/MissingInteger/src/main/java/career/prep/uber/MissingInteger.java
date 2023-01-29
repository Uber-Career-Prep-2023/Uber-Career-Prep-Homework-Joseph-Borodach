package career.prep.uber;

import java.util.*;

public class MissingInteger {
    private final int[] nums;

    /**
     * Changing size sliding window
     * Time: O(n)
     * Space: O(n)
     * Is not in place
     * ~10 min to write solution
     * ~10 min to write tests
     *
     * Notes:
     * a) Originally, I planned a solution which wasn't in place using a queue.
     * b) But then I realized my solution would be quadratic
     * c) I really wanted a constant solution
     * @throws IllegalArgumentException if input is null
     * @param nums
     */
    MissingInteger(int[] nums) {
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
