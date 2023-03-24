package career.prep.uber;

/**
 * Instructions:
 *   Question 6: DedupSortedList
 *   Given a sorted singly linked list, remove any duplicates so that no value appears more than once.
 *
 * Assumptions:
 *   1. We are given the root node of the list instead of a linked list data structure.
 *   2. The list could be sorted in ascending or descending order.
 *   3. The list could include negative numbers.
 *
 * Design:
 *   Algorithmic:
 *     Background: Solutions could be recursive or iterative.
 *     Chose to implement an iterative solution to avoid the overhead of recursion.
 *   Datastructures:
 *     There is no need to use additional datastructures.
 *     Simply maintaining the previous node should suffice.
 *
 * Pseudocode:
 *   1. variables - maintain 2 pointers:
 *     a. previous node
 *     b. current node
 *  2. while (not at the end of the list):
 *     a. If nodes are equal, delete current node.
 *     b. Otherwise, assert that the list is consistently ordered.
 *     c. Move pointers
 *
 * @time O(n), where n is the number of nodes in the linked list.
 * @space O(1), constant.
 *
 * @param <T> The type of values in the linked list. It must implement the Comparable interface.
 */
public class DedupSortedList<T extends Comparable<? super T>> implements DedupSortedListI<T> {
    /**
     * Remove any duplicates so that no value appears more than once.
     *
     * @param root The root node of the linked list.
     * @return The root of the deduplicated list.
     * @throws IllegalArgumentException If the root is null, if the list is not in sorted order, or if the values are strings.
     */
    @Override
    public Node<T> solveIt(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null.");
        }
        if (root.val instanceof String) {
            throw new IllegalArgumentException("Values cannot be strings.");
        }
        Node<T> prev = root;
        Node<T> curr = root.next;
        while (curr != null) {
            if (prev.val.compareTo(curr.val) == 0) {
                prev.next = curr.next;
            } else {
                if (!isConsistent(root, prev, curr)) {
                    throw new IllegalArgumentException("The list must be consistently ordered as ascending or descending.");
                }
                prev = prev.next;
            }
            curr = curr.next;
        }
        return root;
    }

    /**
     * Checks if the list is consistently ordered between the given nodes.
     *
     * @param root The root node of the linked list.
     * @param prev The previous node.
     * @param curr The current node.
     * @return True if the list is consistently ordered, false otherwise.
     */
    private boolean isConsistent(Node<T> root, Node<T> prev, Node<T> curr) {
        if (root.val.compareTo(prev.val) == 0) {
            return true;
        }
        return root.val.compareTo(prev.val) == prev.val.compareTo(curr.val);
    }
}