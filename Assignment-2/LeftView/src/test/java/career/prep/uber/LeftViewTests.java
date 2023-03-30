package career.prep.uber;

import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * JUnit 5 test class to test the {@link LeftView} class.
 */
public class LeftViewTests {

    /**
     * In order to make things easy to visualize, I used list of lists to represent each level of the tree
     * 0s represent null nodes
     */
    private int[][] tree;

    @Test
    public void singleNodeTest() {
        tree = new int[][]{
                {2}
        };

        final int[] expected = {2};

        compare(expected);
    }

    @Test
    public void onlyRightMostSmallTest() {
        tree = new int[][]{
                {2},
                {0, 3}
        };

        final int[] expected = new int[]{2, 3};

        compare(expected);
    }

    @Test
    public void threeNodeTest() {
        tree = new int[][]{
                {2},
                {1, 3}
        };

        final int[] expected = new int[]{2, 1};

        compare(expected);
    }

    @Test
    public void fiveNodeTest() {
        tree = new int[][]{
                    {7},
                   {6, 5},
                {4, 3, 2, 1}
        };

        final int[] expected = new int[]{7, 6, 4};

        compare(expected);
    }

    @Test
    public void example1Test() {
        tree = new int[][]{
                {                           7                          },   // 7
                {            8,                          3             },   // 8
                {     0,            0,            9,            13     },   // 9
                {  0,    0,      0,    0,     20,    0,     14,     0  },   // 20
                {0, 0,  0, 0,  0, 0,  0, 0,  0, 0,  0, 0,  0, 15,  0, 0}    // 15
        };

        final int[] expected = new int[]{7, 8, 9, 20, 15};

        compare(expected);
    }

    @Test
    public void example2Test() {
        tree = new int[][]{
                {              7            }, // 7
                {    20,              4     }, // 20
                {15,      6,      8,      11}  // 15
        };

        final int[] expected = new int[]{7, 20, 15};

        compare(expected);
    }

    @Test
    public void onlyRightMostTest() {
        tree = new int[][]{
                {              3            },  // 3
                {     0,               2    },  // 2
                {0,       0,       0,      1}   // 1
        };

        final int[] expected = new int[]{3, 2, 1};

        compare(expected);
    }

    @Test
    public void onlyRightMostLargeTest() {
        tree = new int[][]{
                {                                 5                                },   // 5
                {                0,                                 4              },   // 4
                {       0,               0,                0,                3     },   // 3
                {  0,       0,      0,       0,       0,       0,       0,       2 },   // 2
                {0, 0,    0, 0,    0, 0,    0, 0,    0, 0,    0, 0,    0, 0,    0, 1}   // 1
        };

        final int[] expected = new int[]{5, 4, 3, 2, 1};

        compare(expected);
    }

    @Test
    public void rightMiddleMostTest() {
        tree = new int[][]{
                {                                10                                },   // 10
                {                0,                                 9              },   // 9
                {       0,               0,                8,                7     },   // 8
                {  0,       0,      0,       0,       6,       0,       0,       5 },   // 6
                {0, 0,    0, 0,    0, 0,    0, 0,    4, 3,    0, 0,    0, 0,    2, 1}   // 4
        };

        final int[] expected = new int[]{10, 9, 8, 6, 4};

        compare(expected);
    }

    private void compare(int[] expected) {
        Node root = getTree();

        print(root);

        final int[] actual = new LeftView().solveIt(root);

        assertNotNull(actual);

        assertEquals(expected.length, actual.length);

        int n = expected.length;

        for (int i = 0; i < n; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    private Node getTree() {
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

                Node leftChild = tree[level][child1] == 0 ? null : new Node(tree[level][child1]);
                Node rightChild = tree[level][child2] == 0 ? null : new Node(tree[level][child2]);
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

    private void print(Node root) {
        LinkedList<Node> queue = new LinkedList<>();

        queue.offer(root);

        int levels = tree.length;

        for (int level = 0; level < levels; level++) {

            boolean allNull = true;

            int parentsCount = queue.size();
            int lastParent = parentsCount - 1;

            for (int parent = 0; parent < parentsCount; parent++) {

                Node parentNode = queue.poll();

                if (parentNode == null) {
                    System.out.print("_");
                    queue.add(null);
                    queue.add(null);
                } else {
                    System.out.print(parentNode.val);
                    queue.add(parentNode.left);
                    queue.add(parentNode.right);
                    allNull = false;
                }

                if (parent != lastParent) {
                    System.out.print(", ");
                }
            }

            if (allNull) {
                break;
            }
            System.out.println();
        }
        System.out.println();
    }
}