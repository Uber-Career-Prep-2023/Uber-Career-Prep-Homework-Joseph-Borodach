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
 * Originally, I used more compicated tests.
 *      However, they were causing issues and I was spending too much time trying to figure out what was going wrong.
 */
public class TopologicalSortTests {

    @Test
    @DisplayName("Test with simplest input possible")
    public void simpleTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                0, Set.of(1)
        );
        final int[] expected = {0, 1};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with three nodes")
    public void threeNodesTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                0, Set.of(1, 2),
                1, Set.of(2)
        );
        final int[] expected = {0, 1, 2};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }

    /**
     * Out-degrees
     * 0 = 0
     * 1 = 2
     * 2 = 2
     * 3 = 1
     */
    @Test
    @DisplayName("Test with four nodes")
    public void fourNodeTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                0, Set.of(1, 2, 3),
                1, Set.of(2, 3),
                2, Set.of(3)
        );
        final int[] expected = {0, 1, 2, 3};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with provided sample, fives nodes")
    public void fiveNodeTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                0, Set.of(1, 2, 3, 4),
                1, Set.of(2, 3, 4),
                2, Set.of(3, 4),
                3, Set.of(4)
        );
        final int[] expected = {0, 1, 2, 3, 4};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test with empty graph")
    public void emptyGraphTest() {
        final Map<Integer, Set<Integer>> graph = new HashMap<>();

        final int[] expected = {};

        final int[] actual = new AdjacencyList().topologicalSort(graph);

        assertArrayEquals(expected, actual);
    }
}