package career.prep.uber;

import java.util.*;

/**
 * Notation:
 *   - n: The number of different coins.
 *
 * Constraint:
 *   - coins[i] > 0
 *      Logic: This is because coins cannot be
 *   - targetSum must be >= 0
 *
 * Approaches:
 * 1. Brute Force: Compute every single possible combination.
 *   - Avoid duplicate sequences by using a set of objects containing the sequences.
 *   Time: O(2^n), exponential.
 *   Space: O(n), linear - for the stack size.
 *
 * 2. Brute Force DFS:
 *   Basic Idea: For each ith coin, either include it or don't, but NEVER MOVE BACKWARDS.
 *   - Compute every single possible combination WITHOUT using a set.
 *   - The idea behind this solution is to set the stage for the Dynamic Programming solution.
 *   Time: O(2^Target Sum), exponential.
 *   Space: O(Target Sum), linear - for the stack size.
 *
 * 3. Memoization: Bottom Up.
 *   Basic Idea: Cache the number of different ways to reach the target from the given number.
 *
 *   Algorithm:
 *     Let dp[i] be the number of ways to make change for the amount i.
 *     dp[i] = sum(dp[i - coin]) for coin in coins
 *       - This means that the # of ways to make change for i is the sum of the # of ways to make change for i - coin for all coin values in the set of coins.
 *     We can create an array dp of length target + 1 & initialize it to 0.
 *     Then, we can set dp[0] = 1 as the base case.
 *     Next, for each coin value in the set of coins, we can iterate through i from coin to target and update dp[i] using the formula above.
 *
 *   Time: O(n * Target Sum).
 *   Space: O(Target Sum), .
 *
 *   I did not come up with the solution,
 *   but I thought it was very important that I spend time
 *   making sure I understand the algorithm inside & out.
 *
 *   See: https://www.geeksforgeeks.org/coin-change-dp-7/
 *
 *   Example: Coins{1, 2, 3}, Target = 5
 *             0  1  2  3  4  5
 *      coin1  1  1  1  1  1  1
 *      coin2  1  1  2  2  3  3
 *      coin3  1  1  2  3  4  5
 *
 *      Note: At Coin = 2, you can't increase the # of ways to make sums that are less than 2.
 *
 * 4. Tabulation: Top Down.
 *
 * To Implement my first solution which is titled "Brute Force: Compute every single possible combination" took 45 minutes.
 *      - It was a bad solution.
 *          - I should have been able to come up with a better solution.
 *          - This is one of those problems I need to practice more.
 *      - I implemented a few more solutions because I wanted to figure out how to implement the dynamic programming solution.
 */
public class CoinChange {

    private int length;
    private int targetSum;
    private int nCombinations;
    private Set<Integer> coins;
    private int[] coinsArray;
    private Set<Combination> combinations;

    public int solveIt(int[] coins, int targetSum) {
        if (coins == null) {
            throw new IllegalArgumentException("Coins array cannot be null.");
        }
        if (targetSum < 0) {
            throw new IllegalArgumentException("Target sum cannot be less than 0.");
        }
        if (targetSum == 0) {
            return 0;
        }
        this.nCombinations = 0;
        this.coinsArray = coins;
        this.length = coins.length;
        this.targetSum = targetSum;
        this.coins = new HashSet<>();
        this.combinations = new HashSet<>();
        for (int coin : coins) {
            if (coin < 1) {
                throw new IllegalArgumentException("Each coin must be a positive number greater than 0.");
            }
            if (this.coins.contains(coin)) {
                throw new IllegalArgumentException(" Duplicate coins are not permitted.");
            }
            this.coins.add(coin);
        }
        int algorithm = 2;

        // This is the "#1 Brute Force" solution discussed above.
        if (algorithm == 0) {
            bruteForce(new Combination());
        }
        // This is the "#2 Brute Force DFS" solution discussed above.
        else if (algorithm == 1) {
            return bruteForceDFS(0, targetSum);
        }
        // This is the "#3 Memoization: Bottom Up" solution discussed above.
        else {
            memoization();
        }
        return nCombinations;
    }

    /**
     * This is the "#1 Brute Force" solution discussed above.
     * There are always to options for each combination.
     * @param combo
     * @return
     */
    private void bruteForce(Combination combo) {
        if (combo.sum > targetSum) {
            return;
        }
        // Do NOT repeat searches.
        if (combinations.contains(combo)) {
            return;
        }
        // Avoid performing duplicate searches.
        combinations.add(new Combination(combo));
        if (combo.sum == targetSum) {
            nCombinations++;
            return;
        }
        // Backtrack.
        // The advantage of backtracking is that it only creates a copy
        // of the object if it passes the first 2 checks in the recursion.
        // In other words, it is better in terms of time & space.
        for (int coin : coins) {
            int originalCount = combo.coinsToCount.getOrDefault(coin, 0);

            // Add the coin.
            combo.sum += coin;
            combo.coinsToCount.put(coin, originalCount + 1);

            // Recurse.
            bruteForce(combo);

            // Remove the coin.
            combo.sum -= coin;
            if (originalCount == 0) {
                combo.coinsToCount.remove(coin);
            } else {
                combo.coinsToCount.put(coin, originalCount);
            }
        }
    }

    public class Combination {
        public int sum;
        public Map<Integer, Integer> coinsToCount;

        public Combination() {
            sum = 0;
            coinsToCount = new TreeMap<>();
        }

        public Combination(Combination combo) {
            sum = combo.sum;
            coinsToCount = new HashMap<>(combo.coinsToCount);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Combination)) {
                return false;
            }
            Combination other = (Combination) o;
            if (sum != other.sum) {
                return false;
            }
            return coinsToCount.equals(other.coinsToCount);
        }

        public int hashCode() {
            return coinsToCount.hashCode();
        }
    }

    /**
     * This is the "#2 Brute Force DFS" solution discussed above.
     * There are always to options for each combination.
     * @return
     */
    private int bruteForceDFS(int index, int sum) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0) {
            return 0;
        }
        if (index == length) {
            return 0;
        }
        return
                // Skip this number.
                bruteForceDFS(index + 1, sum) +
                // Include this number.
                bruteForceDFS(index, sum - coinsArray[index]);
    }

    /**
     * This is the "#3 Memoization: Bottom Up" solution discussed above.
     *
     * @return
     */
    private void memoization() {
        // dp[i] will be storing the number of solutions for value i.
        // We need sum+1 rows as the table is constructed in bottom up manner using the base case (sum = 0)
        long[] nWaysToMakeSum = new long[targetSum + 1];

        // Base case (If given value is 0)
        nWaysToMakeSum[0] = 1;

        // Pick all coins 1 by 1 & update the dp[] values after the index
        // greater than or equal to the value of the picked coin.
        for (int coin = 0; coin < length; coin++) {

            int value = coinsArray[coin];

            System.out.println("\nValue: " + value);

            for (int index = value; index <= targetSum; index++) {

                System.out.println("nWaysToMakeSum[" + index + "]: " + nWaysToMakeSum[index]);

                nWaysToMakeSum[index] += nWaysToMakeSum[index - value];

                System.out.println("nWaysToMakeSum[" + index + "]: " + nWaysToMakeSum[index] + "\n");

            }
        }
        nCombinations = (int) nWaysToMakeSum[targetSum];
    }
}