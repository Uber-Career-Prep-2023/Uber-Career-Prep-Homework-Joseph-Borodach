package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TwoSumTests {

    /**
     * {1, 10, 8, 3, 2, 5, 7, 2, -2, -1}
     * {(2, 8), (3, 8), (3, 7)}
     */
    @Test
    public void ex1() {
        final int[] nums = {1, 10, 8, 3, 2, 5, 7, 2, -2, -1};
        final int k = 10;
        final TwoSum TS = new TwoSum(nums, k);
        final int actual = TS.solveIt();
        final int expected = 3;
        assertEquals(expected, actual);
    }

    /**
     * {1, 10, 8, 3, 2, 5, 7, 2, -2, -1}
     * {(10, -2), (3, 5), (1, 7)}
     */
    @Test
    public void ex2() {
        final int[] nums = {1, 10, 8, 3, 2, 5, 7, 2, -2, -1};
        final int k = 8;
        final TwoSum TS = new TwoSum(nums, k);
        final int actual = TS.solveIt();
        final int expected = 3;
        assertEquals(expected, actual);
    }

    /**
     * {4, 3, 3, 5, 7, 0, 2, 3, 8, 6}
     * {(0, 6), (4, 2), (3, 3), (3, 3), (3, 3)}
     */
    @Test
    public void ex3() {
        final int[] nums = {4, 3, 3, 5, 7, 0, 2, 3, 8, 6};
        final int k = 6;
        final TwoSum TS = new TwoSum(nums, k);
        final int actual = TS.solveIt();
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void negativeK() {
        final int[] nums = {-1, -5, 5, 6, 7, -1};
        final int k = -6;
        final TwoSum TS = new TwoSum(nums, k);
        final int actual = TS.solveIt();
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final int[] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new TwoSum(nums, 1)
        );
    }
}
