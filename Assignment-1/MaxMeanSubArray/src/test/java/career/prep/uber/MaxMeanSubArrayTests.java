package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MaxMeanSubArrayTests {
    @Test
    public void ex1() {
        final int[] nums = {4, 5, -3, 2, 6, 1};
        final int k = 2;
        final MaxMeanSubArray maxMean = new MaxMeanSubArray(nums, k);
        final double actual = maxMean.solveIt();
        final double expected = 4.5;
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void ex2() {
        final int[] nums = {4, 5, -3, 2, 6, 1};
        final int k = 3;
        final MaxMeanSubArray maxMean = new MaxMeanSubArray(nums, k);
        final double actual = maxMean.solveIt();
        final double expected = 3.0;
        assertEquals(expected, actual, 0.0);
    }

    /**
     * The listed answer was 1
     */
    @Test
    public void ex3() {
        final int[] nums = {1, 1, 1, 1, -1, -1, 2, -1, -1, 6};
        final int k = 3;
        final MaxMeanSubArray maxMean = new MaxMeanSubArray(nums, k);
        final double actual = maxMean.solveIt();
        final double expected = 1.3333333333333333;
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void ex4() {
        final int[] nums = {1, 1, 1, 1, -1, -1, 2, -1, -1, 6};
        final int k = 4;
        final MaxMeanSubArray maxMean = new MaxMeanSubArray(nums, k);
        final double actual = maxMean.solveIt();
        final double expected = 1.5;
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void kLessThan1() {
        final int[] nums = {1, 1, 1, 1};
        final int k = 0;
        assertThrows(IllegalArgumentException.class,
                ()-> new MaxMeanSubArray(nums, k)
        );
    }

    @Test
    public void nullArray() {
        final int[] nums = null;
        final int k = 0;
        assertThrows(IllegalArgumentException.class,
                ()-> new MaxMeanSubArray(nums, k)
        );
    }

    @Test
    public void emptyArray() {
        final int[] nums = {};
        final int k = 0;
        assertThrows(IllegalArgumentException.class,
                ()-> new MaxMeanSubArray(nums, k)
        );
    }

    @Test
    public void maxSum0() {
        final int[] nums = {-1, 1};
        final int k = 2;
        final MaxMeanSubArray maxMean = new MaxMeanSubArray(nums, k);
        final double actual = maxMean.solveIt();
        final double expected = 0.0;
        assertEquals(expected, actual, 0.0);
    }
}
