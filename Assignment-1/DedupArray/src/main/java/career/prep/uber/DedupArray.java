package career.prep.uber;
/**
 * Changing size sliding window
 *
 * Time: O(n), where n is the size of the input array.
 *      1) In the worst case, every element is a duplicate, but the the array will still be traveresed only 1 time!
 *      2) There are numerous constant operations which are negligent to the overall runtime of the program, c.
 *      - Therefore, 2n + c = O(n)
 *
 * Space: O(1), this was one of the requirements.
 *
 * Therefore, is in place.
 *
 * ~10 min to write solution
 * ~10 min to write tests
 */
public class DedupArray {
    private final int[] nums;

    /**
     * @throws IllegalArgumentException if input is null
     * @param nums
     */
    DedupArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        this.nums = nums;
    }

    /**
     * @return
     */
    public int[] solveIt() {
        int len = nums.length;
        for (int L = 0, R = 1; R < len; R++) {
            if (nums[R] < nums[L]) {
                throw new IllegalArgumentException();
            }
            if (nums[R] != nums[L]) {
                nums[++L] = nums[R];
            }
            if (R != L) {
                nums[R] = -1;
            }
        }
        return nums;
    }
}