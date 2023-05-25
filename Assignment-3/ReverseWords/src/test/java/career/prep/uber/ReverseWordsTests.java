package career.prep.uber;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link ReverseWords} class.
 */
public class ReverseWordsTests {

    @Test
    public void sample1Test() {
        final String input = "Uber Career Prep";

        final String expected = "Prep Career Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final String input = "Emma lives in Brooklyn, New York.";

        final String expected = "York. New Brooklyn, in lives Emma";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void oneWordTest() {
        final String input = "Uber";

        final String expected = "Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void twoWordTest() {
        final String input = "Uber Rocks";

        final String expected = "Rocks Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    @Test
    public void threeWordTest() {
        final String input = "Uber Engineers Rock";

        final String expected = "Rock Engineers Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /*************
     * Edge cases.
     *************/
    /**
     * Scenario: Spaces at the beginning of the string.
     * Decided to ignore it.
     */
    @Test
    public void spaceAtBeginningTest() {
        final String input = " Uber Engineers Rock";

        final String expected = "Rock Engineers Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /**
     * Scenario: Spaces at the end of the string.
     * Decided to ignore it.
     */
    @Test
    public void spaceAtEndTest() {
        final String input = "Uber Engineers Rock ";

        final String expected = "Rock Engineers Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /**
     * Scenario: Empty string.
     * Decided to ignore it.
     */
    @Test
    public void emptyTest() {
        final String input = "";

        final String expected = "";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /**
     * Scenario: Blank string.
     * Decided to ignore it.
     */
    @Test
    public void blankTest() {
        final String input = "   ";

        final String expected = "";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /**
     * Scenario: Spaces at the end of the string.
     * Decided to ignore it and only add one space.
     */
    @Test
    public void multipleConsecutiveSpaces() {
        final String input = "Uber  Engineers Rock";

        final String expected = "Rock Engineers Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }

    /**
     * Scenario: Spaces at the end of the string.
     * Decided to ignore it and only add one space.
     */
    @Test
    public void multipleConsecutiveSpacesAtBeginningAndEnd() {
        final String input = "  Uber Engineers  ";

        final String expected = "Engineers Uber";

        final String actual = new ReverseWords().solveIt(input);

        assertEquals(expected, actual);
    }
}