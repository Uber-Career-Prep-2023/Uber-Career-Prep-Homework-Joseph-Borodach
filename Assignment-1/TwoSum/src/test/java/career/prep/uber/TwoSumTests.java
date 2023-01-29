package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TwoSumTests {

    @Test
    public void ex1() {
        final int[] nums = {4, 5, 2, -1, -3, -3, 4, 6, -7};
        final TwoSum ZeroSum = new TwoSum(nums);
        final int actual = ZeroSum.solveIt();
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final int[] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new TwoSum(nums)
        );
    }
}
