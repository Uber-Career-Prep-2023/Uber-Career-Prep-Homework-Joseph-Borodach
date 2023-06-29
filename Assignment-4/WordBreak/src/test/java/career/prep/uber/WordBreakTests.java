package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit 5 test class to test the {@link WordBreak} class.
 */
public class WordBreakTests {

    @Test
    public void basicTest() {
        String[] dictionary = {"Elf", "Go", "Golf", "Man", "Manatee", "Not", "Note", "Pig", "Quip", "Tee", "Teen"};
        String word = "man";

        boolean expected = true;
        boolean actual = new WordBreak().solveIt(word, dictionary);

        assertEquals(expected, actual);
    }

    @Test
    public void sample1Test() {
        String[] dictionary = {"Elf", "Go", "Golf", "Man", "Manatee", "Not", "Note", "Pig", "Quip", "Tee", "Teen"};
        String word = "mangolf";

        boolean expected = true;
        boolean actual = new WordBreak().solveIt(word, dictionary);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        String[] dictionary = {"Elf", "Go", "Golf", "Man", "Manatee", "Not", "Note", "Pig", "Quip", "Tee", "Teen"};
        String word = "manateenotelf";

        boolean expected = true;
        boolean actual = new WordBreak().solveIt(word, dictionary);

        assertEquals(expected, actual);
    }

    @Test
    public void sample3Test() {
        String[] dictionary = {"Elf", "Go", "Golf", "Man", "Manatee", "Not", "Note", "Pig", "Quip", "Tee", "Teen"};
        String word = "quipig";

        boolean expected = false;
        boolean actual = new WordBreak().solveIt(word, dictionary);

        assertEquals(expected, actual);
    }
}
