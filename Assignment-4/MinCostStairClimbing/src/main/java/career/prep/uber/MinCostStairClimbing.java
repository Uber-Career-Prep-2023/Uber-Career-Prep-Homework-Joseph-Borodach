package career.prep.uber;

/**
 * Approaches:
 * 1. Brute Force:
 *   - Compute every single possible combination
 *   Time: O(2^n), exponential.
 *      - Double check this.
 *
 * 2. Memoization - Top Down.
 *   - Cache results.
 *   Time: O(n), linear.
 *   Space: O(n), linear.
 *
 * 3. Tabulation - Bottom Up: Greedy Dynamic Programming.
 *   Original Array: {4, 1, 6, 3, 5, 8}
 *   Changed Array:  {13, 9, 11, 8, 5, 8}
 *   Time: O(n), linear.
 *   Space: O(1), constant.
 *
 * Assumed Constraints:
 *   [In an interview, these would be discussed.]
 *   nums[i] > 0
 *
 * Took ~20 Minutes.
 */
public class MinCostStairClimbing {

    private int length;
    private int[] nums;
    private int[] cache;

    public int solveIt(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Toll's array cannot be null.");
        }

        this.nums = nums;
        length = nums.length;
        cache = new int[length];

        if (length == 1) {
            verify(nums[0]);
            return nums[0];
        }
        if (length == 2) {
            verify(nums[0]);
            verify(nums[1]);
            return Math.min(nums[0], nums[1]);
        }

        int algorithm = 1;
        if (algorithm == 0) {
            return memoization();
        }
        return tabulation();
    }

    private void verify(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("nums[i] must be > 0.");
        }
    }

    /**
     * This solution assumes that you can manipulate the original input array.
     * @return
     */
    private int tabulation() {
        for (int i = length - 3; i >= 0; i--) {
            verify(nums[i]);
            verify(nums[i + 1]);
            verify(nums[i + 2]);
            nums[i] += Math.min(nums[i+1], nums[i+2]);
        }
        return Math.min(nums[0], nums[1]);
    }

    private int memoization() {
        return Math.min(dfs(0), dfs(1));
    }

    private int dfs(int i) {
        if (i >= length) {
            return 0;
        }
        if (cache[i] == 0) {
            int minCostToEnd = Math.min(dfs(i + 1), dfs(i + 2));
            cache[i] = nums[i] + minCostToEnd;
        }
        return cache[i];
    }
}