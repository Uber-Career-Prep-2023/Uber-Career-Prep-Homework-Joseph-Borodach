package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit 5 test class to test the {@link LargestSquareOf1s} class.
 */
public class LargestSquareOf1sTests {

    @Test
    public void emptyTest() {
        final char[][] input = {};
        final int expected = 0;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void oneEntry1Test() {
        final char[][] input = {{'0'}};
        final int expected = 0;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void oneEntry2Test() {
        final char[][] input = {{'1'}};
        final int expected = 1;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fourEntries1Test() {
        final char[][] input = {
                {'1', '0'},
                {'1', '1'}
        };
        final int expected = 1;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fourEntries2Test() {
        final char[][] input = {
                {'1', '1'},
                {'1', '1'}
        };
        final int expected = 2;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fourEntries3Test() {
        final char[][] input = {
                {'1', '0'},
                {'0', '1'}
        };
        final int expected = 1;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void fourEntries4Test() {
        final char[][] input = {
                {'0', '1'},
                {'1', '0'}
        };
        final int expected = 1;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void nineEntries1Test() {
        final char[][] input = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'0', '1', '1'}
        };
        final int expected = 2;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void nineEntries2Test() {
        final char[][] input = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        final int expected = 3;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void sample1Test() {
        final char[][] input = {
                {'0', '1', '0', '1'},
                {'0', '0', '1', '1'},
                {'0', '1', '1', '1'},
                {'0', '0', '1', '1'}
        };
        final int expected = 2;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final char[][] input = {
                {'0', '1', '0', '1', '1'},
                {'0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '1', '1', '0', '0'}
        };
        final int expected = 3;

        final int actual = new LargestSquareOf1s().solveIt(input);

        assertEquals(expected, actual);
    }
}
