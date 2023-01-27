package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ReverseVowelsTests {

    @Test
    public void ex1() {
        final String s = "Uber Career Prep";
        final ReverseVowels RV = new ReverseVowels(s);
        final String actual = RV.solveIt();
        final String expected = "eber Ceraer PrUp";
        assertEquals(expected, actual);
    }

    @Test
    public void ex2() {
        final String s = "xyz";
        final ReverseVowels RV = new ReverseVowels(s);
        final String actual = RV.solveIt();
        final String expected = "xyz";
        assertEquals(expected, actual);
    }

    @Test
    public void ex3() {
        final String s = "flamingo";
        final ReverseVowels RV = new ReverseVowels(s);
        final String actual = RV.solveIt();
        final String expected = "flominga";
        assertEquals(expected, actual);
    }

    @Test
    public void nullString() {
        final String s = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new ReverseVowels(s)
        );
    }

    @Test
    public void emptyString() {
        final String s = "";
        final ReverseVowels RV = new ReverseVowels(s);
        final String actual = RV.solveIt();
        final String expected = "";
        assertEquals(expected, actual);
    }
}
