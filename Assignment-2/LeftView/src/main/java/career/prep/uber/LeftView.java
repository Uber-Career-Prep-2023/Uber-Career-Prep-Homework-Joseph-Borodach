package career.prep.uber;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
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
 * General Solution: Perform iterative bfs: Add the first node in the level to the array
 *
 * Pseudo-code:
 *      1. Add root to queue
 *      2. Iterate over every level. For each:
 *          a. Add first node in queue to the left most view list.
 *          b. Dequeue every node in the queue, removing all nodes for the current level
 *          c. Enqueue all nodes on the next level
 *      3. Convert list to array.
 *
 * @time: O(n + h) = O(n)
 *          where:
 *              n is the number of nodes in tree
 *              h is the height of the tree
 *          This is because:
 *              n for iterating over the tree one time, immediately adding the nodes to a list
 *              h, converting the list to an array
 *
 * @Space: O((h * 2) + h):
 *      where h is the height of the tree.
 *      a. (h * 2) for the queue at the bottom level of the tree,
 *          The number of nodes at any level of a full tree is that level * 2 (except for the first level).
 *      b. h for the size of the list which had to be converted to an array.
 *
 * Note: The previous implementation of this class used a two-pass approach:
 *      1. Find the height of the tree using iterative bfs.
 *      2. Create an array of the height of the tree.
 *      3. Perform bfs, A SECOND TIME:
 *          Add the first node in the level to the array
 *
 *      Performance was:
 *          time: O(n + n) = O(n)
 *              Because the tree was iterated over twice:
 *                  1. To get the height
 *                  2. To add the left most nodes to the array
 *          space: O(2 * (h * 2))
 *              where h is the height of the tree.
 *              (h * 2) for the queue at the bottom level of the tree,
 *                  The number of nodes at any level of a full tree is that level * 2 (except for the first level).
 *               2 * (h * 2) because two queues were used total.
 *
 *               Question: Am I technically correct because the usage of the queues did not overlap?!
 *
 *      Realized it was technically more time & space efficient (NOT Big O):
 *          To add the left most nodes to a list as I discovered the tree the 1st time,
 *          then convert the list to an array.
 *
 */
public class LeftView implements LeftViewI {
    /**
     * Creates and returns an array of the leftmost view of the tree.
     * @param root the root node of the binary tree
     * @return an array of the leftmost view of the tree
     * @throws IllegalArgumentException if the root is null
     */
    public int[] solveIt(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree cannot be empty.");
        }
        List<Integer> list = getLeftMostValues(root);
        return toArray(list);
    }



    /**
     * Performs iterative BFS to traverse the tree and add the leftmost node in each level to a list.
     *
     * 1. Add root to queue
     * 2. Iterate over every level. For each:
     *      a. Add first node in queue to the left most view list.
     *      b. Dequeue every node in the queue, removing all nodes for the current level
     *      c. Enqueue all nodes on the next level
     *
     * @param root the root node of the binary tree
     * @return a list of the leftmost nodes in each level of the tree
     */
    private List<Integer> getLeftMostValues(Node root) {
        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            list.add(queue.peek().val);

            int nodesOnLevel = queue.size();
            for (int i = 0; i < nodesOnLevel; i++) {
                Node parent = queue.poll();
                add(queue, parent.left);
                add(queue, parent.right);
            }
        }
        return list;
    }

    /**
     * If the given node is not null, adds it to the queue.
     * @param queue the queue to which the node should be added
     * @param node the node to add to the queue
     */
    private void add(Queue<Node> queue, Node node) {
        if (node != null) {
            queue.offer(node);
        }
    }

    /**
     * Converts a list of integers to an array of integers.
     * @param list the list to convert
     * @return an array of integers
     */
    private int[] toArray(List<Integer> list) {
        int size = list.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}