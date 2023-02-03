package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MissingIntegerTests {

    @Test
    public void ex1() {
        final int[] nums = {1, 2, 3, 4, 6, 7};
        final MissingInteger ZeroSum = new MissingInteger(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void ex2() {
        final int[] nums = {1};
        final MissingInteger ZeroSum = new MissingInteger(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void ex3() {
        final int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12};
        final MissingInteger ZeroSum = new MissingInteger(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void ex4() {
        final int[] nums = {1, 2, 3, 4, 5, 6, 7};
        final MissingInteger ZeroSum = new MissingInteger(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final int[] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new MissingInteger(nums)
        );
    }
}
