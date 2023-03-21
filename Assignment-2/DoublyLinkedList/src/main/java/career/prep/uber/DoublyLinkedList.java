package career.prep.uber;

/**
 * Instructions:
 *      Copy-paste your implementation from Question 1 and modify it.
 *      Your Node struct should have an additional prev reference as well as a next.
 *
 * @param <T>
 */
public class DoublyLinkedList<T> implements LinkedListI<T> {
    /**
     * creates new Node with data val at front
     *
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
            throw new IllegalArgumentException("[insertAtFront: 2]: Value is null");
        }
        Node<T> node = new Node<>(val, null, head);
        if (head != null) {
            head.prev = node;
        }
        return node;
    }

    /**
     * Creates new Node with data val at end
     * @param head
     * @param val
     * @throws IllegalArgumentException if head or val is null.
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
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(val, curr, null);
    }

    /**
     * creates new Node with data val after Node loc
     *
     * @param head
     * @param val
     * @param loc
     * @throws IllegalArgumentException if head, val, or loc is null, or loc does not exist in list.
     *
     * Time: In the worst case, O(n), where n is the number of nodes in the list.
     *      When loc is at the end of the list.
     *
     * Space: O(1), constant.
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
        Node<T> next = curr.next;
        curr.next = new Node<>(val, curr, next);
        if (next != null) {
            next.prev = curr.next;
        }
    }

    /**
     * removes first Node</T>
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
        Node<T> node = head.next;
        if (node != null) {
            node.prev = null;
        }
        return node;
    }

    /**
     * removes last Node
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
        Node<T> curr = head;
        while (curr.next.next != null) {
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
     * @throws IllegalArgumentException if head or loc is null, or if loc does not exist in the list.
     *
     * Time: In the worst case, O(n), where n is the number of nodes in the list.
     *      When loc is at the end of the list.
     *
     * Space: O(1), constant.
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
            Node<T> next = head.next;
            if (next != null) {
                next.prev = null;
            }
            return next;
        }
        Node<T> curr = head;
        while(curr.next != null && !curr.next.equals(loc)) {
            curr = curr.next;
        }
        if (curr.next == null || !curr.next.equals(loc)) {
            throw new IllegalArgumentException("[deleteNode: 15]: loc does not exist in list.");
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
     *
     * Questions:
     *      Should the length be calculated going right AND left, or just right
     *
     * Time: In every case O(n), where n is the number of nodes in the list.
     *
     * Space: O(1), constant.
     */
    @Override
    public int length(Node<T> head) {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        /*
        if (head != null) {
            curr = head.prev;
            while (curr != null) {
                count++;
                curr = curr.prev;
            }
        }
        */
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
            head.prev = next;
            prev = head;
            head = next;
        }
        head.next = prev;
        head.prev = null;
        return head;
    }

    /**
     * Reverses the linked list recursively (Hint: you will need a helper function)
     * @param head
     * @return the other end of the list, which will be the new head after reversing the list.
     * @throws IllegalArgumentException if head is null.
     */
    @Override
    public Node<T> reverseRecursive(Node<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("[reverseRecursive: 2]: Head is null.");
        }
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