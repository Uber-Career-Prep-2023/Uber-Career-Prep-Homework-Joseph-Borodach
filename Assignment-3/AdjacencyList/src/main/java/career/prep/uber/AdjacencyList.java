package career.prep.uber;

import java.util.*;
/**
 * Question 1: Build an Adjacency List/Set Representation of a Graph
 *      - Given an array of pairs of values representing edges in an unweighted graph,
 *          create the equivalent adjacency list/set representation (a map from element to a list or set of elements).
 *      - Pairs represent directed edges: (A, B) means there is an edge from A to B.
 *      - If the pair (B, A) is also provided then there is an undirected edge between A and B.
 *      - For simplicity, you may assume that each node of the graph stores an integer rather than a generic data type and that the elements are distinct.
 *      - Implement a basic DFS and BFS searching for a target value and a topological sort (using either DFS or Kahn’s algorithm).
 *
 * Notation:
 *      "V" is the number of nodes in the graph.
 *      "E" is the number of edges in the graph.
 *
 * ints vs generic nodes:
 *      -Choose to use ints for simplicity’s sake.
 *      - ints: Simple
 *      - Generic nodes:
 *          - More challenging & fun
 *          - Generics! Can be applied to different data types
 *
 * Data structure for storing edges:
 *      - Choose to implement the "lists" using sets because adding an edge only takes O(1) time since it uses a hashing function to track the ints.
 *      - Set
 *          - Don't need to worry about duplicates, can just add the node or int.
 *          - If the graph is dense, hashing can become slow and the runtime would be O(m), where m is the number of edges from this node.
 *              - I am unsure about this.
 *      - Arrays
 *          - Static Size: Either need to set size to the number of nodes - 1, or expand the array as nodes are added.
 *              - Since the problem didn't include a method to add edges to the graph this is not an issue.
 *          - Must check explicity for duplicates, runtime would be O(m), where m is the number of edges from this node.
 *      - Lists
 *          - Dynamic size: Can just add a node to the list
 *          - Must check explicity for duplicates, runtime would be O(m).
 *      - Sorted data structure
 *
 * Data structure for storing a nodes edges:
 *      - Map:
 *          - Dynamic size
 *          - Particularly, important for this problem since one of the arguments is not number of vertices
 *      - Array / List / Bag
 *          - Static size
 *          - The more traditional choice for an adjacency list when the number of vertices is known before hand
 */
public class AdjacencyList {
    /**
     * Builds an adjacency list representation of a graph from an array of edges.
     *
     * Note: No need to use a DirectedEdge class since this is not an edge weighted graph
     *
     * Assumptions:
     *      - Negatives are permitted
     *      - No cycles would be passed
     *          [Cycle detection is generally an independent problem.]
     *
     * @time: O(E + V)
     * @space: O(E + V)
     *
     * @param edges an array of pairs of values representing edges in the graph
     * @return a map from each node to a set of nodes that it is connected to
     * @throws IllegalArgumentException if the edges array is null, an edge connects a node to itself, or an edge connects two nodes already connected in that direction
     */
    public Map<Integer, Set<Integer>> adjacencySet(int[][] edges) {
        if (edges == null) {
            throw new IllegalArgumentException("Edges array cannot be null");
        }
        // Build the graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            addEdge(edge, graph);
        }
        return graph;
    }

    /**
     * Added this method to discuss time & space complexities separately.
     *
     * @time: O(1), constant.
     *      Not positive about this - Hash functions tend to slow down if there are many entries.
     * @space: O(1), constant.
     *
     * @param edge an array representing an edge (from, to)
     * @param graph the graph to add the edge to
     * @throws IllegalArgumentException if the edge connects a node to itself or if an edge connects two nodes already connected in that direction
     */
    private void addEdge(int[] edge, Map<Integer, Set<Integer>> graph) {
        int from = edge[0];
        int to = edge[1];
        if (from == to) {
            throw new IllegalArgumentException("Edges cannot connect vertexes to themselves");
        }
        // Add the edge to the graph
        Set<Integer> edgesTo = graph.computeIfAbsent(from, k -> new HashSet<>());
        if (edgesTo.contains(to)) {
            throw new IllegalArgumentException("Directed edge connecting these 2 vertices already exists");
        }
        edgesTo.add(to);
    }

    /**
     * It's important to note:
     *      Since the graph is a map with sets, could just check:
     *          if the map contains the target and if any the sets contains the target.
     *      The runtime would be O(V), the worst case being when the graph is very dense.
     *      However, this would defeat the purpose of the assignment.
     *      The same points apply to dfs below.
     *
     * @time: O(E + V), the algorithm is essentially using dfs.
     * @space: O(2V) = O(V), the queue costs O(V) and the visited set costs O(V)
     *
     * @param target The target vertex to search for.
     * @param graph The graph represented as a map of vertices and their adjacent vertices.
     * @return true if the target vertex is reachable from any starting vertex, false otherwise.
     * @throws IllegalArgumentException if the graph is null.
     */
    public boolean bfs(int target, Map<Integer, Set<Integer>> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        Set<Integer> visited = new HashSet<>();
        for (int start : graph.keySet()) {

            LinkedList<Integer> queue = new LinkedList<>();

            // Mark the current node as visited and enqueue it
            visited.add(start);
            queue.offer(start);

            while(!queue.isEmpty()) {
                int from = queue.poll();
                if (from == target) {
                    return true;
                }
                // If this vertex is not directly connected to any other vertices, continue to the next vertex.
                if (!graph.containsKey(from)) {
                    continue;
                }
                // Add all vertices connected directly from this vertex, which have not yet been visited
                for (int to : graph.get(from)) {
                    if (!visited.contains(to)) {
                        visited.add(to);
                        queue.offer(to);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Performs a depth-first search (DFS) on a graph to determine if a target vertex is reachable from any starting vertex.
     *
     * Approaches
     *      1. Recursion
     *      2. Stack
     *      - At the end of the day, the time and space complexities of both approaches will be roughly the same.
     *      - I choose to use a stack.
     *
     * Observations & Assumptions
     *      Record which nodes have been "visited", so no node is explored more than once.
     *          - Use a "visited" set.
     *      Never stated that the Graph is connected: Therefore, there could be several forests:
     *          - Will need to check each key in the map to see if it was a separate forest.
     *      No need to check that there are duplicate edges:
     *          - Because that's impossible: Edges connecting vertices use sets.
     *      Cycles should not be an issue because the above point should account for them.
     *
     * @time: O(E + V)
     * @space: O(2V) = O(V), the stack costs O(V) and the visited set costs O(V)
     *
     * @param target The target vertex to search for.
     * @param graph The graph represented as a map of vertices and their adjacent vertices.
     * @return true if the target vertex is reachable from any starting vertex, false otherwise.
     * @throws IllegalArgumentException if the graph is null.
     */
    public boolean dfs(int target, Map<Integer, Set<Integer>> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        Set<Integer> visited = new HashSet<>();
        for (int start : graph.keySet()) {

            Stack<Integer> stack = new Stack<>();

            // Mark the current node as visited and enqueue it
            visited.add(start);
            stack.push(start);

            while (!stack.isEmpty()) {
                int from = stack.pop();
                if (from == target) {
                    return true;
                }
                // If this vertex is not directly connected to any other vertices, continue to the next vertex.
                if (!graph.containsKey(from)) {
                    continue;
                }
                // Add all vertices connected directly from this vertex, which have not yet been visited
                for (int to : graph.get(from)) {
                    if (!visited.contains(to)) {
                        visited.add(to);
                        stack.push(to);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Approaches
     *      1. Stack and recursion
     *          call topologicalSort for each key in the map
     *      2. Two stacks
     *
     * Observations:
     *      The algorithm below does not work for disconnected graphs.
     *      One option is to record the outdegree for each node using a sorted map.
     *      @time: O(E + V log V)?
     *
     * @time: O(E + V), the algorithm is essentially using dfs
     * @space: O(V), extra space needed for the stack
     *
     * @param graph
     * @return
     */
    public int[] topologicalSort(Map<Integer, Set<Integer>> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int start : graph.keySet()) {
            if (!visited.contains(start)) {
                topologicalSortUtil(start, visited, stack, graph);
            }
        }

        // Add the vertices to the return array
        int len = stack.size();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            // System.out.print(stack.peek());
            ans[i] = stack.pop();
        }
        return ans;
    }

    private void topologicalSortUtil(int vertex, Set<Integer> visited, Stack<Integer> stack, Map<Integer, Set<Integer>> graph){
        visited.add(vertex);
        if (graph.containsKey(vertex)) {
            // explore all its children first
            for (int neighbor : graph.get(vertex)) {
                if (!visited.contains(neighbor))
                    topologicalSortUtil(neighbor, visited, stack, graph);
            }
        }

        // Push the current vertex to the stack
        stack.push(vertex);
    }
}

























