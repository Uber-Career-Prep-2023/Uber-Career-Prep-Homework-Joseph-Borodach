package career.prep.uber;

import java.util.Arrays;

/**
 * Solutions:
 * Approach #1: Two arrays/strings increment/decrement hashmap counts
 * - Idea: Add chars to map in first, subtract chars from sec, see if k >= rem.
 * - Time: O(n), or linear, where n is equal to the size of the larger string.
 *      - In the worst case, the sizes of the strings are equally long and they fewer than k differences:
 *          - 1) Iterate over the 1st string, n, incrementing each chars count.
 *          - 2) Iterate over the 2nd string, n, decrementing each chars count which is > 0 & decrementing each chars count which is <= 0.
 *          - 3) There are numerous constant operations throughout the program, c.
 *      - Therefore, 2n + c == O(n).
 * - Space: O(n), or linear, where n is equal to the size of the larger string.
 *      - Therefore, the algorithm is NOT considered to be in place.
 * - Advantage: Time is linear
 * - Disadvantage: Space is linear and is not in place.
 *
 * Approach #2: Sort, then iterate. (When I wrote this I was not thinking that I would code in Java, so this decription is not true for Java, but would be corroect for other languages where Strings are not immutable).
 * - Idea: Sort each string, and then "merge" their results, counting the number of differences.
 * - Time: O(n log n), or linearithmic, where n is equal to the size of the larger string.
 * - Space: O(1), or constant.
 *      - Therefore, the algorithm IS considered to be in place.
 * - Advantage: Space is constant
 * - Disadvantage: Time is linearithmic.
 *
 * Unit tests are in separate test file.
 *
 * ~20 min to write solution
 * ~10 min to write tests
 *
 * Questions:
 * 1) Will the strings be equal length?
 */
public class KAnagrams {
    private final int k;
    private String s1, s2;

    /**
     * @param s1
     * @param s2
     */
    KAnagrams(String s1, String s2, int k) {
        if (k < 0 || s1 == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        this.k = k;
        if (s2.length() > s1.length()) {
            String s = s2;
            s2 = s1;
            s1 = s;
        }
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * The larger string needs to go first
     * Using the below method improved runtime significantly, in half: I pasted the older for loop which
     * @return
     */
    public boolean solveIt() {
        if (s1.length() == k || s2.length() == k) {
            return true;
        }
        int i = 1;
        if (i == 0) {
            return counts();
        }
        return sorts(s1.toCharArray(), s2.toCharArray());
    }

    /**
     * Approach #1: Two arrays/strings increment/decrement hashmap counts
     * There are 2 variations of the solution
     * @return
     */
    private boolean counts() {
        int[] chars = new int[256];
        for (char c : s1.toCharArray()) {
            chars[Character.getNumericValue(c) + 1]++;
        }
        int i = 0;
        if (i == 0) {
            return faster(chars);
        }
        return slower(chars);
    }

    public boolean faster(int[] chars) {
        int d = (s1.length() - (k + 1));
        for (int i = s2.length() - 1; i >= 0 && i >= d && d >= 0; i--) {
            int v = Character.getNumericValue(s2.charAt(i)) + 1;
            if (chars[v] != 0) {
                chars[v]--;
                d--;
            }
        }
        return d < 0;
    }

    public boolean slower(int[] chars) {
        int d = s1.length();
        for (char c : s2.toCharArray()) {
            int v = Character.getNumericValue(c) + 1;
            if (chars[v] != 0) {
                chars[v]--;
                d--;
            }
        }
        return d <= k;
    }

    /**
     * Approach #2: Sort, then iterate.
     * @return
     */
    private boolean sorts(char[] chars1, char[] chars2) {
        int len1 = s1.length();
        int len2 = s2.length();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        int d = 0;
        for (int P1 = 0, P2 = 0; P1 < len1 || P2 < len2; P1++, P2++) {
            if (P2 >= len2) {
                d += (len1 - P1);
                break;
            }
            if (chars1[P1] > chars2[P2]) {
                P1--;
            } else if (chars1[P1] < chars2[P2]) {
                P2--;
                d++;
            }
        }
        return d <= k;
    }
}