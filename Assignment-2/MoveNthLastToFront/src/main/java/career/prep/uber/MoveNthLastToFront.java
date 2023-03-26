package career.prep.uber;

/**
 * Instructions:
 *   Question 7: MoveNthLastToFront
 *   Given a singly linked list, move the kth from the last element to the front of the list.
 *
 * Assumptions:
 *   The 2 examples provided in the instructions contradict one another:
 *     Does the kth from the last element begin from 0 or 1?
 *       In other words, is the last node referred to as 1 from the last element or 0 from the last element?
 *     To make this visually easy to see, the counting systems are written out after the example:
 *       Input:  15  ->  2  ->  8  ->  7  ->  20  ->  9  ->  11  ->  6  ->  19
 *
 *       From 0:  8  ->  7  ->  6  ->  5  ->   4  ->  3  ->   2  ->  1  ->  0
 *       From 1:  9  ->  8  ->  7  ->  6  ->   5  ->  4  ->   3  ->  2  ->  1
 *
 *       Ex #1: k = 2, output moves node 6, indicating that the count begins from 1.
 *       Ex #2: k = 7, output moves node 2, indicating that the count begins from 0.
 *
 *   What did I do?
 *     I thought it made sense to begin the count from 0 because the last node is 0 nodes from the last node.
 *     However, almost all versions of the problem online seemed to begin the count from 1.
 *     Accordingly, the count for the program begins from 1.
 *
 * Notation:
 *   n is the number of nodes in the list.
 *
 * General Design:
 *   a. Find the kth to last node.
 *   b. Move the kth node to the head of the list.
 *
 * How to find the kth to last node?
 *   1. Brute force:
 *      a. Determine the size of the list.
 *      b. Calculate the other perspective of k: what number node is kth to the last = i.
 *      c. Move a pointer i nodes from the root.
 *      d. Move the node itself to the front.
 *      time: O(n + n + 1) = O(n).
 *   2. Constant Time Improvement: Maintain two pointers.
 *      a. Move fast points kth nodes the start.
 *      b. While fast.next is not null: Move both the slow and fast pointers one at a time.
 *      c. When the fast.next is null, slow is the kth to last pointer.
 *      time: O(n + 1) = O(n).
 *   Note: The second approach only improves the runtime by a constant factor.
 *
 * @time: O(n), linear, where n is the number of nodes in the list.
 * @space O(1), constant.
 *
 * @param <T> the type of the elements in the linked list.
 */
public class MoveNthLastToFront<T extends Comparable<? super T>> implements MoveNthLastToFrontI<T> {
    /**
     * Move the kth from the last element to the front of the list.
     *
     * @param head the head of the singly linked list.
     * @param k the index (starting from 1) of the node to be moved to the front.
     * @return the linked list with the kth to last node moved to the front of the list.
     * @throws IllegalArgumentException if the list is empty, k is less than 1, or k is greater than the size of the list.
     * If k is the same size as the list, considered the problem a no-op and did not throw an IAE.
     */
    public Node<T> solveIt(Node<T> head, final int k) {
        if (head == null) {
            throw new IllegalArgumentException("The list cannot be empty.");
        }
        if (k <= 0) {
            throw new IllegalArgumentException("The index k must be greater than zero.");
        }
        Node<T> fast = head;
        for (int i = 1; i < k; i++) {
            if (fast.next == null) {
                throw new IllegalArgumentException("k is greater than the size of the list.");
            }
            fast = fast.next;
        }
        if (fast.next == null) {
            return head;
        }
        Node<T> prev = null;
        Node<T> slow = head;
        while (fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        slow.next = head;
        return slow;
    }
}










