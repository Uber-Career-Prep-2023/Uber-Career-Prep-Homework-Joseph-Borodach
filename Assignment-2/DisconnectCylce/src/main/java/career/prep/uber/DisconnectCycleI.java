package career.prep.uber;

/**
 * Instructions:
 *      Question 9: DisconnectCycle
 *      Given a singly linked list, disconnect the cycle, if one exists.
 *
 * @param <T>
 */
public interface DisconnectCycleI<T extends Comparable<? super T>> {
    /**
     * Disconnect the cycle, if one exists
     *
     * @param head of the list.
     * @return the list with the cycle removed.
     * @throws IllegalArgumentException if the head is null
     * If there is no cycle the list is just returned as is.
     */
    Node<T> solveIt(Node<T> head);
}
