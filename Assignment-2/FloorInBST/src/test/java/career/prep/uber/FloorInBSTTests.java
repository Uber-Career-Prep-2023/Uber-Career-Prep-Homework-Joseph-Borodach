package career.prep.uber;

import java.util.LinkedList;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * JUnit 5 test class to test the {@link FloorInBST} class.
 */
public class FloorInBSTTests {

    private Node root;

    @Nested
    @DisplayName("Tests for a single node BST")
    class SingleNodeTests {

        @BeforeEach
        void setUp() {
            root = new Node(2);
        }

        @ParameterizedTest
        @CsvSource({
                "2, 2",
                "3, 2",
                "1, -1"
        })
        @DisplayName("Tests for a single node BST")
        void testSingleNode(int target, int expected) {
            compare(target, expected);
        }
    }

    @Nested
    @DisplayName("Tests for a 2-level BST")
    class TwoLevelBSTTests {

        @BeforeEach
        void setUp() {
            int[][] tree = new int[][]{
                    {2},
                    {0, 3}
            };
            root = createBST(tree);
        }

        @ParameterizedTest
        @CsvSource({
                "4, 3",
                "3, 3",
                "1, 0",
                "0, 0",
                "2, 2",
                "-1, -1"
        })
        @DisplayName("Tests for a 2-level BST")
        void testTwoLevelBST(int target, int expected) {
            compare(target, expected);
        }
    }

    @Nested
    @DisplayName("Tests for a complex BST")
    class ComplexBSTTests {

        @BeforeEach
        void setUp() {
            // n stands for null
            int n = Integer.MAX_VALUE;
            int[][] tree = new int[][]{
                    {              10             },
                    {     8,               16     },
                    { n,      9,      13,      17 },
                    {n, n,   n, n,   n, n,   n, 20}
            };
            root = createBST(tree);
        }

        @ParameterizedTest
        @CsvSource({
                "13, 13",
                "15, 13",
                "21, 20",
                "7, -1"
        })
        @DisplayName("Tests for a 2-level BST")
        void testComplexBST(int target, int expected) {
            compare(target, expected);
        }
    }

    private void compare(int target, int expected) {
        if (expected == -1) {
            assertThrows(IllegalArgumentException.class, () -> new FloorInBST().solveIt(root, target));
        }
        else {
            final Node actual = new FloorInBST().solveIt(root, target);

            assertNotNull(actual);

            assertEquals(expected, actual.val);
        }
    }

    private Node createBST(int[][] tree) {
        Node root = new Node(tree[0][0]);

        int levels = tree.length;

        // Note: Cannot use an official queue because they do not allow for null values
        LinkedList<Node> queue = new LinkedList<>();

        queue.offer(root);

        for (int level = 1; level < levels; level++) {

            int parentsCount = queue.size();

            for (int p = 0; p < parentsCount; p++) {

                Node parent = queue.poll();

                int child1 = p * 2;
                int child2 = child1 + 1;

                Node leftChild = tree[level][child1] == Integer.MAX_VALUE ? null : new Node(tree[level][child1]);
                Node rightChild = tree[level][child2] == Integer.MAX_VALUE ? null : new Node(tree[level][child2]);
                queue.offer(leftChild);
                queue.offer(rightChild);

                if (parent != null) {
                    parent.left = leftChild;
                    parent.right = rightChild;
                }
            }
        }
        return root;
    }
}