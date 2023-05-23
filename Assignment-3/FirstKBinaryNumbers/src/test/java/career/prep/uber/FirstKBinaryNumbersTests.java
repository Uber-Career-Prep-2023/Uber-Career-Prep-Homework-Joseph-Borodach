package career.prep.uber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * JUnit 5 test class to test the {@link FirstKBinaryNumbers} class.
 */
public class FirstKBinaryNumbersTests {
    @Test
    public void sample1Test() {
        final int k = 5;

        final String[] expected = {"0", "1", "10", "11", "100"};

        final String[] actual = new FirstKBinaryNumbers().solveIt(k);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final int k = 10;

        final String[] expected = {"0", "1", "10", "11", "100", "101", "110", "111", "1000", "1001"};

        final String[] actual = new FirstKBinaryNumbers().solveIt(k);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void numberOneTest() {
        final int k = 1;

        final String[] expected = {"0"};

        final String[] actual = new FirstKBinaryNumbers().solveIt(k);

        assertArrayEquals(expected, actual);
    }

}
