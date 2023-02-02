package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BackspaceStringCompareTests {

    @Test
    public void ex1() {
        final String s1 = "abcde";
        final String s2 = "abcde";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex2() {
        final String s1 = "Uber Career Prep";
        final String s2 = "u#Uber Careee#r Prep";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex3() {
        final String s1 = "abcdef###xyz";
        final String s2 = "abcw#xyz";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex4() {
        final String s1 = "abcdef###xyz";
        final String s2 = "abcdefxyz###";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void ex5() {
        final String s1 = "";
        final String s2 = "AAAA####";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex6() {
        final String s1 = "bxj##tw";
        final String s2 = "bxo#j##tw";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void ex7() {
        final String s1 = "isfcow#";
        final String s2 = "isfcog#w#";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        final boolean actual = BSC.solveIt();
        final boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void nullStringA() {
        final String s1 = null;
        final String s2 = "u#Uber Careee#r Prep";
        assertThrows(IllegalArgumentException.class,
                ()-> new BackspaceStringCompare(s1, s2)
        );
    }

    @Test
    public void nullStringB() {
        final String s1 = "u#Uber Careee#r Prep";
        final String s2 = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new BackspaceStringCompare(s1, s2)
        );
    }

    /*
    @Test
    public void invalidInput1() {
        final String s1 = "AAA####";
        final String s2 = "";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        assertThrows(IllegalArgumentException.class,
                ()-> BSC.solveIt()
        );
    }

    @Test
    public void invalidInput2() {
        final String s1 = "#";
        final String s2 = "";
        final BackspaceStringCompare BSC = new BackspaceStringCompare(s1, s2);
        assertThrows(IllegalArgumentException.class,
                ()-> BSC.solveIt()
        );
    }
     */
}
