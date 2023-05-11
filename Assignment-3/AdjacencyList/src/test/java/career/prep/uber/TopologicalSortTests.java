package career.prep.uber;

import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit 5 test class to test the {@link .dfs} method.
 */
public class TopologicalSortTests {
    /**
     * Out-degrees
     * 0 = 0
     * 1 = 2
     * 2 = 2
     * 3 = 1
     */
    @Test
    @DisplayName("Test with provided sample")
    public void providedSampleTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2, 3),
                2, Set.of(0, 3),
                3, Set.of(2)
        );
        final int[] expected = {1, 2, 3, 0};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        boolean areEqual = Arrays.equals(expected, actual);
        if (!areEqual) {
            print(actual);
            fail();
        }
        // assertArrayEquals(expected, actual);
    }

    /**
     * Out-degrees
     * 1 = 1
     * 2 = 0
     * 3 = 1
     * 4 = 0
     */
    @Test
    @DisplayName("Test with a disconnected graph / several forests")
    public void disconnectedGraphTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2),
                3, Set.of(4)
        );
        final int[][] expected = {
                {1, 3, 2, 4},
                {1, 3, 4, 2},
                {3, 1, 2, 4},
                {3, 1, 4, 2}
        };

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        boolean isValidOrder = false;
        for (int[] order : expected) {
            if (Arrays.equals(order, actual)) {
                isValidOrder = true;
                break;
            }
        }
        if (!isValidOrder) {
            print(actual);
        }
        assertTrue(isValidOrder);
    }

    @Test
    @DisplayName("Test with empty graph")
    public void emptyGraphTest() {
        final Map<Integer, Set<Integer>> graph = new HashMap<>();

        final int[] expected = {};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }

    /**
     * @param actual
     */
    private void print(final int[] actual) {
        int last = actual.length - 1;
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + (i != last ? ", " : "\n"));
        }
    }
}