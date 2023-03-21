package career.prep.uber;

public interface LinkedListI<T> {
    /**
     * creates new Node with data val at front
     * @param head
     * @param val
     * @return returns new head
     */
    Node<T> insertAtFront(Node<T> head, T val);

    /**
     * creates new Node with data val at end
     * @param head
     * @param val
     */
    void insertAtBack(Node<T> head, T val);

    /**
     * creates new Node with data val after Node loc
     * @param head
     * @param val
     * @param loc
     */
    void insertAfter(Node<T> head, T val, Node<T> loc);

    /**
     * removes first Node
     * @param head
     * @return returns new head
     */
    Node<T> deleteFront(Node<T> head);

    /**
     * removes last Node
     * @param head
     */
    void deleteBack(Node<T> head);

    /**
     * deletes Node loc
     * @param head
     * @param loc
     * @return returns head
     */
    Node<T> deleteNode(Node<T> head, Node<T> loc);

    /**
     * @param head
     * @return returns length of the list
     */
    int length(Node<T> head);

    /**
     * reverses the linked list iteratively
     * @param head
     * @return
     */
    Node<T> reverseIterative(Node<T> head);

    /**
     * reverses the linked list recursively (Hint: you will need a helper function)
     * @param head
     * @return
     */
    Node<T> reverseRecursive(Node<T> head);
}
