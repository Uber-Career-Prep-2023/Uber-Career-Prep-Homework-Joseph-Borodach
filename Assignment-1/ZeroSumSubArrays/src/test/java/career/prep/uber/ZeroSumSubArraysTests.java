package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ZeroSumSubArraysTests {

    @Test
    public void ex1() {
        final int[] nums = {4, 5, 2, -1, -3, -3, 4, 6, -7};
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void ex2() {
        final int[] nums = {1, 8, 7, 3, 11, 9};
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 0;
        assertEquals(expected, actual);
    }

    /**
     * 8, 3, 3, 1, 3
     * {0}
     * {8, -5, 0, -2, 3}
     */
    @Test
    public void ex3() {
        final int[] nums = {8, -5, 0, -2, 3, -4};
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 2;
        assertEquals(expected, actual);
    }

    /**
     * The ans is 7, not 6!
     * Combos:
     * 1) {0}
     * 2) {2, -2}
     * 3) {1, 2, -2, -1}
     * 4) {0, 1, 2, -2, -1}
     * 5) {0}
     * 6) {1, 2, -2, -1, 0}
     * 7) {0, 1, 2, -2, -1, 0}
     */
    @Test
    public void ex4a() {
        final int[] nums = {0, 1, 2, -2, -1, 0};
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 7;
        assertEquals(expected, actual);
    }

    /**
     * The ans is 7, not 6!
     * Combos:
     * 1) {0}
     * 2) {2, -2}
     * 3) {1, 2, -2, -1}
     * 4) {0, 1, 2, -2, -1}
     *
     * 5) {0}
     * 6) {1, 2, -2, -1, 0}
     * 7) {0, 1, 2, -2, -1, 0}
     *
     * 8) {0}
     * 9) {0, 0}
     *
     * 10) {-1, 0, 0, 1}
     * 11) {-2, -1, 0, 0, 1, 2}
     * 12) {2, -2, -1, 0, 0, 1, 2, -2}
     * 13) {1, 2, -2, -1, 0, 0, 1, 2, -2, -1}
     *
     * 14) {1, 2, -2, -1, 0, 0}
     * 15) {0, 1, 2, -2, -1, 0, 0}
     *
     * 16) {4, -4}
     *
     * 17) {3, 4, -4, -3}
     * 18) {0, 3, 4, -4, -3}
     * 19) {0, 0, 3, 4, -4, -3}
     * 20) {1, 2, -2, -1, 0, 0, 3, 4, -4, -3}
     * 21) {0, 1, 2, -2, -1, 0, 0, 3, 4, -4, -3}
     *
     * 22) {0}
     * 23) {1, 2, -2, -1, 0}
     * 24) {0, 1, 2, -2, -1, 0}
     * 25) {0, 0, 1, 2, -2, -1, 0}
     * 26) {1, 2, -2, -1, 0, 0, 1, 2, -2, -1, 0}
     * 27) {0, 1, 2, -2, -1, 0, 0, 1, 2, -2, -1, 0}
     */
    @Test
    public void ex4b() {
        //                 {0, 1, 2,  3, 4,  5, 6, 7, 8,  9, 10, 11
        final int[] nums = {0, 1, 2, -2, -1, 0, 0, 1, 2, -2, -1, 0};
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 28;
        assertEquals(expected, actual);
    }

    @Test
    public void ex5() {
        final int n = 10;
        final int h = n/2;
        final int[] nums = new int[n];
        for (int i = 0; i < h; i++) {
            nums[i] = i;
            System.out.println(i);
        }
        for (int i = h, j = -(h-1); i < n; i++, j++) {
            nums[i] = j;
            System.out.println(j);
        }
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = h+4;
        assertEquals(expected, actual);
    }

    @Test
    public void ex5a() {
        final int n = 1_000_000;
        final int h = n/2;
        final int[] nums = new int[n];
        for (int i = 0; i < h; i++) {
            nums[i] = i;
        }
        for (int i = h, j = -(h-1); i < n; i++, j++) {
            nums[i] = j;
        }
        final ZeroSumSubArrays ZeroSum = new ZeroSumSubArrays(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = h+4;
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final int[] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new ZeroSumSubArrays(nums)
        );
    }
}
