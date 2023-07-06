package career.prep.uber;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit 5 test class to test the {@link CoinChangeTests} class.
 */
public class CoinChangeTests {

    @Test
    public void oneCoinTest() {
        final int[] coins = {1};

        final int targetSum = 3;

        final int expected = 1;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    @Test
    public void twoCoins1Test() {
        final int[] coins = {1, 2};

        final int targetSum = 2;

        final int expected = 2;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    @Test
    public void twoCoins2Test() {
        final int[] coins = {1, 2};

        final int targetSum = 3;

        final int expected = 2;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    @Test
    public void sample1Test() {
        final int[] coins = {2, 5, 10};

        final int targetSum = 20;

        final int expected = 6;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final int[] coins = {2, 5, 10};

        final int targetSum = 15;

        final int expected = 3;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    /**
     * Edge Cases
     */
    @Test
    public void zeroSumTest() {
        final int[] coins = {1};

        final int targetSum = 0;

        final int expected = 0;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }

    @Test
    public void zeroCoinsTest() {
        final int[] coins = {};

        final int targetSum = 1;

        final int expected = 0;

        final int actual = new CoinChange().solveIt(coins, targetSum);

        assertEquals(expected, actual);
    }
}
