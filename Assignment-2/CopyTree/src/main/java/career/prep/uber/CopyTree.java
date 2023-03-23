package career.prep.uber;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Instructions:
 *      Question 4: CopyTree
 *      Given a binary tree, create a deep copy. Return the root of the new tree.
 *
 * Design:
 *      Algorithmic: There are several ways to copy a binary tree, including recursive and iterative approaches.
 *          Chose to implement iterative BFS:
 *              a. Iterative because it does not suffer the overhead of recursion.
 *              b. BFS because it seemed the neatest and simplest approach for the problem.
 *
 *      Data Structures:
 *          There were a few structures that could be used
 *              a. Queue or stack, imitating the recursion stack
 *              b. Hashmap, mapping each original node to a new node.
 *          Chose to use a queue (or stack)
 *              because a hashmap's space complexity, in every case, will be 2n, O(n), where n is the number of nodes in the tree.
 *              Whereas, a queue or stack can 'easily' get rid of the nodes which it already examined.
 *
 * @time O(n), where n is the number of nodes in the tree.
 *      There is no way around examining every node in the tree, so we must visit each node once.
 *
 * @space O(h * 2)
 *      The space complexity is proportional to the maximum number of nodes that can be in the queue at any given time.
 *      In a full binary tree, the number of nodes in the last level is 2^h, so the maximum number of nodes in the queue is 2 * 2^h,
 *      because we need to store the original node and it's copy.
 *
 * @param <T> a comparable and serializable data type.
 */
public class CopyTree<T extends Comparable<T> & java.io.Serializable> implements CopyTreeI<T>  {
    /**
     * Creates a deep copy of the given binary tree and returns the root of the new tree.
     *
     * Pseudocode:
     *      1. Make new node for root
     *      2. While queue is not empty:
     *         a. Dequeue a node from the original tree
     *         b. Enqueue the corresponding node in the new tree
     *         c. If the dequeued node has a left child, create a new node for it, connect it to the corresponding
     *            node in the new tree, and enqueue it.
     *         d. If the dequeued node has a right child, create a new node for it, connect it to the corresponding
     *            node in the new tree, and enqueue it.
     *
     * @param root the root of the binary tree to copy.
     * @return the root of the new binary tree.
     * @throws IllegalArgumentException if the root is null.
     */
    public Node<T> solveIt(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("[solveIt: 2]: Root cannot be null");
        }
        Node<T> copyRoot = new Node<>(root.val);
        Queue<Node<T>> queue = new LinkedList<>();
        Queue<Node<T>> copyQueue = new LinkedList<>();
        queue.add(root);
        copyQueue.add(copyRoot);
        while(!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            Node<T> copyNode = copyQueue.poll();

            if (curr.left != null) {
                queue.add(curr.left);
                copyNode.left = new Node<>(curr.left.val);
                copyQueue.add(copyNode.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
                copyNode.right = new Node<>(curr.right.val);
                copyQueue.add(copyNode.right);
            }
        }
        return copyRoot;
    }
}
