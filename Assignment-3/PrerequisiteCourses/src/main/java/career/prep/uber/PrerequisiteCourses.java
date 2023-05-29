package career.prep.uber;

import java.util.*;

/**
 * @author josephborodach
 *
 * Question #10:
 *      Given:
 *          A list of courses that a student needs to take to complete their major.
 *          A map of courses to their prerequisites.
 *      Return a valid order for them to take their courses assuming they only take one course for their major at once.
 *
 * Approaches:
 * 1. Graph traversal, dfs or bfs.
 *      Model requiquisite courses as directed edges in a graph.
 *      Map strings to verticies & indexes.
 *      Maintain inDegree int array to know the number of prerequisites a course has.
 *      Maintain a boolean array to know which courses have already been added.
 *
 */
public class PrerequisiteCourses {
    private int[] inDegrees;
    private int vertexCount;
    private List<Set<Integer>> adjacencyList;
    private Map<String, Integer> courseIndices;

    /**
     * Determines the valid order of courses to be taken.
     *
     * @param courses       Array of courses the student needs to take.
     * @param prerequisites Map of courses to their prerequisites.
     * @return Valid order of courses to be taken.
     * @throws IllegalArgumentException if the arguments are null, empty, or contain invalid data.
     */
    public String[] solveIt(final String[] courses, final Map<String, List<String>> prerequisites) {
        validateInput(courses, prerequisites);
        initializeGraph(courses);

        buildGraph(prerequisites);

        return getOrder(courses);
    }

    /*****************
     * Helper Methods.
     *****************/
    /**
     * Validates the input arguments.
     *
     * @param courses       Array of courses the student needs to take.
     * @param prerequisites Map of courses to their prerequisites.
     * @throws IllegalArgumentException if the arguments are null, empty, or contain invalid data.
     */
    private void validateInput(String[] courses, Map<String, List<String>> prerequisites) {
        if (courses == null || prerequisites == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        if (courses.length == 0) {
            throw new IllegalArgumentException("Courses cannot be empty");
        }
    }

    /**
     * Initializes the graph data structures.
     *
     * @param courses Array of courses the student needs to take.
     */
    private void initializeGraph(String[] courses) {
        vertexCount = 0;
        courseIndices = new HashMap<>();
        adjacencyList = new ArrayList<>();
        inDegrees = new int[courses.length];

        for (String course : courses) {
            if (courseIndices.containsKey(course)) {
                throw new IllegalArgumentException("Duplicate courses are not allowed");
            }
            courseIndices.put(course, vertexCount++);
            adjacencyList.add(new HashSet<>());
        }
    }

    /**
     * Builds the graph representation based on the prerequisites.
     *
     * @param prerequisites Map of courses to their prerequisites.
     * @throws IllegalArgumentException if the prerequisites contain invalid data.
     */
    private void buildGraph(final Map<String, List<String>> prerequisites) {
        for (Map.Entry<String, List<String>> entry : prerequisites.entrySet()) {
            String course = entry.getKey();
            int vertexTo = getIndex(course);

            List<String> coursePrerequisites = entry.getValue();

            for (String prerequisite : coursePrerequisites) {
                int prerequisiteIndex = getIndex(prerequisite);

                Set<Integer> set = adjacencyList.get(prerequisiteIndex);
                if (set.contains(vertexTo)) {
                    throw new IllegalArgumentException("Duplicate vertex");
                }
                set.add(vertexTo);
                inDegrees[vertexTo]++;
            }
        }
    }

    /**
     * Traverse Graph using bfs
     * Only add a node to the queue if it's inDegree is 0, indicating that all of its prerequisites are filled.
     * @param courses
     * @return
     */
    private String[] getOrder(final String[] courses) {
        String[] validOrder = new String[vertexCount];
        Queue<Integer> queue = new LinkedList<>();

        // Enqueue all courses which have no prerequisites.
        for (int i = 0; i < vertexCount; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int nthCourse = 0;
        while(!queue.isEmpty()) {
            int vertexFrom = queue.poll();

            validOrder[nthCourse] = courses[vertexFrom];
            nthCourse++;

            // Decrement neighbors inDegrees.
            for (int neighbor : adjacencyList.get(vertexFrom)) {
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return validOrder;
    }

    /**
     * Gets the index of a course in the graph.
     *
     * @param course Course name.
     * @return Index of the course.
     * @throws IllegalArgumentException if the course is not found in the courseIndices map.
     */
    private int getIndex(String course) {
        if (!courseIndices.containsKey(course)) {
            throw new IllegalArgumentException("Vertex was not included in courses array: " + course);
        }
        return courseIndices.get(course);
    }
}