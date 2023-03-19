package career.prep.uber;

public class DoublyLinkedList<T> extends Util implements DoublyLinkedListI<T> {
    /**
     * creates new Node with data val at front
     *
     * @param head
     * @param val
     * @return returns new head
     */
    @Override
    public Node<T> insertAtFront(Node<T> head, T val) {
        verify(val);
        Node<T> node = new Node<>(val, null, head);
        if (head != null) {
            head.prev = node;
        }
        return node;
    }

    /**
     * creates new Node with data val at end
     * @param head
     * @param val
     */
    @Override
    public void insertAtBack(Node<T> head, T val) {
        verify(head, val);
        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(val, curr, null);
    }

    /**
     * creates new Node with data val after Node loc
     * @param head
     * @param val
     * @param loc
     */
    @Override
    public void insertAfter(Node<T> head, T val, Node<T> loc) {
        verify(head, val, loc);
        Node<T> curr = head;
        while(!curr.equals(loc)) {
            verify((curr = curr.next));
        }
        Node<T> next = curr.next;
        curr.next = new Node<T>(val, curr, next);
        if (next != null) {
            next.prev = curr.next;
        }
    }

    /**
     * removes first Node
     * @param head
     * @return returns new head
     */
    @Override
    public Node<T> deleteFront(Node<T> head) {
        verify(head);
        Node<T> node = head.next;
        if (node != null) {
            node.prev = null;
        }
        return node;
    }

    /**
     * removes last Node
     * @param head
     */
    @Override
    public void deleteBack(Node<T> head) {
        verify(head);
        verify(head.next);
        Node<T> curr = head;
        while(curr.next.next != null) {
            curr = curr.next;
        }
        curr.next.prev = null;
        curr.next = null;
    }

    /**
     * deletes Node loc
     * @param head
     * @param loc
     * @return returns head
     */
    @Override
    public Node<T> deleteNode(Node<T> head, Node<T> loc) {
        verify(head, loc);
        Node<T> curr = head;
        verify(curr.next);
        while(!curr.next.equals(loc)) {
            verify((curr = curr.next));
        }
        Node<T> next = curr.next.next;
        curr.next = next;
        if (next != null) {
            next.prev = curr;
        }
        return head;
    }

    /**
     * @param head
     * @return returns length of the list
     */
    @Override
    public int length(Node<T> head) {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        if (head != null) {
            curr = head.prev;
            while (curr != null) {
                count++;
                curr = curr.prev;
            }
        }
        return count;
    }

    /**
     * reverses the linked list iteratively
     * @param head
     * @return
     */
    @Override
    public Node<T> reverseIterative(Node<T> head) {
        verify(head);
        Node<T> prev = null;
        while (head.next != null) {
            Node<T> next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }
        head.next = prev;
        return head;
    }

    /**
     * reverses the linked list recursively (Hint: you will need a helper function)
     * @param head
     * @return
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
        Node<T> next = node.next;
        node.next = prev;
        node.prev = next;
        return reverse(node, next);
    }
}











