package career.prep.uber;

public class MaxMeanSubArray {
    private final int[] nums;
    private final double k;

    /**
     * @param nums
     * @param k
     */
    MaxMeanSubArray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            throw new IllegalArgumentException();
        }
        this.nums = nums;
        this.k = k;
    }

    /**
     * Fixed-size sliding window
     * Time: Worst case, O(n), linear, where n is equal to the size of the input array
     * All of the constant opperations are negligent to the overall runtime of the program
     * Space: O(1), constant
     * Unit tests are in test file
     * To write the solution took ~10
     * To write test cases probably took another ~20
     * @return
     */
    public double solveIt() {
        double cur = 0;
        for (int R = 0; R < k; R++) {
            cur += nums[R];
        }
        double max = cur;
        for (int L = 0, R = (int) k; R < nums.length; L++, R++) {
            cur += (nums[R] - nums[L]);
            max = Math.max(cur, max);
        }
        return max / k;
    }
}
