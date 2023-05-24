package career.prep.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author josephborodach
 *
 * Question #6:
 *      In some states, it is not possible to drive between any two towns because they are not connected to the same road network.
 *      Given a list of towns and a list of pairs representing roads between towns, return the number of road networks.
 *      (For example, a state in which all towns are connected by roads has 1 road network, and a state in which none of the towns are connected by roads has 0 road networks.)
 *
 * Model for the Problem:
 *      - The problem can be modeled as a Undirected Edge Graph,
 *      where towns are verticies and roads are edges.
 *      - The question essentially asks: How many forests or disconnected graphs are there in the network?
 *
 * Approaches:
 * 1. Brute force:
 *      Algorithm:
 *      - Iterate over every pair of roads, marking all pairs connected to current vertex
 *      - Add each newly discovered vertex to some Queue
 *      Time: Not exactly sure. It's worse than O(n^2) but not as bad as O(2^n).
 * 2. Graph Traversal
 *      Algorithm:
 *      - Map each string town to an index.
 *      - Turn towns and roads into an adjacency list graph.
 *      - Travers the graph using dfs or bfs.
 *      - Mark a vertex when its discovered.
 *      - Each time you do another traversal from scratch, increment count by 1.
 *      Time: O(Mapping verticies + Forming Adjacency List + Graph Traversal)
 *          = O(V + E + (E + V))
 *          = O(E + V)
 *      Space: O(Mapping verticies + Adjacency List + Visited Boolean Array + Graph Traversal)
 *          = O(V + E + V + (E + V))
 *          = O(2E + 3V)
 *          = O(E + V)
 *      Note: Techinacly, the runtime could be influenced by the length of the strings,
 *      particularlly if they are all lengthy.
 */
public class RoadNetworks {

    private int V; // Number of vertices in the graph.
    private boolean[] visited;
    private List<Set<Integer>> adjacencyList;
    private Map<String, Integer> stringsToIndexes;

    /**
     * Computes the number of road networks in a given state.
     *
     * Note: I used a 2 dimensional array of strings because it was neater and simpler for testing.
     *
     * @param towns a list of string towns in the network.
     * @param roads a list of pairs of roads in the network.
     * @return the number of road networks.
     * @throws IllegalArgumentException if the list of towns or roads is null.
     */
    public int solveIt(List<String> towns, String[][] roads) {
        // Verify input.
        if (towns == null) {
            throw new IllegalArgumentException("List of towns cannot be null");
        }
        if (roads == null) {
            throw new IllegalArgumentException("List of roads cannot be null");
        }

        stringsToIndexes = buildMap(towns);
        V = stringsToIndexes.size();
        adjacencyList = getAdjacencyList(roads);
        visited = new boolean[V];
        int nRoadNetworks = 0;

        // Travers the graph using dfs.
        for (int v = 0; v < V; v++) {
            // Vertex v alreayd not been visited yet, indicating it's not an independent roadNetwork.
            // Or, Vertex was never built into the roadNetwork.
            if (visited[v] || adjacencyList.get(v).isEmpty()) {
                continue;
            }
            // Explore all connected vertices in the roadNetwork.
            dfs(v);
            nRoadNetworks++;
        }
        return nRoadNetworks;
    }

    /**
     * Builds a map of towns to their corresponding indexes for efficient graph representation.
     *
     * @param towns a list of string towns in the network.
     * @return a map of towns to their indexes.
     * @throws IllegalArgumentException if a town is null or listed twice.
     */
    private Map<String, Integer> buildMap(List<String> towns) {
        Map<String, Integer> stringsToIndexes = new HashMap<>();

        // Add towns to map.
        for (String town : towns) {
            if (town == null) {
                throw new IllegalArgumentException("Town cannot be null");
            }
            if (stringsToIndexes.containsKey(town)) {
                throw new IllegalArgumentException("Town is listed twice in list of towns");
            }
            stringsToIndexes.put(town, stringsToIndexes.size());
        }

        return stringsToIndexes;
    }

    /**
     * Converts the list of roads into an adjacency list representation of the graph.
     *
     * @param roads a list of pairs of roads in the network.
     * @return the adjacency list representation of the graph.
     * @throws IllegalArgumentException if a vertex contains a self-cycle or a town is not in the list of towns.
     */
    private List<Set<Integer>> getAdjacencyList(String[][] roads) {
        List<Set<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjacencyList.add(new HashSet<>());
        }
        // Connect the vertices in both directions.
        for (String[] road : roads) {
            String town1 = road[0];
            String town2 = road[1];

            // Verify that towns are not identical and cyclic.
            if (town1 == town2) {
                throw new IllegalArgumentException("Vertex cycles are not permitted");
            }

            // Verify that the towns were listed in the list of towns.
            if (!stringsToIndexes.containsKey(town1)) {
                throw new IllegalArgumentException("Town was not in list of towns: " + town1);
            }
            if (!stringsToIndexes.containsKey(town2)) {
                throw new IllegalArgumentException("Town was not in list of towns: " + town2);
            }

            int idx1 = stringsToIndexes.get(town1);
            int idx2 = stringsToIndexes.get(town2);

            addEdge(idx1, idx2, adjacencyList);
            addEdge(idx2, idx1, adjacencyList);
        }
        return adjacencyList;
    }

    /**
     * Performs depth-first search (DFS) traversal on the graph.
     *
     * @param v the starting vertex for DFS traversal.
     */
    private void dfs(int v) {
        // Vertex has already been visited.e
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        for (int neighbor : adjacencyList.get(v)) {
            dfs(neighbor);
        }
    }

    /**
     * Adds an edge between two vertices in the adjacency list representation of the graph.
     *
     * @param vertexFrom the index of the vertex where the edge starts.
     * @param vertexTo   the index of the vertex where the edge ends.
     * @param adjacencyList the adjacency list representation of the graph.
     * @throws IllegalArgumentException if an edge connecting the two vertices already exists.
     */
    private void addEdge(int vertexFrom, int vertexTo, List<Set<Integer>> adjacencyList) {
        Set<Integer> connectedTo = adjacencyList.get(vertexFrom);
        if (connectedTo.contains(vertexTo)) {
            throw new IllegalArgumentException("Edge connecting these two vertices already exists");
        }
        connectedTo.add(vertexTo);
    }
}