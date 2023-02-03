package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KAnagramsTests {

    @Test
    public void ex1() {
        final int k = 1;
        final String s1 = "apple";
        final String s2 = "peach";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void ex2() {
        final int k = 2;
        final String s1 = "apple";
        final String s2 = "peach";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex3() {
        final int k = 3;
        final String s1 = "cat";
        final String s2 = "dog";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex4() {
        final int k = 1;
        final String s1 = "debit curd";
        final String s2 = "bad credit";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex5() {
        final int k = 2;
        final String s1 = "baseball";
        final String s2 = "basketball";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex6() {
        final int k = 4;
        final String s1 = "aaa";
        final String s2 = "aaabbbbbbbbbbbb";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void ex7() {
        final int k = 4;
        final String s1 = "aaabbbbbbbbbbbb";
        final String s2 = "aaa";
        final KAnagrams KA = new KAnagrams(s1, s2, k);
        final boolean actual = KA.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }
}
