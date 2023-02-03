package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class EdgeTests {
    @Test
    public void negativeK() {
        final int k = -1;
        final String s1 = "apple";
        final String s2 = "peach";
        assertThrows(IllegalArgumentException.class,
                ()-> new KAnagrams(s1, s2, k)
        );
    }

    @Test
    public void nullS1() {
        final int k = 2;
        final String s1 = null;
        final String s2 = "peach";
        assertThrows(IllegalArgumentException.class,
                ()-> new KAnagrams(s1, s2, k)
        );
    }

    @Test
    public void nullS2() {
        final int k = 2;
        final String s1 = "peach";
        final String s2 = null;
        assertThrows(IllegalArgumentException.class,
                ()-> new KAnagrams(s1, s2, k)
        );
    }
}