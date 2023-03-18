package career.prep.uber;

import career.prep.uber.Node;

/**
 * Instructions:
 * Implement the following methods.
 * Rather than having a separate linked list class, we will pass a Node struct that represents the head of the list (this is common practice in interview questions).
 * The linked list article includes a Node struct definition in a number of common languages (C++, Python, Java, JavaScript); feel free to use it in your implementation.
 * For simplicity, you can make your nodes store integers rather than generic data types.
 * In each of the methods, you should use pointers in languages that support pointers (e.g., Node * in C++) or a reference in languages that support references (e.g., Python).
 */
public interface SinglyLinkedListI<T> {
    /**
     * creates new Node with data val at front
     * @param head
     * @param val
     * @return returns new head
     * Since it is assumed that we are only dealing with ints, only ints were error checked for.
     */
    public Node insertAtFront(Node head, T val);

    /**
     * creates new Node with data val at end
     * @param head
     * @param val
     */
    public void insertAtBack(Node head, T val);

    /**
     * creates new Node with data val after Node loc
     * @param head
     * @param val
     * @param loc
     */
    public void insertAfter (Node head, T val, Node loc);

    /**
     * removes first Node; returns new head
     * @param head
     * @return
     */
    public Node deleteFront (Node head);

    /**
     * removes last Node Node
     * @param head
     */
    public void deleteBack (Node head);

    /**
     * deletes Node loc
     * @param head
     * @param loc
     * @return returns head
     */
    public Node deleteNode (Node head, Node loc);

    /**
     * returns length of the list
     * @param head
     * @return
     */
    public int length (Node head);

    /**
     * reverses the linked list iteratively
     * @param head
     * @return
     */
    public Node reverseIterative (Node head);

    /**
     * reverses the linked list recursively (Hint: you will need a helper function)
     * @param head
     * @return
     */
    public Node reverseRecursive (Node head);
}
