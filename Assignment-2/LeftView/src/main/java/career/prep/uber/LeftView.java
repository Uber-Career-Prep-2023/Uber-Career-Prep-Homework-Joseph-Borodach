package career.prep.uber;

import java.util.LinkedList;

/**
 * @author Joseph Borodach
 * @version 1
 *
 * Instructions:
 *      Question 10: LeftView
 *      Given a binary tree, create an array of the left view (leftmost elements in each level) of the tree.
 *
 * <p><img src="examples.png"></p>
 *
 * Design:
 *      1. Because leetcode's version of the problem does not use generics and instead uses integers, I gave in and used integers.
 *      2. However, I used an array since the problem states to use an array, even though leetcode using a list
 *          (which is slightly easier because it does not have a fixed size).
 *      3. As with many of these problems, the solution could be done recursively or iteratively,
 *          I chose to implement an iterative solution:
 *              a. To avoid the overhead of recursion
 *              b. It's more challenging
 *
 * General Solution: Perform in-order dfs, only adding left nodes to the array.
 *
 * Pseudo Code:
 *      1. Set up:
 *          Find the height of the tree:
 *              Use iterative bfs to find the height of the tree.
 *          Create an array of the height of the tree.
 *      2. Perform bfs:
 *          Add the first node in the level to the array
 *
 * @time: O(n + n) = O(n), the number of nodes in the tree.
 *      The tree is iterated over two times: Once to get a height and a second time to get the leftmost nodes for each level.
 *
 *      If the problem asked to return a list:
 *          a. Would not need to search the tree two times.
 *          b. Could use dfs, and avoid searching the bottom right side of the tree unnecessarily.
 *          However, neither of the changes would the Big O runtime of the program.
 *
 * @Space: O(2 * (h * 2)):
 *      Excluded the space for the return in array
 *      Two queue in total are used.
 *          Each queue's will be most full at the bottom level of the tree.
 */
public class LeftView implements LeftViewI {

    /**
     * Create and return an array of the leftmost view of the tree
     *
     * @param root of the binary tree
     * @return an array of the leftmost view of the tree
     * @throws IllegalArgumentException if the root is null
     */
    public int[] solveIt(Node root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int h = getHeight(root);
        return getLeftMost(root, h);
    }

    /**
     * If we want to get the right most view, just use a dequeue.
     *
     * @param root of the binary tree
     * @param height of the tree
     * @return the array filled with the leftmost values in the tree
     */
    private int[] getLeftMost(Node root, int height) {
        int[] array = new int[height];
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        for (int h = 0; h < height; h++) {

            // Add the first node in the level (left most node) to array
            array[h] = queue.peek().val;

            // dequeue every node in the queue, removing all nodes from previous level
            // enqueue all nodes on the next level
            int nodeCount = queue.size();
            for (int i = 0; i < nodeCount; i++) {
                Node parent = queue.poll();
                addChildren(queue, parent);
            }
        }
        return array;
    }

    /**
     * Use iterative bfs to find the height of the tree
     *
     * @param root of the binary tree
     * @return the height of the tree
     */
    private int getHeight(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            // dequeue every node in the queue, removing all nodes from previous level
            // enqueue all nodes on the next level
            int nodeCount = queue.size();
            for (int i = 0; i < nodeCount; i++) {
                Node parent = queue.poll();
                addChildren(queue, parent);
            }
        }
        return height;
    }

    /**
     * @param queue
     * @param parent
     */
    private void addChildren(LinkedList<Node> queue, Node parent) {
        add(queue, parent.left);
        add(queue, parent.right);
    }

    /**
     * If the given node is not null, add it to the queue.
     * @param queue
     * @param node
     */
    private void add(LinkedList<Node> queue, Node node) {
        if (node != null) {
            queue.offer(node);
        }
    }
}