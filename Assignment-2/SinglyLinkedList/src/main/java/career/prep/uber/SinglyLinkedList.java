package career.prep.uber;
import java.util.Stack;
/**
 * There are a few ways performance could be improved using class vars to store data about the list (second to last node, ect.)
 * if the class would be dealing with the same list every time.
 * I did not make that assumption
 */
public class SinglyLinkedList<T> implements SinglyLinkedListI<T> {
    /**
     * creates new Node<T> with data val at front
     * @param head
     * @param val
     * @return returns new head
     * Since it is assumed that we are only dealing with ints, only ints were error checked for.
     * It might not make sense to throw an IAE if the head is null, because then there can never be a list of size 1.
     */
    @Override
    public Node<T> insertAtFront(Node<T> head, T val) {
        // verify(head);
        verify(val);
        Node<T> node = new Node<>(val);
        if (head != null) {
            node.setNext(head);
        }
        return node;
    }

    /**
     * creates new Node<T> with data val at end
     * @param head
     * @param val
     * Time: In every case n, where n is the number of nodes in the list.
     */
    @Override
    public void insertAtBack(Node<T> head, T val) {
        verify(head);
        verify(val);
        Node<T> curr = head;
        while(curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<T>(val));
    }

    /**
     * creates new Node<T> with data val after Node<T> loc
     * @param head
     * @param val
     * @param loc
     * Time: In every case d, where d is the number of nodes between head and loc.
     */
    @Override
    public void insertAfter(Node<T> head, T val, Node<T> loc) {
        verify(head);
        verify(loc);
        verify(val);
        Node<T> curr = head;
        while(!curr.equals(loc)) {
            verify((curr = curr.getNext()));
        }
        curr.setNext(new Node<T>(val, curr.getNext()));
    }

    /**
     * removes first Node<T>
     * @param head
     * @return returns new head
     * Time: In every case O(1), constant.
     */
    @Override
    public Node<T> deleteFront(Node<T> head) {
        verify(head);
        Node<T> node = head.getNext();
        return node;
    }

    /**
     * removes last Node<T> Node<T>
     * @param head
     * Time: In every case n, where n is the number of nodes in the list.
     */
    @Override
    public void deleteBack(Node<T> head) {
        verify(head);
        verify(head.getNext());
        Node<T> curr = head;
        while(curr.getNext().getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(null);

    }

    /**
     * deletes Node<T> loc
     * @param head
     * @param loc
     * @return returns head
     * What if the are multiple nodes like this?
     * Time: In every case d, where d is the number of nodes between head and loc.
     */
    @Override
    public Node<T> deleteNode(Node<T> head, Node<T> loc) {
        verify(head);
        verify(loc);
        Node<T> curr = head;
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
     * Time: In every case n, where n is the number of nodes in the list.
     */
    @Override
    public int length(Node<T> head) {
        // verify(head);
        int count = 0;
        Node<T> curr = head;
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
     * Time: In every case n, where n is the number of nodes in the list.
     * Space: Constant.
     */
    @Override
    public Node<T> reverseIterative(Node<T> head) {
        verify(head);
        Node<T> prev = null;
        while (head.getNext() != null) {
            Node<T> next = head.getNext();
            head.setNext(prev);
            prev = head;
            head = next;
        }
        head.setNext(prev);
        return head;
    }

    /**
     * reverses the linked list iteratively
     * @param head
     * @return
     * Time: In every case n, where n is the number of nodes in the list.
     * Space: In every case n, where n is the number of nodes in the list.
     */
    private Node<T> reverseUsingStack(Node<T> head) {
        Stack<Node<T>> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        head = stack.pop();
        Node<T> curr = head;
        while (!stack.isEmpty()) {
            Node<T> next = stack.pop();
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
     * Time: In every case n, where n is the number of nodes in the list.
     */
    @Override
    public Node<T> reverseRecursive(Node<T> head) {
        verify(head);
        return reverse(null, head);
    }

    private Node<T> reverse(Node<T> prev, Node<T> node) {
        if (node == null) {
            return prev;
        }
        Node<T> next = node.getNext();
        node.setNext(prev);
        return reverse(node, next);
    }

    private void verify(Node<T> node) {
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
