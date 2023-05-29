package career.prep.uber;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link MergeKSortedArrays} class.
 */
public class MergeKSortedArraysTests {

    @Test
    public void sample1Test() {
        final int[][] arrays = {{1, 2, 3, 4, 5},{1, 3, 5, 7, 9}};

        final int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 7, 9};

        final int[] actual = new MergeKSortedArrays().solveIt(arrays);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final int[][] arrays = {{1, 4, 7, 9}, {2, 6, 7, 10, 11, 13, 15}, {3, 8, 12, 13, 16}};

        final int[] expected = {1, 2, 3, 4, 6, 7, 7, 8, 9, 10, 11, 12, 13, 13, 15, 16};

        final int[] actual = new MergeKSortedArrays().solveIt(arrays);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void only1ArrayTest() {
        final int[][] arrays = {{1, 2, 3}};

        final int[] expected = {1, 2, 3};

        final int[] actual = new MergeKSortedArrays().solveIt(arrays);

        assertArrayEquals(expected, actual);
    }

    // Not that negative number should particularly make a difference here.
    @Test
    public void negativeNumbersTest() {
        final int[][] arrays = {{1, 2, 3},{-1, -2, -3}};

        final int[] expected = {-1, -2, -3, 1, 2, 3};

        final int[] actual = new MergeKSortedArrays().solveIt(arrays);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void nullSubArrayTest() {
        final int[][] arrays = {{1, 2, 3}, null};

        assertThrows(IllegalArgumentException.class, () -> new MergeKSortedArrays().solveIt(arrays));
    }

    @Test
    public void emptyArrayTest() {
        final int[][] arrays = {};

        assertThrows(IllegalArgumentException.class, () -> new MergeKSortedArrays().solveIt(arrays));
    }

    @Test
    public void emptySubArrayTest() {
        final int[][] arrays = {{1, 2, 3}, {}};

        assertThrows(IllegalArgumentException.class, () -> new MergeKSortedArrays().solveIt(arrays));
    }
}