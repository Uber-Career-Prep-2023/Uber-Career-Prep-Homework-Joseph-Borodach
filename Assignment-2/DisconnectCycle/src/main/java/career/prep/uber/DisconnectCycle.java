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
 *      Part A: Detect a cycle
 *          Use 2 pointers: Slow & fast
 *          Move both pointers:
 *              slow at 1 node &
 *              fast at 2 nodes per iteration
 *          Until:
 *              They equal one another,
 *              OR, fast reaches the end of the list (in which case, there is no cycle).
 *      Part B: Detect the repeated node
 *          Reset the slow pointer to the start of the list.
 *          Maintain a pointer at the previous node of the fast pointer.
 *          Move both pointers at the same rate until they equal one another,
 *          at which point, the repeated node was located.
 *      Part C: Remove the cycle
 *          Set prev.next to null.
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
 * @time: O(n), linear, where n is the number of nodes in the list.
 *      Two different way to explain the runtime.
 *
 *      Explanation #1: More technical
 *          Number of complete cyclic rounds made by _ pointer before they meet first time:
 *              x for fast pointer
 *              y for slow pointer
 *
 *          Distance traveled by fast pointer =
 *              2 * (Distance traveled by slow pointer) =
 *                  (m + n * x + k) =
 *              2 * (m + n * y + k)
 *
 *          From the above equation, we can conclude below:
 *              m + k = (x - 2y) * n
 *
 *          Which means m + k is a multiple of n.
 *
 *          Thus, we can write:
 *              m + k = i * n or
 *              m = i * n - k.
 *          Hence, distance moved by slow pointer:
 *              m, is equal to distance moved by fast pointer:
 *              i*n - k or (i-1) * n + n - k (cover the loop completely i-1 times and start from n-k).
 *
 * @space: O(1), constant.
 *
 * Alternative Approaches:
 *      1. Use hashing
 *      2. Add a variable to the node class to mark if a node has been visited.
 *          Unsure if this approach might actually be quicker.
 *          Only 1 iteration of the list because the moment a node is visited a 2nd time, just remove that node.
 *
 *
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
        boolean containsCycle = false;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            prev = fast.next;
            fast = fast.next.next;
            // detected cycle
            if (slow == fast) {
                containsCycle = true;
                break;
            }
        }

        if (containsCycle) {
            slow = head;

            // locate the repeated node
            while (fast != null && slow != fast) {
                slow = slow.next;
                prev = fast;
                fast = fast.next;
            }

            // remove the repeated node
            if (prev != null) {
                prev.next = null;
            }
        }
        return head;
    }
}
