package career.prep.uber;
/**
 * Instructions:
 * Implement the following methods.
 * Rather than having a separate linked list class, we will pass a Node<T> struct that represents the head of the list (this is common practice in interview questions).
 * The linked list article includes a Node<T> struct definition in a number of common languages (C++, Python, Java, JavaScript); feel free to use it in your implementation.
 * For simplicity, you can make your nodes store integers rather than generic data types.
 * In each of the methods, you should use pointers in languages that support pointers (e.g., Node<T> * in C++) or a reference in languages that support references (e.g., Python).
 */
public interface SinglyLinkedListI<T> {
    /**
     * creates new Node<T> with data val at front
     * @param head
     * @param val
     * @return returns new head
     * Since it is assumed that we are only dealing with ints, only ints were error checked for.
     */
    Node<T> insertAtFront(Node<T> head, T val);

    /**
     * creates new Node<T> with data val at end
     * @param head
     * @param val
     */
    void insertAtBack(Node<T> head, T val);

    /**
     * creates new Node<T> with data val after Node<T> loc
     * @param head
     * @param val
     * @param loc
     */
    void insertAfter(Node<T> head, T val, Node<T> loc);

    /**
     * removes first Node<T>
     * @param head
     * @return returns new head
     */
    Node<T> deleteFront(Node<T> head);

    /**
     * removes last Node<T> Node<T>
     * @param head
     */
    void deleteBack(Node<T> head);

    /**
     * deletes Node<T> loc
     * @param head
     * @param loc
     * @return returns head
     */
    Node<T> deleteNode(Node<T> head, Node<T> loc);

    /**
     * returns length of the list
     * @param head
     * @return
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
