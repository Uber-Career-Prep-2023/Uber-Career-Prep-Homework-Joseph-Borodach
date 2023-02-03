package career.prep.uber;
/**
 * Approach: Binary Search Variation
 *
 * The Idea: Cut down the range in half where we know the that the imbalance is until only 2 indexes remain and we know it's the one on the left.
 *
 * Time: O(log n), or logarithmic:
 * 1) log n because it takes log n iterations of constant work to reach a range of size 2.
 * 2) The constant operations are negligent to the overall runtime of the program, c.
 * Therefore, log n + c == O(log n).
 *
 * Space: O(1), or constant:
 * Because there is no additional space taken up in adition to the input integer array.
 *
 * Therefore, the algorithm is considered to be in place.
 *
 * Unit tests are in separate test file.
 *
 * ~20 min to write solution
 * ~10 min to write tests
 *
 * Notes:
 *
 */
public class MissingInteger {
    private final int len;
    private final int[] nums;

    /**
     * @throws IllegalArgumentException if input is null
     * @param nums
     */
    MissingInteger(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        this.nums = nums;
        len = nums.length;
    }

    /**
     * @return
     */
    public int solveIt() {
        if (nums[0] != 1) {
            return 1;
        }
        if (nums[len - 1] != len + 1) {
            return len + 1;
        }
        int L = 0, R = len - 1;
        while ((R - L) > 1) {
            int mid = (R + L) / 2;
            if ((nums[L] - L) != (nums[mid] - mid)) {
                R = mid;
            } else if ((nums[R] - R) != (nums[mid] - mid)) {
                L = mid;
            }
        }
        return nums[L] + 1;
    }
}