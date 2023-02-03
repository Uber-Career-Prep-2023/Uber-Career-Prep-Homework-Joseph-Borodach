package career.prep.uber;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MergeIntervalsTests {

    private void makeAssertions(final int[][] expected, final int[][] actual) {
        Arrays.sort(actual, Comparator.comparingDouble(o -> o[0]));
        Arrays.sort(expected, Comparator.comparingDouble(o -> o[0]));
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i][0], actual[i][0]);
            assertEquals(expected[i][1], actual[i][1]);
        }
    }
    @Test
    public void ex1() {
        final int[][] nums = {{2, 3}, {4, 8}, {1, 2}, {5, 7}, {9, 12}};
        final MergeIntervals ZeroSum = new MergeIntervals(nums);
        final int[][] actual = ZeroSum.solveIt();
        final int[][] expected = {{4, 8}, {1, 3}, {9, 12}};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex2() {
        final int[][] nums = {{5, 8}, {6, 10}, {2, 4}, {3, 6}};
        final MergeIntervals ZeroSum = new MergeIntervals(nums);
        final int[][] actual = ZeroSum.solveIt();
        final int[][] expected = {{2, 10}};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex3() {
        final int[][] nums = {{10, 12}, {5, 6}, {7, 9}, {1, 3}};
        final MergeIntervals ZeroSum = new MergeIntervals(nums);
        final int[][] actual = ZeroSum.solveIt();
        final int[][] expected = {{10, 12}, {5, 6}, {7, 9}, {1, 3}};
        makeAssertions(expected, actual);
    }

    @Test
    public void nullString() {
        final int[][] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new MergeIntervals(nums)
        );
    }
}
