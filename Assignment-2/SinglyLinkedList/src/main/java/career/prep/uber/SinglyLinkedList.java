package career.prep.uber;
import java.util.Stack;
/**
 * Instructions:
 * Implement the following methods.
 * Rather than having a separate linked list class, we will pass a Node struct that represents the head of the list (this is common practice in interview questions).
 * The linked list article includes a Node struct definition in a number of common languages (C++, Python, Java, JavaScript); feel free to use it in your implementation.
 * For simplicity, you can make your nodes store integers rather than generic data types.
 * In each of the methods, you should use pointers in languages that support pointers (e.g., Node * in C++) or a reference in languages that support references (e.g., Python).
 */
public class SinglyLinkedList<T> implements SinglyLinkedListI<T> {
    /**
     * creates new Node with data val at front
     * @param head
     * @param val
     * @return returns new head
     * Since it is assumed that we are only dealing with ints, only ints were error checked for.
     * It might not make sense to throw an IAE if the head is null, because then there can never be a list of size 1.
     */
    @Override
    public Node insertAtFront(Node head, T val) {
        verify(val);
        Node node = new Node(val);
        if (head != null) {
            node.setNext(head);
        }
        return node;
    }

    /**
     * creates new Node with data val at end
     * @param head
     * @param val
     * Time: In every case n, where n is the number of nodes in the list.
     */
    @Override
    public void insertAtBack(Node head, T val) {
        verify(head);
        verify(val);
        Node curr = head;
        while(curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node(val));
    }

    /**
     * creates new Node with data val after Node loc
     * @param head
     * @param val
     * @param loc
     * Time: In every case d, where d is the number of nodes between head and loc.
     */
    @Override
    public void insertAfter(Node head, T val, Node loc) {
        verify(head);
        verify(loc);
        verify(val);
        Node curr = head;
        while(!curr.equals(loc)) {
            verify((curr = curr.getNext()));
        }
        curr.setNext(new Node(val, curr.getNext()));
    }

    /**
     * removes first Node
     * @param head
     * @return returns new head
     */
    @Override
    public Node deleteFront(Node head) {
        verify(head);
        Node node = head.getNext();
        return node;
    }

    /**
     * removes last Node Node
     * @param head
     */
    @Override
    public void deleteBack(Node head) {
        verify(head);
        verify(head.getNext());
        Node curr = head;
        while(curr.getNext().getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(null);

    }

    /**
     * deletes Node loc
     * @param head
     * @param loc
     * @return returns head
     * What if the are multiple nodes like this?
     */
    @Override
    public Node deleteNode(Node head, Node loc) {
        verify(head);
        verify(loc);
        Node curr = head;
        verify(curr.getNext());
        while(!curr.getNext().equals(loc)) {
            verify((curr = curr.getNext()));
        }
        curr.setNext(curr.getNext().getNext());
        return head;
    }

    /**
     * returns length of the list
     * @param head
     * @return
     * If the head is null, return 0.
     * It would also make sense to throw an IAE.
     */
    @Override
    public int length(Node head) {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    /**
     * reverses the linked list iteratively
     * @param head
     * @return
     */
    @Override
    public Node reverseIterative(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        head = stack.pop();
        Node curr = head;
        while (!stack.isEmpty()) {
            Node next = stack.pop();
            curr.setNext(next);
            curr = next;
        }
        curr.setNext(null);
        return head;
    }

    /**
     * reverses the linked list recursively
     * @param head
     * @return
     */
    @Override
    public Node reverseRecursive(Node head) {
        verify(head);
        return reverse(null, head);
    }

    private Node reverse(Node prev, Node node) {
        Node next = node.getNext();
        node.setNext(prev);
        return next != null ? reverse(node, next) : node;
    }

    private void verify(Node node) {
        if (node == null) {
            throw new IllegalStateException();
        }
    }

    private void verify(T val) {
        if (val == null) {
            throw new IllegalStateException();
        }
    }
}
