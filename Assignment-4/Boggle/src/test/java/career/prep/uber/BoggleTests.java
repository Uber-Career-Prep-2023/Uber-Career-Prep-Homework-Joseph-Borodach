package career.prep.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * JUnit 5 test class to test the {@link Boggle} class.
 */
public class BoggleTests {

    @Test
    void basicTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A', 'Y'}
        };

        String[] words = {"Ace", "Lay", "Gape"};

        List<String> expected = Arrays.asList("Ace", "Lay");

        List<String> actual = new Boggle().solveIt(board, words);

        assertListsEqual(expected, actual);
    }

    @Test
    void sample1Test() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A', 'Y'}
        };

        // The sample provided was missing the word "Race".
        String[] words = {
                "Ace", "Gape", "Mace",
                "Ape", "Grape", "Map",
                "Cape", "Lace", "May",
                "Clap", "Lap", "Pace",
                "Clay", "Lay", "Pay",
                "Rap", "Tape", "Tray",
                "Ray", "Trace", "Yap",
                "Race", "Tap", "Trap"
        };

        List<String> expected = Arrays.asList(
                "Ace", "Lay", "Clap",
                "Race", "Clay", "Ape",
                "Pace", "Ray", "Cape",
                "Lace", "Lap", "Yap",
                "Pay", "Rap"
        );

        List<String> actual = new Boggle().solveIt(board, words);

        assertListsEqual(expected, actual);
    }

    /**************************
     ******* Edge Cases *******
     **************************/
    /*************
     * Empty Tests
     *************/
    @Test
    void emptyBoardTest() {
        char[][] board = {};

        String[] words = {"Ace", "Lay", "Gape"};

        List<String> expected = new ArrayList<>();

        List<String> actual = new Boggle().solveIt(board, words);

        assertEquals(expected, actual);
    }

    @Test
    void emptyColumnsTest() {
        char[][] board = {{}};

        String[] words = {"Ace", "Lay", "Gape"};

        List<String> expected = new ArrayList<>();

        List<String> actual = new Boggle().solveIt(board, words);

        assertEquals(expected, actual);
    }

    @Test
    void emptyWordsTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A', 'Y'}
        };

        String[] words = {};

        List<String> expected = new ArrayList<>();

        List<String> actual = new Boggle().solveIt(board, words);

        assertListsEqual(expected, actual);
    }

    @Test
    void emptyWordInDictionaryTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A', 'Y'}
        };
        String[] words = {
                "Ace", "Lay", "", "Gape"
        };
        assertThrows(IllegalArgumentException.class, () -> new Boggle().solveIt(board, words));
    }

    /*************
     * Null Tests
     *************/
    @Test
    void nullBoardTest() {
        Boggle boggle = new Boggle();

        assertThrows(IllegalArgumentException.class, () -> boggle.solveIt(null, new String[0]));
    }

    @Test
    void nullWordsTest() {
        Boggle boggle = new Boggle();

        assertThrows(IllegalArgumentException.class, () -> boggle.solveIt(new char[0][0], null));
    }

    @Test
    void nullWordInDictionaryTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A', 'Y'}
        };
        String[] words = {
                "Ace", "Lay", null, "Gape"
        };

        assertThrows(IllegalArgumentException.class, () -> new Boggle().solveIt(board, words));
    }

    @Test
    void boardWithNullRowTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                null,
                {'L', 'A', 'Y'}
        };
        String[] words = {
                "Ace", "Lay", "Gape"
        };

        assertThrows(IllegalArgumentException.class, () -> new Boggle().solveIt(board, words));
    }

    /*************
     * Other Tests
     *************/
    @Test
    void boardWithDifferentRowLengthsTest() {
        char[][] board = {
                {'A', 'D', 'E'},
                {'R', 'C', 'P'},
                {'L', 'A'}
        };
        String[] words = {
                "Ace", "Lay", "Gape"
        };

        assertThrows(IllegalArgumentException.class, () -> new Boggle().solveIt(board, words));
    }

    private void assertListsEqual(List<String> expected, List<String> actual) {
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}