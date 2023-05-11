package career.prep.uber;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * JUnit 5 test class to test the {@link .adjacencySet} method.
 */
public class AdjacencyListTests {
    @Test
    @DisplayName("Test adjacency list with provided sample")
    public void providedSampleTest() {
        final int[][] edges = {{1, 2}, {2, 3}, {1, 3}, {3, 2}, {2, 0}};

        final Map<Integer, Set<Integer>> expected = Map.of(
                1, Set.of(2, 3),
                2, Set.of(0, 3),
                3, Set.of(2)
        );

        final Map<Integer, Set<Integer>> actual = new AdjacencyList().adjacencySet(edges);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test adjacency list with parallel edges")
    public void parallelEdgesTest() {
        final int[][] edges = {{1, 2}, {2, 1}};

        final Map<Integer, Set<Integer>> expected = Map.of(
                1, Set.of(2),
                2, Set.of(1)
        );

        final Map<Integer, Set<Integer>> actual = new AdjacencyList().adjacencySet(edges);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test adjacency list with a disconnected graph / several forests")
    public void disconnectedGraphTest() {
        final int[][] edges = {{1, 2}, {3, 4}};

        final Map<Integer, Set<Integer>> expected = Map.of(
                1, Set.of(2),
                3, Set.of(4)
        );

        final Map<Integer, Set<Integer>> actual = new AdjacencyList().adjacencySet(edges);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test adjacency list with no edges")
    public void emptyEdgesTest() {
        final int[][] edges = {};

        final Map<Integer, Set<Integer>> actual = new AdjacencyList().adjacencySet(edges);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Test adjacency list with duplicate edge")
    public void duplicateEdgeTest() {
        final int[][] edges = {{1, 2}, {1, 2}};

        assertThrows(IllegalArgumentException.class, () -> new AdjacencyList().adjacencySet(edges));
    }

    @Test
    @DisplayName("Test adjacency list with a vertex connected to itself; self loop")
    public void selfLoopTest() {
        final int[][] edges = {{1, 1}};

        assertThrows(IllegalArgumentException.class, () -> new AdjacencyList().adjacencySet(edges));
    }

    /**
     * There's no logical reason this would fail, but tested for it because I mentioned it in the javadocs.
     */
    @Test
    @DisplayName("Test adjacency list with a negative vertices")
    public void negativesVerticesTest() {
        final int[][] edges = {{-1, 1}, {1, -2}};

        final Map<Integer, Set<Integer>> expected = Map.of(
                -1, Set.of(1),
                1, Set.of(-2)
        );

        final Map<Integer, Set<Integer>> actual = new AdjacencyList().adjacencySet(edges);

        assertEquals(expected, actual);
    }
}