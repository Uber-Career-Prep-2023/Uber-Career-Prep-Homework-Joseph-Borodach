package career.prep.uber;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * JUnit 5 test class to test the {@link NumberOfIslands} class.
 */
public class NumberOfIslandsTests {

    private int[][] island;

    @Test
    public void oneIslandTest() {
        island = new int[][]{
                {1, 0, 0},
                {0, 0, 0}
        };

        makeAssertion(1);
    }

    @Test
    public void threeIslandsTest() {
        island = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };

        makeAssertion(3);
    }

    @Test
    public void emptyIslandTest() {
        island = new int[][]{};

        makeAssertion(0);
    }

    @Test
    public void oneLargeIslandTest() {
        island = new int[][]{
                {1, 1},
                {1, 1}
        };
        makeAssertion(1);
    }

    private void makeAssertion(final int expected) {
        final int actual = new NumberOfIslands().solveIt(island);

        assertEquals(expected, actual);
    }
}