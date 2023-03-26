package career.prep.uber;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * This class contains JUnit test cases for the `DedupSortedList` class.
 */
public class DedupSortedListTests {

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
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(1);
        list.insertAtBack(root, 2);

        Node<Integer> expected = new Node<>(1);
        list.insertAtBack(expected, 2);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains two duplicate nodes.
     */
    @Test
    public void twoDuplicateNodesTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(1);
        add(1, 1, root, list);

        Node<Integer> expected = new Node<>(1);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains one duplicate node.
     */
    @Test
    public void oneDuplicateTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(1);
        add(2, 2, root, list);
        add(1, 3, root, list);

        Node<Integer> expected = new Node<>(1);
        list.insertAtBack(expected, 2);
        list.insertAtBack(expected, 3);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains multiple duplicate values.
     */
    @Test
    public void multipleDuplicatesTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(1);
        add(2, 1, root, list);
        add(2, 2, root, list);
        add(2, 3, root, list);

        Node<Integer> expected = new Node<>(1);
        list.insertAtBack(expected, 2);
        list.insertAtBack(expected, 3);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains multiple duplicate values in a descending order.
     */
    @Test
    public void descendingTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(3);
        add(2, 3, root, list);
        add(2, 2, root, list);
        add(2, 1, root, list);

        Node<Integer> expected = new Node<>(3);
        list.insertAtBack(expected, 2);
        list.insertAtBack(expected, 1);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains negative nodes.
     */
    @Test
    public void negativeNodesTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(-10);
        add(2, -5, root, list);
        add(2, 10, root, list);

        Node<Integer> expected = new Node<>(-10);
        list.insertAtBack(expected, -5);
        list.insertAtBack(expected, 10);

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains invalid duplicates.
     * Expects an `IllegalArgumentException` to be thrown.
     */
    @Test
    public void invalidListTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> root = new Node<>(3);

        add(2, 3, root, list);
        add(2, 2, root, list);
        add(2, 3, root, list);

        assertThrows(IllegalArgumentException.class, () -> new DedupSortedList<Integer>().solveIt(root));
    }

    /**
     * Test that the program works for another data type than Integers.
     * There are many more factors that should be checked for in the program.
     */
    @Test
    public void charTest() {
        final LinkedListI<Character> list = new SinglyLinkedList<>();

        Node<Character> root = new Node<>('a');
        list.insertAtBack(root,'a');
        list.insertAtBack(root,'b');

        Node<Character> expected = new Node<>('a');
        list.insertAtBack(expected,'b');

        compare(root, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains many duplicate values.
     */
    @Test
    public void largeTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        int value = Integer.MIN_VALUE;

        Node<Integer> root = new Node<>(value);

        Node<Integer> expected = new Node<>(value);

        final int iterations = 500;

        int d = (Integer.MAX_VALUE / (iterations / 2));

        for (int i = 0; i < iterations; i++) {
            value = getRandom(value + 1, value + d);

            int numberOfDuplicates = getRandom(2, 10);

            add(numberOfDuplicates, value, root, list);

            list.insertAtBack(expected,value);
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
     * @param nodesToAdd the number of duplicate nodes to add.
     * @param value the value to add to each node.
     * @param root
     * @param list
     */
    private void add(int nodesToAdd, int value, Node<Integer> root, final LinkedListI<Integer> list) {
        for (int i = 0; i < nodesToAdd; i++) {
            list.insertAtBack(root, value);
        }
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