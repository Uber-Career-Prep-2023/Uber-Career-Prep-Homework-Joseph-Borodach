package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShortestSubstringTests {

    @Test
    public void ex1() {
        final String[] strings = {"abracadabra", "abc"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "brac";
        assertEquals(expected, actual);
    }

    /**
     *
     */
    @Test
    public void ex2() {
        final String[] strings = {"zxycbaabcdwxyzzxwdcbxyzabccbazyx", "zzyzx"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "zzxwdcbxyz";
        assertEquals(expected, actual);
    }

    @Test
    public void ex3() {
        final String[] strings = {"abcaaaaabbbbbbccccccc", "abc"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "abc";
        assertEquals(expected, actual);
    }

    @Test
    public void ex4() {
        final String[] strings = {"abc", "cba"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "abc";
        assertEquals(expected, actual);
    }

    @Test
    public void ex5() {
        final String[] strings = {"bdab", "ab"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "ab";
        assertEquals(expected, actual);
    }

    /**
     * What should be returned?
     */
    @Test
    public void noValidSubstring() {
        final String[] strings = {"abc", "def"};
        final ShortestSubstring shortest = new ShortestSubstring(strings);
        final String actual = shortest.solveIt();
        final String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final String[] strings = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new ShortestSubstring(strings)
        );
    }

    @Test
    public void tooFewStrings() {
        final String[] strings = {"abc"};
        assertThrows(IllegalArgumentException.class,
                ()-> new ShortestSubstring(strings)
        );
    }

    @Test
    public void tooManyStrings() {
        final String[] strings = {"abc", "abc", "abc"};
        assertThrows(IllegalArgumentException.class,
                ()-> new ShortestSubstring(strings)
        );
    }
}
