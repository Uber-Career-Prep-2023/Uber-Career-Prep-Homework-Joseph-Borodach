package career.prep.uber;
/**
 * Instructions:
 *      Implement the following methods.
 *      Rather than having a separate linked list class, we will pass a Node<T> struct that represents the head of the list (this is common practice in interview questions).
 *      The linked list article includes a Node<T> struct definition in a number of common languages (C++, Python, Java, JavaScript); feel free to use it in your implementation.
 *      For simplicity, you can make your nodes store integers rather than generic data types.
 *      In each of the methods, you should use pointers in languages that support pointers (e.g., Node<T> * in C++) or a reference in languages that support references (e.g., Python).
 *
 * I decided to make things more interesting using generics.
 *
 * Assumptions:
 *      Could be given a different list every time.
 *      Accordingly, did not use any class variables.
 *
 * Questions:
 *      None
 */
public class SinglyLinkedList<T extends Comparable<? super T>> implements LinkedListI<T> {
    /**
     * Creates new Node<T> with data val at front
     * @param head
     * @param val
     * @return returns new head
     * @throws IllegalArgumentException if val is null.
     *
     * Time: In every case, O(1), constant.
     *
     * Space: O(1), constant.
     */
    @Override
    public Node<T> insertAtFront(Node<T> head, T val) {
        if (val == null) {
            throw new IllegalArgumentException("[insertAtFront: 2]: val is null.");
        }
        Node<T> node = new Node<>(val);
        if (head != null) {
            node.next = head;
        }
        return node;
    }

    /**
     * creates new Node<T> with data val at end
     * @param head
     * @param val
     * @throws IllegalArgumentException if head or val is null.
     *
     * Assumptions:
     *      That the same list would not be given every time.
     *      If the same list was given everytime, then, could keep a class variable storing the last node in the list.
     *      This would make insertion O(1), constant.
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     *
     * Space: O(1), constant.
     */
    @Override
    public void insertAtBack(Node<T> head, T val) {
        if (head == null) {
            throw new IllegalArgumentException("[insertAtBack: 2]: Head is null.");
        }
        if (val == null) {
            throw new IllegalArgumentException("[insertAtBack: 4]: val is null.");
        }
        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(val);
    }

    /**
     * creates new Node<T> with data val after Node<T> loc
     * @param head
     * @param val
     * @param loc
     * @throws IllegalArgumentException if head, val, or loc is null.
     *
     * Time: In the worst case, O(n), where n is the number of nodes in the list.
     *      When loc is at the end of the list.
     *
     * Space: O(1), constant.
     *
     * Questions:
     *      Could runtime be improved using slow and fast runners?
     *      This could improve best and average cases.
     *      However, in the worst case, n - 1 nodes would need to be searched
     *      if the node to be deleted is the 2nd to last node in the list.
     */
    @Override
    public void insertAfter(Node<T> head, T val, Node<T> loc) {
        if (head == null) {
            throw new IllegalArgumentException("[insertAfter: 2]: Head is null.");
        }
        if (val == null) {
            throw new IllegalArgumentException("[insertAfter: 4]: val is null.");
        }
        if (loc == null) {
            throw new IllegalArgumentException("[insertAfter: 6]: loc is null.");
        }
        Node<T> curr = head;
        while(curr != null && !curr.equals(loc)) {
            curr = curr.next;
        }
        if (curr == null) {
            throw new IllegalArgumentException("[insertAfter: 15]: loc does not exist in list.");
        }
        curr.next = new Node<>(val, curr.next);
    }

    /**
     * removes first Node<T>
     * @param head
     * @return returns new head
     * @throws IllegalArgumentException if head is null.
     *
     * Time: In every case, constant, O(1).
     *
     * Space: O(1), constant.
     */
    @Override
    public Node<T> deleteFront(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("[deleteFront: 2]: Head is null.");
        }
        return head.next;
    }

    /**
     * removes last Node<T> Node<T>
     * @param head
     * @throws IllegalArgumentException if head is null or the list only has 1 node.
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     *
     * Space: O(1), constant.
     */
    @Override
    public void deleteBack(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("[deleteBack: 2]: Head is null.");
        }
        if (head.next == null) {
            throw new IllegalArgumentException("[deleteBack: 4]: List only has one node.");
        }
        Node<T> prev = null;
        Node<T> curr = head;
        while(curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;

    }

    /**
     * deletes Node<T> loc
     * @param head
     * @param loc
     * @return returns head
     * @throws IllegalArgumentException if head or loc is null, or if loc does not exist in the list.
     *
     * Instead of throwing an exception when loc is null, it might be more appropriate to simply return the head since deleting a null node doesn't make any sense.
     *
     * Assumptions:
     *      That there are no duplicates, as stated in the problem.
     *      If there were duplicates, would iterate threw the entire list, removing every appearance of the node.
     *
     * Time: In the worst case, O(n), where n is the number of nodes in the list.
     *      When loc is at the end of the list.
     *
     * Space: O(1), constant.
     *
     * Questions:
     *      Could runtime be improved using slow and fast runners?
     *      Does this method return a node in the case that the node to be deleted
     */
    @Override
    public Node<T> deleteNode(Node<T> head, Node<T> loc) {
        if (head == null) {
            throw new IllegalArgumentException("[deleteNode: 2]: Head is null.");
        }
        if (loc == null) {
            throw new IllegalArgumentException("[deleteNode: 4]: loc is null.");
        }
        if (head.equals(loc)) {
            return head.next;
        }
        Node<T> prev = null;
        Node<T> curr = head;
        while(curr != null && !curr.equals(loc)) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == null || !curr.equals(loc)) {
            throw new IllegalArgumentException("[deleteNode: 15]: loc does not exist in list.");
        }
        prev.next = curr.next;
        return head;
    }

    /**
     * @param head
     * @return returns length of the list.
     * If the head is null, return 0.
     * It could also make sense to throw an IllegalArgumentException, but then the user could never check the size of an empty list.
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     *
     * Space: O(1), constant.
     *
     * Questions:
     *      Could the runtime be decreased to O(log n), by using a fast runner?
     */
    @Override
    public int length(Node<T> head) {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * Reverses the linked list iteratively
     * @param head
     * @return the other end of the list, which will be the new head after reversing the list.
     * @throws IllegalArgumentException if head is null.
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     *
     * Space: O(1), constant.
     *      Originally, wrote an implementation using a stack, which was O(n).
     *      But, then, realized that was just immitating the recursive stack and not truly reversing the list iteratively.
     */
    @Override
    public Node<T> reverseIterative(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("[reverseIterative: 2]: Head is null.");
        }
        Node<T> prev = null;
        while (head.next != null) {
            Node<T> next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        head.next = prev;
        return head;
    }

    /**
     * Reverses the linked list recursively (Hint: you will need a helper function)
     * @param head
     * @return the other end of the list, which will be the new head after reversing the list.
     * @throws IllegalArgumentException if head is null.
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     * Space: In every case O(n), where n is the number of nodes in the list.
     */
    @Override
    public Node<T> reverseRecursive(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("[reverseRecursive: 2]: Head is null.");
        }
        return reverseHelper(null, head);
    }

    /**
     * @param prev
     * @param node
     * @return the other end of the list, after reversing the list.
     */
    private Node<T> reverseHelper(Node<T> prev, Node<T> node) {
        if (node == null) {
            return prev;
        }
        Node<T> next = node.next;
        node.next = prev;
        return reverseHelper(node, next);
    }
}