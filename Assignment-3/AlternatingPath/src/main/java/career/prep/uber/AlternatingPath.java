package career.prep.uber;

import java.util.*;

/**
 * @author josephborodach
 *
 * Question #8:
 *      Given an origin and a destination in a directed graph in which edges can be blue or red.
 *      Determine the length of the shortest path from the origin to the destination in which the edges traversed in alternate colors.
 *      Return -1 if no such path exists.
 *
 * Assumptions:
 *      1. The verticies were strings. Logic:
 *          On the one hand, they should be character because in the samples they were not surrounded by "" as the colors were.
 *          On the other hand, however, only using characters would limit the number of verticies to a maximum of 52 (or 26 if only uppercase letter were allowed).
 *
 * Approaches:
 * 1. Brute Force.
 *      I'm not sure what the brute force solution is.
 *
 * 2. Graph traversal (dfs or bfs) starting from "origin"
 *      - Using bfs, can return the 1st path that reaches the "destination.
 *      - Will convert the array of edges, into an Adjacency List.
 *      - To incorperate the "alternative colors" constraint, just track the color of the last edge.
 *          - Create an edge class which stores the vertex the edges connects to and an enum for the color.
 *      - In order to track the verticies, I mapped the strings to indexes and tossed the String representations out of the problem.
 *          - Note: The reason this is necessary was because I decided the verticies were Strings and not Characters.
 *              If the String are very lengthy, the runtime would be terrible.
 *          - This conversion is only necessary once for each vertex.
 *      Time: O(E + V). The worst case of bfs will still be the same as dfs and need to traverse every possible path.
 *      Space: O((E + V) + V + (E + V)).
 *          (E + V) Because we need to store the "traversal" through recursion or an additional data strcuture (Queue, Stack).
 *          V Because we need to map the String verticies to numbers.
 *          (E + V) Because we need to store the graph as an Adjacency List.
 *
 */
public class AlternatingPath {
    /**
     * Solves the problem and returns the length of the shortest path with alternating colors.
     *
     * @param edges        a directed graph in which edges can be blue or red.
     * @param origin       vertex.
     * @param destination  vertex.
     * @return the length of the shortest path from the origin to the destination with alternating colors,
     *         or -1 if no such path exists.
     */
    public int solveIt(final DirectedEdge[] edges, String origin, String destination) {
        if (edges == null) {
            throw new IllegalArgumentException("Edges array cannot be null");
        }
        validateString(origin);
        validateString(destination);
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Origin cannot equal destination");
        }

        Graph graph = new Graph();
        List<Set<NumberedDirectedEdge>> adjacencyList = graph.buildGraph(edges, origin, destination);

        Queue<Integer> queue = new LinkedList<>();
        Queue<Color> colorsQueue = new LinkedList<>();
        boolean[] redVisited = new boolean[graph.V];
        boolean[] blueVisited = new boolean[graph.V];

        queue.offer(graph.origin);
        colorsQueue.offer(Color.NEUTRAL);

        int length = 0;
        int nChildrenRemaining = 1;
        int nChildrenOnNextLevel = 0;

        while (!queue.isEmpty()) {
            int vertexFrom = queue.poll();
            Color color = colorsQueue.poll();

            if (color == Color.BLUE) {
                if (blueVisited[vertexFrom]) {
                    continue;
                }
                blueVisited[vertexFrom] = true;
            }
            else if (color == Color.RED) {
                if (redVisited[vertexFrom]) {
                    continue;
                }
                redVisited[vertexFrom] = true;
            }

            for (NumberedDirectedEdge edge : adjacencyList.get(vertexFrom)) {
                if (edge.color == color) {
                    continue;
                }
                if (edge.vertexTo == graph.destination) {
                    return length + 1;
                }
                nChildrenOnNextLevel++;
                queue.offer(edge.vertexTo);
                colorsQueue.offer(edge.color);
            }

            nChildrenRemaining--;
            if (nChildrenRemaining == 0) {
                length++;
                nChildrenRemaining = nChildrenOnNextLevel;
                nChildrenOnNextLevel = 0;
            }
        }
        return -1;
    }

    public class Graph {
        private int V;
        private int origin;
        private int destination;
        private Map<String, Integer> stringToIndexes;
        private List<Set<NumberedDirectedEdge>> adjacencyList;

        public List<Set<NumberedDirectedEdge>> buildGraph(final DirectedEdge[] edges, String origin, String destination) {
            stringToIndexes = new HashMap<>();
            adjacencyList = new ArrayList<>();

            this.origin = getVertex(origin);
            this.destination = getVertex(destination);

            for (DirectedEdge edge : edges) {
                int indexTo = getVertex(edge.vertexTo);
                int indexFrom = getVertex(edge.vertexFrom);

                NumberedDirectedEdge numberedEdge = new NumberedDirectedEdge(indexFrom, indexTo, edge.color);
                Set<NumberedDirectedEdge> set = adjacencyList.get(indexFrom);
                if (set.contains(numberedEdge)) {
                    throw new IllegalArgumentException("Cannot have duplicate edges");
                }
                set.add(numberedEdge);
            }
            this.V = stringToIndexes.size();
            return adjacencyList;
        }

        private int getVertex(String vertex) {
            return stringToIndexes.computeIfAbsent(vertex, key -> {
                int index = stringToIndexes.size();
                adjacencyList.add(new HashSet<>());
                return index;
            });
        }
    }

    public enum Color {
        BLUE, RED, NEUTRAL;
    }

    public static class NumberedDirectedEdge {
        int vertexFrom;
        int vertexTo;
        Color color;

        NumberedDirectedEdge(int vertexFrom, int vertexTo, String color) {
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
            if (color.equals("red")) {
                this.color = Color.RED;
            } else if (color.equals("blue")) {
                this.color = Color.BLUE;
            } else {
                throw new IllegalArgumentException("Colors are limited to blue and red");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            NumberedDirectedEdge other = (NumberedDirectedEdge) o;
            return vertexFrom == other.vertexFrom && vertexTo == other.vertexTo && color == other.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertexFrom, vertexTo, color);
        }
    }

    public static class DirectedEdge {
        String vertexFrom;
        String vertexTo;
        String color;

        DirectedEdge(String vertexFrom, String vertexTo, String color) {
            validateString(color);
            validateString(vertexFrom);
            validateString(vertexTo);

            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
            this.color = color;
        }
    }

    public static void validateString(String vertex) {
        if (vertex == null || vertex.isEmpty() || vertex.isBlank()) {
            throw new IllegalArgumentException("String arguments cannot be null, empty, or blank");
        }
    }
}