package career.prep.uber;

/**
 * Instructions:
 *   Question 8: IsPalindrome
 *   Given a doubly linked list, determine if it is a palindrome.
 *   A palindrome is a sequence of characters that reads the same forward and backward.
 *
 *   Examples:
 *     Input: 9 <--> 2 <--> 4 <--> 2 <--> 9
 *     Output: True
 *     Input: 9 <--> 12 <--> 4 <--> 2 <--> 9
 *     Output: False
 *
 * Different Approaches:
 *      Note: I implemented 2a: Outward-in moving pointers.
 *
 *      1. Use extra space.
 *          a. Find the total length of the list.
 *          b. Then check if the list is a valid palindrome using dfs (or a stack to model dfs):
 *              i. Recurse until reaching the middle of the list (using a counter).
 *              ii. If the list is of odd length, skip the middle node.
 *              iii. Compare the current node to the current recursive stack's node, then return the next node in the list.
 *              iv. If we reach the end of the list return true.
 *          Note: Space would be O(n/2).
 *
 *      2. Use moving pointers. This can be done in one of two ways: Outward-in vs. Inward-out
 *          a. Outward-in: Pointing from the two ends of the list, moving inward until arriving at the middle of the list.
 *              aka. "Doubly Linked List Forward-Backward Two-Pointer"
 *              See pseudo-code below.
 *          b. Inward-out: Pointing the from the middle of the list, moving outward until arriving at the ends of the list.
 *
 *          Notes:
 *              1. The pointer approaches only work because we are given a doubly linked list.
 *              2. Calculating the length of the list is crucial.
 *              3. Both approaches' worst case runtimes are combated by the other approach.
 *                  The worst case runtimes are when they compare the majority of the list only to find at the end that the palindrome is invalid.
 *                      a. Outward-in: When the only nodes that are different are at the middle of the list.
 *                      b. Inward-out: When the only nodes that are different are at the end of the list.
 *
 * Implementation:
 *      This implementation uses the outward-in approach with two pointers starting at opposite ends of the list.
 *      It calculates the length of the list by moving the right pointer to the end of the list.
 *      Then it moves both pointers inward towards the middle, comparing each pair of nodes as it goes.
 *      If at any point the values of the two nodes being compared are not equal, it returns false.
 *      The length of the list determines whether the middle node(s) should be skipped or not.
 *
 * @time: O(n + n/2 + 1) == O(n), linear, where n is the number of nodes in the list.
 *      n for moving the right pointer to the end of the list.
 *      n/2 for moving the pointers inward half of the size of the list.
 *
 * @space: O(1), constant.
 *
 * @param <T> the type of value stored in the list, must be Comparable
 */
public class IsPalindrome<T extends Comparable<? super T>> implements IsPalindromeI<T> {
    /**
     * Outward-in: Pointing from the two ends of the list, moving inward until arriving at the middle of the list.
     * aka. "Doubly Linked List Forward-Backward Two-Pointer"
     *
     * Pseudo-code:
     *      a. Calculates the length of the list by moving the right pointer to the end of the list.
     *      b. Then moves both pointers inward towards the middle, comparing each pair of nodes as it goes.
     *      c. If at any point the values of the two nodes being compared are not equal, it returns false.
     *      d. The length of the list determines whether the middle node(s) should be skipped or not.
     *
     * @param head of the doubly linked list.
     * @return true if the list is a palindrome, false otherwise.
     * @throws IllegalArgumentException if the head node is null.
     */
    @Override
    public boolean solveIt(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("Head cannot be null");
        }
        int length = 1;
        Node<T> right = head;
        // Move the right pointer to the end of the list while counting the number of nodes in the list.
        while (right.next != null) {
            right = right.next;
            length++;
        }
        Node<T> left = head;
        int middle = length / 2;
        // Move the left and right pointers inward until they meet in the middle, comparing each pair of nodes as it goes.
        for (int i = 0; i < middle; i++) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.prev;
        }
        return true;
    }
}






