package career.prep.uber;

public class Queue<T> extends SinglyLinkedList<T> implements QueueI<T> {
    private Node<T> back;
    private Node<T> front;

    /**
     * Constructor.
     */
    public Queue() {
        back = null;
        front = null;
    }

    /**
     * @return returns the first item in the queue
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("[peek: 2]: Queue is empty.");
        }
        return back.val;
    }

    /**
     * "adds x to the back of the queue"
     * @param x
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public void enqueue(T x) {
        if (x == null) {
            throw new IllegalArgumentException("[enqueue: 2]: Value is null.");
        }
        if (back == null) {
            front = insertAtFront(null, x);
            back = front;
        } else {
            insertAtBack(back, x);
            back = back.next;
        }
    }

    /**
     * Remove the head of the linked list.
     * @return removes and returns the first item in the queue
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("[dequeue: 2]: Queue is empty.");
        }
        Node<T> firstNode = new Node<>(front);
        front = deleteFront(front);
        return firstNode.val;
    }

    /**
     * @return returns a boolean indicating whether the queue is empt
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public boolean isEmpty() {
        return front == null;
    }
}