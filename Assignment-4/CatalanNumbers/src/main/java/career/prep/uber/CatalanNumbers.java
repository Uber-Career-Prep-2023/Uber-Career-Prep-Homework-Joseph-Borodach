package career.prep.uber;

/**
 * CatalanNumbers class calculates the Catalan numbers using the formula:
 * (2n)! / (n+1)!n!
 *
 * Math Notes:
 *      Factorial of 0 is 1.
 *
 * Approaches:
 * 1. Brute Force:
 *   - Go from 0 to n, calculating the Catalan Number.
 *   Time: O(n!), exponential.
 *
 * 2. Recursive Dynamic Programming:
 *   - Store the factorials.
 *   Algorithm:
 *      getFactorial(num):
 *          if cache[num] != 0:
 *              return cache[num]
 *          cache[num] = num * getFactorial(num - 1)
 *      For each number:
 *          getFactorial(2n) / getFactorial(n+1) * getFactorial(n)
 *
 * Let N = n + 1
 * Time: O((iterating over N nums) + (performing 2 * N multiplications)) = O(N), linear.
 * Space: O(N), linear.
 *   [Note: Did not include the return array in the space complexity.
 *          Regardless, it would not influence the Big-O.]
 *
 * Note: The current implementation supports input numbers up to 33, as factorial calculations
 * may exceed the range of the long data type for larger numbers.
 *
 * After I finished, I found this article: It provides a couple solutions I did not think of.
 *  - https://www.codingninjas.com/studio/library/catalan-numbers
 */
public class CatalanNumbers {
    private long[] factorials;

    /**
     * Calculates the Catalan numbers for a given input number.
     *
     * @param num The input number.
     * @return An array containing the Catalan numbers up to the given input number.
     * @throws IllegalArgumentException if the number is negative or larger than 33.
     */
    public long[] solveIt(final int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Number cannot be negative.");
        }
        if (num > 33) {
            throw new IllegalArgumentException("Number cannot be larger than 33.");
        }
        final int length = num + 1;
        long[] catalanNumbers = new long[length];

        final int largestNumber = (num == 0) ? 2 : num * 2;
        factorials = new long[largestNumber + 1];
        factorials[0] = 1;

        for (int i = 0; i <= num; i++) {
            long dividend = getFactorial(2 * i);
            long divisor = getFactorial(i + 1) * getFactorial(i);
            catalanNumbers[i] = dividend / divisor;
        }
        return catalanNumbers;
    }

    /**
     * Calculates the factorial of a given number.
     *
     * @param num The number for which to calculate the factorial.
     * @return The factorial value of the given number.
     */
    private long getFactorial(final int num) {
        if (factorials[num] == 0) {
            factorials[num] = num * getFactorial(num - 1);
        }
        return factorials[num];
    }
}