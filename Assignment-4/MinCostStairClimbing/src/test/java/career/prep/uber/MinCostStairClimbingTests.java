package career.prep.uber;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit 5 test class to test the {@link MinCostStairClimbing} class.
 */
public class MinCostStairClimbingTests {

    @Test
    public void sample1Test() {
        final int[] nums = {4, 1, 6, 3, 5, 8};
        final int expected = 9;
        final int actual = new MinCostStairClimbing().solveIt(nums);
        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final int[] nums = {11, 8, 3, 4, 9, 13, 10};
        final int expected = 25;
        final int actual = new MinCostStairClimbing().solveIt(nums);
        assertEquals(expected, actual);
    }

    /*************
     * Edge Cases.
     *************/
    @Test
    public void zero1Test() {
        final int[] nums = {0};
        assertThrows(IllegalArgumentException.class, () -> new MinCostStairClimbing().solveIt(nums));
    }

    @Test
    public void zero2Test() {
        final int[] nums = {1, 0};
        assertThrows(IllegalArgumentException.class, () -> new MinCostStairClimbing().solveIt(nums));
    }

    @Test
    public void negativeTest() {
        final int[] nums = {-1};
        assertThrows(IllegalArgumentException.class, () -> new MinCostStairClimbing().solveIt(nums));
    }
}
