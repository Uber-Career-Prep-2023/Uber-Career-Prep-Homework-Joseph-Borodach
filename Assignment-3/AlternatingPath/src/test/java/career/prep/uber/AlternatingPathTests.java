package career.prep.uber;

import career.prep.uber.AlternatingPath.DirectedEdge;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link AlternatingPath} class.
 */
public class AlternatingPathTests {
    @Test
    public void sample1Test() {
        final DirectedEdge[] edges = {
                new DirectedEdge("A", "B", "blue"),
                new DirectedEdge("A", "C", "red"),
                new DirectedEdge("A", "D", "red"),

                new DirectedEdge("B", "D", "blue"),
                new DirectedEdge("B", "E", "blue"),

                new DirectedEdge("C", "B", "red"),

                new DirectedEdge("D", "C", "blue"),
                new DirectedEdge("D", "E", "red"),

                new DirectedEdge("E", "C", "red")
        };

        final String origin = "A";

        final String destination = "E";

        // Path: A→D (red), D→C (blue), C→B (red), B→E (blue)
        final int expected = 4;

        final int actual = new AlternatingPath().solveIt(edges, origin, destination);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final DirectedEdge[] edges = {
                new DirectedEdge("A", "B", "blue"),
                new DirectedEdge("A", "C", "red"),
                new DirectedEdge("A", "D", "red"),

                new DirectedEdge("B", "D", "blue"),
                new DirectedEdge("B", "E", "blue"),

                new DirectedEdge("C", "B", "red"),

                new DirectedEdge("D", "C", "blue"),
                new DirectedEdge("D", "E", "red"),

                new DirectedEdge("E", "C", "red")
        };

        final String origin = "E";

        final String destination = "D";

        // Only Path: E→C (red), C→B (red), B→D (blue)
        final int expected = -1;

        final int actual = new AlternatingPath().solveIt(edges, origin, destination);

        assertEquals(expected, actual);
    }
}
