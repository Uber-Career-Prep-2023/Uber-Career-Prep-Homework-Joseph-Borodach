package career.prep.uber;

import java.util.*;

/**
 * Solutions
 * Approach #1:
 * - Idea:
 *      1) Sort 2-dimensional array
 *      2) Merge in places using 2 pointers.
 * - Time: O(n log n), or linearithmic, where n is equal to the size of the input array.
 *      - In the worst case, no intervals can be merged:
 *          1) The array is sorted, n log n.
 *          2) The entire array must be iterated over without merging any intervals, n.
 *          3) All of the constant opperations are negligent to the overall runtime of the program == c.
 *      - Overall runtime == n log n + n + c == O(n log n)
 *
 * - Space: O(n), or linear.
 *      - In the worst case, is only one merge and we must create a 2nd two dimensional array of size n - 1.
 *
 * - Therefore, the algorithm is NOT considered to be in place.
 *
 * Unit tests are in separate test file.
 *
 * To write the solution took ~10
 * To write test cases probably took another ~30
 */
public class MergeIntervals {
    private final int[][] nums;

    /**
     * @throws IllegalArgumentException if input is null
     * @param nums
     */
    MergeIntervals(int[][] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        this.nums = nums;
    }

    /**
     * @return
     */
    public int[][] solveIt() {
        Arrays.sort(nums, (a, b) -> Integer.compare(a[0], b[0]));
        int L = 0;
        for (int R = 1; R < nums.length; R++) {
            if (nums[R][0] <= nums[L][1]) {
                nums[L][1] = Math.max(nums[R][1], nums[L][1]);
            } else {
                nums[++L] = nums[R];
            }
        }
        int[][] compressed = new int[L+1][2];
        for (int i = 0; i <= L; i++) {
            compressed[i] = nums[i];
        }
        return compressed;
    }
}