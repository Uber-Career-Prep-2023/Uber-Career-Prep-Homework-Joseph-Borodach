package career.prep.uber;

/**
 * Approach: Fixed-size sliding window.
 *
 * Time: O(n), or linear, where n is equal to the size of the input array.
 * In the worst case, k = 1:
 * 1) The entire array is iterated over one time == n.
 * 2) All of the constant opperations are negligent to the overall runtime of the program == c.
 * Overall runtime == n + c == O(n)
 *
 * Space: O(1), or constant.
 * Because: The only space used in addition to the input array are constant additions.
 *
 * Therefore, the algorithm is considered to be in place.
 *
 * Unit tests are in separate test file.
 *
 * To write the solution took ~10
 * To write test cases probably took another ~20
 */
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
     * @return
     */
    public double solveIt() {
        double sum = 0;
        for (int R = 0; R < k; R++) {
            sum += nums[R];
        }
        double max = sum;
        for (int L = 0, R = (int) k; R < nums.length; L++, R++) {
            sum += (nums[R] - nums[L]);
            max = Math.max(sum, max);
        }
        return max / k;
    }
}