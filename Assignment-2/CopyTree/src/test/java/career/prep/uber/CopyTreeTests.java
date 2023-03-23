package career.prep.uber;

import org.junit.Test;

import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CopyTreeTests {

    /**
     * This test case creates a two-level binary tree and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void twoLevelTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
    }

    /**
     * This test case creates a three-level binary tree and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void threeLevelTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(6);
        root.right.left = new Node<>(5);
        root.right.right = new Node<>(7);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
        compare(root.right, copyRoot.right);
    }

    /**
     * This test case creates a binary tree with a null child node and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void nullChildTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
    }

    /**
     * This test case creates a binary tree with negative values and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void negativeTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(-4);
        root.right = new Node<>(3);
        root.left.left = new Node<>(-1);
        root.right.right = new Node<>(5);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
        compare(root.right, copyRoot.right);
    }

    /**
     * This test case creates a binary tree with duplicate values and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void duplicateValuesTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(3);
        root.right = new Node<>(3);
        root.left.left = new Node<>(5);
        root.right.right = new Node<>(5);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
        compare(root.right, copyRoot.right);
    }

    /**
     * This test case creates a binary tree with string values and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void stringTreeTest() {
        Node<String> root = new Node<>("Hello");
        root.left = new Node<>("World");
        root.right = new Node<>("!");

        final Node<String> copyRoot = new CopyTree<String>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
        compare(root.right, copyRoot.right);
    }

    /**
     * This test case creates an unbalanced binary tree and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void unbalancedTreeTest() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.left.left = new Node<>(8);
        root.left.left.left.left = new Node<>(9);

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        compare(root, copyRoot);
        compare(root.left, copyRoot.left);
        compare(root.right, copyRoot.right);
        compare(root.left.left, copyRoot.left.left);
        compare(root.left.left.left, copyRoot.left.left.left);
        compare(root.left.left.left.left, copyRoot.left.left.left.left);
    }

    /**
     * This test case creates a large binary tree with 127 nodes and checks whether the copied tree has the same structure and values as the original tree.
     */
    @Test
    public void largeTreeTest() {
        // Create a large binary tree with 127 nodes
        Node<Integer> root = new Node<>(1);
        Node<Integer> curr = root;
        for (int i = 2; i <= 127; i++) {
            curr.left = new Node<>(i);
            curr.right = new Node<>(i + 1);
            curr = curr.left;
        }

        final Node<Integer> copyRoot = new CopyTree<Integer>().solveIt(root);

        // Verify that the copied tree has the same structure and values as the original tree
        Queue<Node<Integer>> queue = new LinkedList<>(Arrays.asList(root));
        Queue<Node<Integer>> copyQueue = new LinkedList<>(Arrays.asList(copyRoot));
        while(!queue.isEmpty() && !copyQueue.isEmpty()) {
            Node<Integer> node = queue.poll();
            Node<Integer> copyNode = copyQueue.poll();

            compare(node, copyNode);

            if (node.left != null) {
                queue.add(node.left);
                copyQueue.add(copyNode.left);
            }

            if (node.right != null) {
                queue.add(node.right);
                copyQueue.add(copyNode.right);
            }
        }
        assertTrue(queue.isEmpty());
        assertTrue(copyQueue.isEmpty());
    }

    /**
     * Helper Methods:
     * This method compares two nodes and their children for equality in value and reference. If the nodes are null, the method checks whether both nodes are null.
     * @param node
     * @param copy
     */
    private void compare(final Node node, final Node copy) {
        assertValue(node, copy);
        assertValue(node.left, copy.left);
        assertValue(node.right, copy.right);
    }

    /**
     * assertValue: This method asserts that two nodes have the same value and do not reference the same object. If the nodes are null, the method asserts that both nodes are null.
     * @param node
     * @param copy
     */
    private void assertValue(final Node<Integer> node, final Node<Integer> copy) {
        if (node == null) {
            assertNull(copy);
            return;
        }

        assertNotNull(copy);

        // By not converting the values, different data types can be tested for.
        assertEquals(node.val, copy.val);

        // check that they do not reference the same objects.
        assertNotEquals(node, copy);
    }
}