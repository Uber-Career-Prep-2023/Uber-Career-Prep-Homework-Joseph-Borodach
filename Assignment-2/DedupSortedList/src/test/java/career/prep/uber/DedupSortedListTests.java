package career.prep.uber;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * This class contains JUnit test cases for the `DedupSortedList` class.
 */
public class DedupSortedListTests {

    private Node<Integer> node;
    private Node<Integer> expected_node;

    /**
     * Tests the `solveIt()` method when the input list contains only one node.
     */
    @Test
    public void oneNodeTest() {
        Node<Integer> root = new Node<>(1);

        Node<Integer> expected = new Node<>(1);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains two nodes with no duplicates.
     */
    @Test
    public void twoNodesNoDuplicatesTest() {
        Node<Integer> root = new Node<>(1);
        node = root;
        add(1, 2);

        Node<Integer> expected = new Node<>(1);
        expected_node = expected;
        add(2);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains two duplicate nodes.
     */
    @Test
    public void twoDuplicateNodesTest() {
        Node<Integer> root = new Node<>(1);
        node = root;
        add(1, 1);

        Node<Integer> expected = new Node<>(1);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains one duplicate node.
     */
    @Test
    public void oneDuplicateTest() {
        Node<Integer> root = new Node<>(1);
        node = root;
        add(2, 2);
        add(1, 3);

        Node<Integer> expected = new Node<>(1);
        expected_node = expected;
        add(2);
        add(3);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains multiple duplicate values.
     */
    @Test
    public void multipleDuplicatesTest() {
        Node<Integer> root = new Node<>(1);
        node = root;

        add(2, 1);
        add(2, 2);
        add(2, 3);


        Node<Integer> expected = new Node<>(1);
        expected_node = expected;

        add(2);
        add(3);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains multiple duplicate values in a descending order.
     */
    @Test
    public void descendingTest() {
        Node<Integer> root = new Node<>(3);
        node = root;

        add(2, 3);
        add(2, 2);
        add(2, 1);

        Node<Integer> expected = new Node<>(3);
        expected_node = expected;

        add(2);
        add(1);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains negative nodes.
     */
    @Test
    public void negativeNodesTest() {
        Node<Integer> root = new Node<>(-10);
        node = root;
        add(2, -5);
        add(2, 10);

        Node<Integer> expected = new Node<>(-10);
        expected_node = expected;
        add(-5);
        add(10);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains invalid duplicates.
     * Expects an `IllegalArgumentException` to be thrown.
     */
    @Test
    public void invalidListTest() {
        Node<Integer> root = new Node<>(3);
        node = root;

        add(2, 3);
        add(2, 2);
        add(2, 3);

        assertThrows(IllegalArgumentException.class, () -> new DedupSortedList<Integer>().solveIt(root));
    }

    /**
     * Test that the program works for another data type than Integers.
     * There are many more factors that should be checked for in the program.
     */
    @Test
    public void charTest() {
        Node<Character> root = new Node<>('a');
        root.next = new Node<>('a');
        root.next.next = new Node<>('b');

        Node<Character> expected = new Node<>('a');
        expected.next = new Node<>('b');

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains many duplicate values.
     */
    @Test
    public void largeTest() {
        int value = Integer.MIN_VALUE;

        Node<Integer> root = new Node<>(value);
        node = root;

        Node<Integer> expected = new Node<>(value);
        expected_node = expected;

        final int iterations = 500;

        int d = (Integer.MAX_VALUE / (iterations / 2));

        for (int i = 0; i < iterations; i++) {
            value = getRandom(value + 1, value + d);

            int numberOfDuplicates = getRandom(2, 10);

            add(numberOfDuplicates, value);

            add(value);
        }
        compare(root, expected);
    }

    /**
     * @param min
     * @param max
     * @return
     */
    private int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Adds n nodes with the same value to the list represented by the node field.
     * @param n the number of duplicate nodes to add.
     * @param value the value to add to each node.
     */
    private void add(int n, int value) {
        for (int i = 0; i < n; i++) {
            node.next = new Node<>(value);
            node = node.next;
        }
    }

    /**
     * Adds a node with the given value to the list represented by the expected_node field.
     * @param value the value to add to the node.
     */
    private void add(int value) {
        expected_node.next = new Node<>(value);
        expected_node = expected_node.next;
    }

    /**
     * Compares the output of the solveIt() method for the given input to an expected output.
     * @param root the root node of the input list.
     * @param expected the expected output list.
     */
    private void compare(final Node<?> root, Node<?> expected) {
        Node<?> actual = new DedupSortedList().solveIt(root);

        while (expected != null) {
            assertNotNull(actual);

            // System.out.println(actual.val);

            assertEquals(expected.val, actual.val);

            actual = actual.next;
            expected = expected.next;
        }
        assertNull(actual);
    }
}