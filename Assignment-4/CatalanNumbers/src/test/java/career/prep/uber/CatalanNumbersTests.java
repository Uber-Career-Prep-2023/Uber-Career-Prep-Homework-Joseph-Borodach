package career.prep.uber;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit 5 test class to test the {@link CatalanNumbers} class.
 */
public class CatalanNumbersTests {

    @Test
    public void zeroTest() {
        final int input = 0;
        final long[] expected = {1};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneTest() {
        final int input = 1;
        final long[] expected = {1, 1};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoTest() {
        final int input = 2;
        final long[] expected = {1, 1, 2};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void threeTest() {
        final int input = 3;
        final long[] expected = {1, 1, 2, 5};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void fourTest() {
        final int input = 4;
        final long[] expected = {1, 1, 2, 5, 14};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void fiveTest() {
        final int input = 5;
        final long[] expected = {1, 1, 2, 5, 14, 42};
        final long[] actual = new CatalanNumbers().solveIt(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void negativeTest() {
        final int input = -1;
        assertThrows(IllegalArgumentException.class, () -> new CatalanNumbers().solveIt(input));
    }

    /**
     * Integer.MAX_VALUE = 2147483647
     * Half of that is 1073741823.5.
     * Anything above _ will cause a Java heap exception.
     */
    @Test
    public void tooLargeAValueTest() {
        final int input = 34;
        assertThrows(IllegalArgumentException.class, () -> new CatalanNumbers().solveIt(input));
    }

    @Test
    public void doesNotDivideByZeroTest() {
        final int input = 33;
        assertDoesNotThrow(() -> new CatalanNumbers().solveIt(input));
    }
}
