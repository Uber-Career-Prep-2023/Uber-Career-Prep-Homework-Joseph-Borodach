package career.prep.uber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * JUnit 5 test class to test the {@link .dfs} method.
 */
public class GraphTraversalTests {
    @Test
    @DisplayName("Test with provided sample")
    public void providedSampleTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2, 3),
                2, Set.of(0, 3),
                3, Set.of(2)
        );

        final AdjacencyList adjacencyList = new AdjacencyList();

        // Contains every single vertex in the provided sample
        for (int i = 0; i < 4; i++) {
            assertTrue(new AdjacencyList().dfs(i, graph));
        }
    }

    @Test
    @DisplayName("Test vertex with no edges leaving")
    public void vertexWithNoEdgesTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2, 3),
                2, Set.of(0, 3),
                3, Set.of(2)
        );
        // Contains a vertex which has no edges leaving it
        assertTrue(new AdjacencyList().dfs(0, graph));
    }

    @Test
    @DisplayName("Test with non existant vertex")
    public void nonExistentStartVertexTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2, 3),
                2, Set.of(0, 3),
                3, Set.of(2)
        );
        assertFalse(new AdjacencyList().dfs(4, graph));
    }

    @Test
    @DisplayName("Test with a disconnected graph / several forests")
    public void disconnectedGraphTest() {
        final Map<Integer, Set<Integer>> graph = Map.of(
                1, Set.of(2),
                3, Set.of(4)
        );

        final AdjacencyList adjacencyList = new AdjacencyList();

        // Contains a "vertex to" from the 1st forest
        assertTrue(adjacencyList.dfs(2, graph));

        // Contains a "vertex to" from the 2nd forest
        assertTrue(adjacencyList.dfs(4, graph));
    }

    @Test
    @DisplayName("Test with empty graph")
    public void emptyGraphTest() {
        final Map<Integer, Set<Integer>> graph = new HashMap<>();

        assertFalse(new AdjacencyList().dfs(0, graph));
    }
}