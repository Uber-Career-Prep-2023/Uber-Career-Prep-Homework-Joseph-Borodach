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
 * @param <T> the type of value stored in the list, must be Comparable
 */
public interface IsPalindromeI<T extends Comparable<? super T>> {
    boolean solveIt(Node<T> head);
}
