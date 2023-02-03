package career.prep.uber;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TimedTests {
    @Test(timeout = 5_000)
    public void time1() {
        final int n = 100_000_000;
        final int k = 100_000_000 / 2;
        char[] chars1 = new char[n];
        char[] chars2 = new char[n];
        Arrays.fill(chars1, 'a');
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                chars2[i] = 'a';
            } else {
                chars2[i] = 'b';
            }
        }
        final String s1 = new String(chars1);
        final String s2 = new String(chars2);
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test(timeout = 3_500)
    public void time2() {
        final int n = 100_000_000;
        final int k = 100_000_000 / 2;
        char[] chars1 = new char[n];
        char[] chars2 = new char[n];
        Arrays.fill(chars1, 'a');
        for (int i = 0; i < n; i++) {
            if (i % 4 == 0) {
                chars2[i] = 'a';
            } else {
                chars2[i] = 'b';
            }
        }
        final String s1 = new String(chars1);
        final String s2 = new String(chars2);
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }

    /**
     * This test perfect illustrates the difference between the fast and slow approaches
     */
    @Test(timeout = 6_000)
    public void time3() {
        final int n = 500_000_000;
        final int k = 10;
        char[] chars1 = new char[n];
        char[] chars2 = new char[n];
        for (int i = 0; i < n; i++) {
            chars1[i] = 'a';
            chars2[i] = 'b';
        }
        final String s1 = new String(chars1);
        final String s2 = new String(chars2);
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }
}
