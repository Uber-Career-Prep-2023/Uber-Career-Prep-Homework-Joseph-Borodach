package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DedupArrayTests {

    private void makeAssertions(final int[] expected, final int[] actual) {
        for (int i = 0; i < expected.length; i++) {
            //System.out.println(expected[i]);
            //System.out.println(actual[i]);
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void ex1() {
        final int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {1, 2, 3, 4, -1, -1, -1, -1, -1};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex2() {
        final int[] nums = {0, 0, 1, 4, 5, 5, 5, 8, 9, 9, 10, 11, 15, 15};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {0, 1, 4, 5, 8, 9, 10, 11, 15, -1, -1, -1, -1, -1};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex3() {
        final int[] nums = {1, 3, 4, 8, 10, 12};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {1, 3, 4, 8, 10, 12};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex4() {
        final int[] nums = {1, 1, 1, 1, 1};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {1, -1, -1, -1, -1};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex5() {
        final int[] nums = {1, 1};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {1, -1};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex6() {
        final int[] nums = {1};
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        final int[] expected = {1};
        makeAssertions(expected, actual);
    }

    @Test
    public void ex7() {
        final int n = 500_000_000;
        int[] nums = new int[n];
        for (int i = 0, c = 0; i < n; i += 2, c++) {
            nums[i] = c;
            nums[i+1] = c;
        }
        final DedupArray DA = new DedupArray(nums);
        final int[] actual = DA.solveIt();
        int[] expected = new int[n];
        final int h = n/2;
        for (int i = 0; i < h; i++) {
            expected[i] = i;
        }
        for (int i = h; i < n; i++) {
            expected[i] = -1;
        }
        makeAssertions(expected, actual);
    }

    @Test
    public void nullString() {
        final int[] nums = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new DedupArray(nums)
        );
    }

    @Test
    public void unsorted() {
        final int[] nums = {2, 1};
        final DedupArray DA = new DedupArray(nums);
        assertThrows(IllegalArgumentException.class,
                ()-> DA.solveIt()
        );
    }
}
