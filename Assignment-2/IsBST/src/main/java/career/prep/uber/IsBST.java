package career.prep.uber;

import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Instructions:
 *      Question 5: IsBST
 *      Given a binary tree, determine if it is a binary search tree.
 *
 * Assumptions:
 *      Because the problem did not state otherwise (in contrast to previous problems),
 *      I assumed that duplicates were permitted,
 *      and, therefore, a node can have left and right children equal to itself.
 *
 *      Generally speaking, BST's can contain duplicates,
 *      and it is dependent on the set of requirements to dictate whether the BST should be able to contain duplicates.
 *
 * General Solution:
 *      For each node, simply check that it is greater than it's left child and less than it's right child.
 *
 *      It's important to note that there is an assumption here that really warrants being proved:
 *
 *          We are suggesting that it's enough to greedily compare a node to its immediate children,
 *          and that it is not necessary to compare grandchildren and grandparents, etc.
 *
 *          The logical reason this is true is that by comparing a child to its immediate children,
 *          (and continuing to do such a comparison for each descendent) we verify that if the tree is a valid BST:
 *              all grandchildren to the left will be less than this node, and
 *              all grandchildren to the right will be greater than this node.
 *
 * Design: As for the previous problem
 *
 *     Algorithmic:
 *
 *          Background:
 *              There are few different algorithms that can be used to examine each node.
 *              Some approaches are recursive and others are iterative.
 *
 *          Chose to:
 *              Use iterative approaches because they don't suffer the overhead of recursion.
 *              Use bfs because depth is not particularly relevant and I find bfs to be a neater and simpler solution.
 *
 *      Datastructures:
 *
 *          Background:
 *              Iterative approaches could be accomplished using a queue or stack.
 *
 *          Chose to:
 *              Use a queue.
 *
 * @time O(n):
 *           where n is the number of nodes
 *           because in every case we will examine each node
 *           and compare it's value to that of it's children.
 *
 * @space O(h * 2):
 *          Where is the height of the tree, starting from 0, not 1.
 *          In other words, the root of the tree is height 0, not 1.
 *
 * @param <T> a number.
 */
public class IsBST<T extends Comparable<? super T>> implements IsBSTI<T> {
    /**
     * Determine if the given tree is a binary search tree.
     *
     * Pseudocode:
     *      1. while the queue is not null:
     *          a. dequeue the first node in the queue:
     *          b. if left child is not null:
     *              i. return false if the current node's value is less than its left child's value
     *              ii. enqueue left child
     *          c. if right child is not null
     *              i. return false if the current node's value is greater than its right child's value
     *              ii. enqueue right child
     *      2. if the queue is emptied, return true.
     *
     * compareTo:
     *      returns 0 if they are equal.
     *      returns < 0 if the value is less than the other value
     *                  (the value outside the parentheses is less than value inside the parentheses).
     *      returns > 0 if the value is greater than the other value.
     *                  (the value outside the parentheses is greater than value inside the parentheses).
     *
     * What if values are different types?
     *
     * @param root of the possibly binary tree.
     * @return if the tree is a valid binary search tree.
     * @throws IllegalArgumentException if the root is null.
     */
    @Override
    public boolean solveIt(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("[solveIt: 2]: Root cannot be null");
        }
        if (root.val instanceof String) {
            throw new IllegalArgumentException("[solveIt: 4]: Values cannot be strings.");
        }

        Queue<Node<T>> queue = new LinkedList<>(Arrays.asList(root));

        while (!queue.isEmpty()) {

            Node<T> node = queue.poll();

            Node<T> left = node.left;
            if (left != null) {
                if (node.val.compareTo(left.val) < 0) {
                    return false;
                }
                queue.add(left);
            }

            Node<T> right = node.right;
            if (right != null) {
                if (node.val.compareTo(right.val) > 0) {
                    return false;
                }
                queue.add(right);
            }
        }
        return true;
    }
}
