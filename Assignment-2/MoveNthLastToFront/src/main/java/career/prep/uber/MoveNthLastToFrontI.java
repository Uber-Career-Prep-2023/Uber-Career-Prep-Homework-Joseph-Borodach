package career.prep.uber;

/**
 * Instructions:
 *   Question 7: MoveNthLastToFront
 *   Given a singly linked list, move the nth from the last element to the front of the list.
 *
 * @param <T> the type of the elements in the linked list.
 */
public interface MoveNthLastToFrontI<T extends Comparable<? super T>> {
    Node<T> solveIt(Node<T> head, final int k);
}
