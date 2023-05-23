package career.prep.uber;

/**
 * @author josephborodach
 *
 * Question 5: Given a number, k, return an array of the first k binary numbers, represented as strings.
 *
 * My reaction to the problem: Excited! I haven't done something like this!
 *
 * For this problem, I wrote several solutions because I wanted to learn from implementing them.
 *      - The algorithm that the integer "alogirthm" was set to be the one I handed in.
 *
 * Approaches: Each algorithm is discussed further below, in its method's implementation.
 * 1. Brute Force:
 *      Algorithm:
 *      - For each number from 0 to k
 *      - Create a string binary representation.
 *      Time: O(k)
 * 2. Mathy Solution
 *      The same approach as #3, but I implement the Integer.toBinaryString method myslef.
 *          [Addmitingly, without searching up how the method works under the hood,
 *          I'm not sure if I had been able to do it on my own.]
 *      Algorithm:
 *      - Starting from zero, get int binary representation and add 1.
 *      - Convert int to string.
 * 3. Using Java's Integer.toBinaryString(x) Function.
 *
 */
public class FirstKBinaryNumbers {

    /**
     * @param k a number.
     * @return an array of the first k binary numbers, represented as strings.
     * @throws IllegalArgumentException if k is less than or equal to zero.
     */
    public String[] solveIt(final int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k cannot be less 1");
        }
        final int algorithm = 1;
        if (algorithm == 0) {
            return bruteForce(k);
        }
        if (algorithm == 1) {
            return mathSolution(k);
        }
        return javaConversionFunction(k);
    }

    /**********************
     * Different Solutions.
     *********************/
    /**
     * Algorithm:
     * - For each number from 0 to k
     * - Add 1 first index until reaching a zero
     *
     * Time: O(k^2), quadratic.
     *      For each ith number, calling .toString() will take O(i) time.
     *      Mathematically, the sum (0 + 1 + 2 + 3 + ... + n) is equal to the sum of the first n natural numbers,
     *      which can be calculated using the formula for the sum of an arithmetic series:
     *          Sum = (n/2) * (first term + last term)
     *          Sum = (n/2) * (0 + n)
     *          Sum = (n/2) * n = (n^2)/2
     *      Which simplifies to O(n^2), quadratic.
     *
     * @param k a number.
     * @return an array of the first k binary numbers, represented as strings.
     */
    private String[] bruteForce(final int k) {
        String[] binaryStrings = new String[k];

        StringBuilder binaryString = new StringBuilder("0");

        // For zero representation
        binaryStrings[0] = binaryString.toString();

        for (int i = 1; i < k; i++) {
            // As long as char equals 1, replace with zero and continue
            for (int j = binaryString.length() - 1; j >= 0; j--) {
                // if 0, then replace with 1 and stop.
                if (binaryString.charAt(j) == '0') {
                    binaryString.setCharAt(j, '1');
                    break;
                }
                // Replace with 0, and continue with next index.
                binaryString.setCharAt(j, '0');
            }
            // if first char is 0, must add 1 to the beginning
            if (binaryString.charAt(0) == '0') {
                binaryString.insert(0, '1');
            }
            binaryStrings[i] = binaryString.toString();
        }
        return binaryStrings;
    }

    /**
     * Algorithm:
     * - For each number from 0 to k
     * - Add 1 first index until reaching a zero
     *
     * Time: O(k log k) or O(n log n), linearithmic.
     *      See explanation for method below.
     *
     * @param k a number.
     * @return an array of the first k binary numbers, represented as strings.
     */
    private String[] mathSolution(final int k) {
        String[] binaryStrings = new String[k];

        // For zero representation
        binaryStrings[0] = "0";

        // For zero representation
        for (int i = 1; i < k; i++) {
            StringBuilder sb = new StringBuilder();

            // Ex: n = 1, n % 2 = 1
            // Ex: n = 2, 2 % 2 = 0, 1 % 2 = 1
            int n = i;
            while (n > 0) {
                sb.insert(0, n % 2);
                n /= 2;
            }

            binaryStrings[i] = sb.toString();
        }
        return binaryStrings;
    }

    /**
     * Search up Integer.toBinaryString method and take notes on it.
     *
     * Time: O(k log k) or O(n log n), linearithmic.
     *      The Integer.toBinaryString function has a time complexity of O(log n)
     *          in terms of the number of bits required to represent the input number,
     *          not the value of the input number itself.
     *      The time complexity of Integer.toBinaryString is O(log n) in the worst case scenario,
     *          where n represents the input number.
     *      The number of bits required to represent an integer n is proportional to log n (base 2).
     *      Therefore, the time complexity of Integer.toBinaryString can be approximated as O(log n).
     *      Therefore, the overall runtime is O(k log k) or O(n log n)
     *          since the method iterates over k numbers
     *          and calls Integer.toBinaryString for each number.
     *
     * @param k a number.
     * @return an array of the first k binary numbers, represented as strings.
     */
    private String[] javaConversionFunction(final int k) {
        // Only goes up to and not including k, so if k is zero
        String[] binaryStrings = new String[k];
        for (int i = 0; i < k; i++) {
            binaryStrings[i] = Integer.toBinaryString(i);
        }
        return binaryStrings;
    }
}













