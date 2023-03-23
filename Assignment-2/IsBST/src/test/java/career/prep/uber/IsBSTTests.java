package career.prep.uber;

import org.junit.Test;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class IsBSTTests {

    /**
     * Creates a valid bst and checks that the solution returns true.
     */
    @Test
    public void validTwoLevelTest() {
        Node<Integer> root = new Node<>(2);
        root.left = new Node<>(1);
        root.right = new Node<>(3);

        isValid(root, true);
    }

    /**
     * Creates an invalid bst where the left child is greater than its parent and checks that the solution returns false.
     */
    @Test
    public void invalidLeftChildTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        isValid(root, false);
    }

    /**
     * Creates an invalid bst where the right child is less than its parent
     * and checks that the solution returns false.
     */
    @Test
    public void invalidRightChildTest() {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(1);
        root.right = new Node<>(2);

        isValid(root, false);
    }

    /**
     * Creates a valid bst where the right child is equal to its parent and checks that the solution returns true.
     */
    @Test
    public void validEqualChildTest() {
        Node<Integer> root = new Node<>(2);
        root.left = new Node<>(1);
        root.right = new Node<>(2);

        isValid(root, true);
    }

    /**
     * Creates a valid bst of data type double and checks that the solution returns true.
     */
    @Test
    public void validDoubleTests() {
        Node<Double> root = new Node<>(1.6);
        root.left = new Node<>(1.5);
        root.right = new Node<>(1.7);

        isValid(root, true);
    }

    /**
     * Creates a invalid bst of data type double and checks that the solution returns true.
     */
    @Test
    public void invalidDoubleTests() {
        Node<Double> root = new Node<>(1.5);
        root.left = new Node<>(1.6);
        root.right = new Node<>(1.7);

        isValid(root, false);
    }

    /**
     * Creates a valid bst of data type char and checks that the solution returns true.
     */
    @Test
    public void validCharTests() {
        Node<Character> root = new Node<>('b');
        root.left = new Node<>('a');
        root.right = new Node<>('c');

        isValid(root, true);
    }

    /**
     * Creates an invalid bst of data type char and checks that the solution returns true.
     */
    @Test
    public void invalidCharTests() {
        Node<Character> root = new Node<>('a');
        root.left = new Node<>('b');
        root.right = new Node<>('c');

        isValid(root, false);
    }

    /**
     * Confirms that strings don't work, since it is not possible to compare strings given the current specs.
     */
    @Test
    public void stringTests() {
        Node<String> root = new Node<>("Hello");
        assertThrows(IllegalArgumentException.class, () -> new IsBST().solveIt(root));
    }

    /**
     * Creates a large valid bst and checks that the solution returns true.
     */
    @Test
    public void largeTest() {
        Node<Integer> root = new Node<>(0);
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 0; i < 127; i++) {
            Node<Integer> node = queue.poll();

            Node<Integer> left = new Node<>(getRandom(Integer.MIN_VALUE, node.val + 1));
            node.left = left;
            queue.add(left);

            Node<Integer> right = new Node<>(getRandom(node.val, Integer.MAX_VALUE));
            node.right = right;
            queue.add(right);
        }

        isValid(root, true);
    }

    /**
     * Creates the bst provided in example 1 and checks that the solution returns true.
     */
    @Test
    public void exampleTest1() {
        Node<Integer> root = new Node<>(10);

        root.left = new Node<>(8);
        root.left.right = new Node<>(9);

        root.right = new Node<>(16);
        root.right.left = new Node<>(13);
        root.right.right = new Node<>(17);
        root.right.right.right = new Node<>(20);

        isValid(root, true);
    }

    /**
     * Creates the bst provided in example 2 and checks that the solution returns false.
     */
    @Test
    public void exampleTest2() {
        Node<Integer> root = new Node<>(10);

        root.left = new Node<>(8);
        root.left.right = new Node<>(9);

        root.right = new Node<>(16);
        root.right.left = new Node<>(13);
        root.right.right = new Node<>(17);
        root.right.right.right = new Node<>(15);

        isValid(root, false);
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
     * @param root
     * @param expected
     */
    private void isValid(Node root, final boolean expected) {

        final boolean actual = new IsBST().solveIt(root);

        assertEquals(expected, actual);

    }
}