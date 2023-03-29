package career.prep.uber;

/**
 * Instructions:
 *      Question 9: DisconnectCycle
 *      Given a singly linked list, disconnect the cycle, if one exists.
 *
 * Examples:
 *      Input: 10 -> 18 -> 12 -> 9 -> 11 -> 4 -> 12
 *      Ouptut: 10 -> 18 -> 12 -> 9 -> 11 -> 4
 *
 *      Input: 10 -> 18 -> 12 -> 9 -> 11 -> 4 -> 4
 *      Output: 10 -> 18 -> 12 -> 9 -> 11 -> 4
 *
 * Pseudo-code:
 *      Part A - Detect a cycle:
 *          Have two pointers: Slow & fast
 *          Move the two pointers, slow at one node and fast at two nodes per iteration, until they equal one another.
 *      Part B - Detect the cyclic node:
 *          Then, reset the slow pointer to the start of the list.
 *          Move the two pointers at the same rate until they equal one another.
 *          Also, maintain a pointer holding the previous node of the fast pointer.
 *          Once they equal one another, you located the cycle.
 *      Part C - Remove the cycle
 *
 * Example:
 *      Input: 10 -> 18 -> 12 -> 9 -> 11 -> 4 -> 12
 *      Part A:
 *          slow: 10, 18, 12,  9, 11
 *          fast: 10, 12, 11, 12, 11
 *      Part B:
 *          slow: 10, 18, 12,
 *          fast: 11,  4, 12
 *
 *      Input: 10 -> 18 -> 12 -> 9 -> 11 -> 4 -> 4
 *      Part A:
 *          slow: 10, 18, 12, 9, 11, 4
 *          fast: 10, 12, 11, 4,  4, 4
 *      Part B:
 *          slow: 10, 18, 12, 9, 11, 4
 *          fast:  4,  4,  4, 4,  4, 4
 *
 * Assumptions:
 *      Nodes are only considered equal if they are actually the same object.
 *      Theoretically, a node could have the same value and point to the same node and that would also create a cycle, however,
 *
 * @time: O(n + n + 1) = O(n), linear, where n is the number of nodes in the list.
 * @space: O(1), constant.
 *
 * @param <T>
 */
public class DisconnectCycle<T extends Comparable<? super T>> implements DisconnectCycleI<T> {
    /**
     * Disconnect the cycle, if one exists
     *
     * @param head of the list.
     * @return the list with the cycle removed.
     * @throws IllegalArgumentException if the head is null
     * If there is no cycle the list is just returned as is.
     */
    public Node<T> solveIt(Node<T> head) {
        if (head == null || head.next == null) {
            throw new IllegalArgumentException("List must contain at least two nodes");
        }
        Node<T> slow = head;
        Node<T> fast = head;
        Node<T> prev = null;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            prev = fast.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        slow = head;
        while (fast != null) {
            if (slow == fast) {
                if (prev != null) {
                    prev.next = null;
                }
                break;
            }
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        return head;
    }
}
