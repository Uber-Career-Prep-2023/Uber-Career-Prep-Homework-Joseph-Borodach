package career.prep.uber;

import java.util.*;

/**
 * @author josephborodach
 *
 * Question #11:
 * - Given:
 *      An origin city.
 *      Amaximum travel time k.
 *      Pairs of destinations that can be reached directly from each other with corresponding travel times in hours.
 * - Return the number of destinations within k hours of the origin.
 *      Assume that having a stopover in a city adds an hour of travel time.
 *
 * Design:
 * - Modelled the problem as an Undirected Weighted Graph.
 * - Convert the strings to indexes (as done for previous problems).
 *
 * Approaches:
 * 1. Perform Graph Traversal - dfs or bfs
 * - Algorithm:
 *      - Track the shortest paths to each city, initializing each one to Integer.MAX_VALUE
 *      - Only continue exploring a given city if:
 *          a. travelTime is below the permitted travelTime.
 *          b. And, the current shortest path is shorter than the previous one.
 *      - If a cities the shortest path was previously Integer.MAX_VALUE, add it to the set of reachable cities.
 * - Time: O(E * V), because in the worst case, cities will be explored several times.
 * - Space: O(E * V)
 *
 * 2. Djikstra's Shortest Path Algorithm
 * - The optimal approach.
 * - An interview does not alote the time to implemeent this algorithm
 *      - Unless you're permitted to use APIs for the Indexed Priority Queue.
 *      - Or you don't use an Indexed Priority Queue and instead use objects for the priority queue.
 * - Time: O((E + V) log V)
 *
 * Implementation:
 * - In the end, I decided to implement the easier because:
 *      I wanted to keep it under 40 minutes, so I implemented the eaiser 1st solutions.
 *      If I have time, I will also implement Djikstra's Shortest Path Algorithm because I want to the practice implementing a simpler version of Djikstra.
 */
public class VacationDestinations {

    public class DirectedEdgeTo {
        public final int vertexTo;
        public final double hours;

        DirectedEdgeTo(final int vertexTo, final double hours) {
            if (vertexTo < 0) {
                throw new IllegalArgumentException("Cities cannot be negative");
            }
            validate(hours);
            this.vertexTo = vertexTo;
            this.hours = hours;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DirectedEdgeTo other = (DirectedEdgeTo) o;
            return vertexTo == other.vertexTo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertexTo);
        }
    }

    private int vertexCount;
    private List<Set<DirectedEdgeTo>> adjacencyList;
    private Map<String, Integer> destinationsIndices;

    public int solveIt(final String origin, final int travelTime, final Object[][] pairs) {
        validate(origin, travelTime, pairs);

        buildGraph(pairs);

        return dfs(origin, travelTime);
    }

    private void buildGraph(final Object[][] pairs) {
        vertexCount = 0;
        adjacencyList = new ArrayList<>();
        destinationsIndices = new HashMap<>();

        for (Object[] pair : pairs) {
            if (pair.length != 3) {
                throw new IllegalArgumentException("Objects must be of length 3");
            }
            String city1 = getCity(pair[0]);
            String city2 = getCity(pair[1]);

            int idx1 = getIndex(city1);
            int idx2 = getIndex(city2);

            double hours = getHours(pair[2]);

            addEdgeTo(idx1, idx2, hours);
            addEdgeTo(idx2, idx1, hours);
        }
    }

    private void addEdgeTo(final int vertexFrom, final int vertexTo, final double hours) {
        Set<DirectedEdgeTo> edgeToSet = adjacencyList.get(vertexFrom);
        DirectedEdgeTo edge = new DirectedEdgeTo(vertexTo, hours);
        if (edgeToSet.contains(edge)) {
            throw new IllegalArgumentException("There is already an edge connecting these two vertices");
        }
        edgeToSet.add(edge);
    }

    private int dfs(final String origin, final double permittedTravelTime) {
        if (!destinationsIndices.containsKey(origin)) {
            return 0;
        }
        int reachableCities = 0;
        final int startVertex = destinationsIndices.get(origin);

        double[] shortestPaths = new double[vertexCount];
        Arrays.fill(shortestPaths, Double.MAX_VALUE);

        Stack<Integer> verticesStack = new Stack<>();
        verticesStack.push(startVertex);
        shortestPaths[startVertex] = 0.0;
        while (!verticesStack.isEmpty()) {
            final int vertex = verticesStack.pop();
            final double hoursTraveled = vertex == startVertex ? shortestPaths[vertex] : shortestPaths[vertex] + 1;
            if (hoursTraveled >= permittedTravelTime) {
                continue;
            }
            for (final DirectedEdgeTo edge : adjacencyList.get(vertex)) {
                final int vertexTo = edge.vertexTo;
                final double hoursTo = hoursTraveled + edge.hours;
                if (hoursTo > permittedTravelTime || hoursTo >= shortestPaths[vertexTo]) {
                    continue;
                }
                if (shortestPaths[vertexTo] == Double.MAX_VALUE) {
                    reachableCities++;
                }
                shortestPaths[vertexTo] = hoursTo;
                verticesStack.push(vertexTo);
            }
        }
        return reachableCities;
    }

    /*****************
     * Helper Methods.
     *****************/
    /**
     * Gets the index of a city in the graph.
     *
     * @param city city name.
     * @return Index of the city.
     */
    private int getIndex(final String city) {
        return destinationsIndices.computeIfAbsent(city, key -> {
                    adjacencyList.add(new HashSet<>());
                    return vertexCount++;
        });
    }

    private double getHours(final Object hours) {
        if (!(hours instanceof Double || hours instanceof Integer)) {
            throw new IllegalArgumentException("2nd index of object arrays must be an integer or double");
        }
        return Double.parseDouble(hours.toString());
    }

    private String getCity(final Object city) {
        if (!(city instanceof String)) {
            throw new IllegalArgumentException("0 and 1 indices of object arrays must be strings");
        }
        return city.toString();
    }

    private String validate(final String city) {
        if (city == null) {
            throw new IllegalArgumentException("Cities cannot be null");
        }
        if (city.isEmpty()) {
            throw new IllegalArgumentException("Cities cannot be empty");
        }
        if (city.isBlank()) {
            throw new IllegalArgumentException("Cities cannot be blank");
        }
        return city;
    }

    private void validate(final double hours) {
        if (hours <= 0.0) {
            throw new IllegalArgumentException("Hours must be greater than 0");
        }
    }

    private void validate(final String origin, final int travelTime, final Object[][] pairs) {
        validate(origin);
        validate(travelTime);
        if (pairs == null) {
            throw new IllegalArgumentException("Pairs array cannot be null");
        }
        if (pairs.length == 0) {
            throw new IllegalArgumentException("Pairs array cannot be empty");
        }
    }
}