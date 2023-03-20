package career.prep.uber;

public class Queue<T> extends Util<T> implements QueueI<T> {
    public Node<T> front, back;
    private SinglyLinkedList<T> linkedList;

    public Queue() {
        linkedList = new SinglyLinkedList<>();
        front = null;
        back = null;
    }

    /**
     * Time: O(1), constant.
     * @return returns the first item in the queue
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        return back.val;
    }

    /**
     * Time: O(1), constant.
     * "adds x to the back of the queue"
     * @param x
     */
    public void enqueue(T x) {
        verify(x);
        if (back == null) {
            front = linkedList.insertAtFront(null, x);
            back = front;
        } else {
            linkedList.insertAtBack(back, x);
            back = back.next;
        }
    }

    /**
     * Time: O(1), constant.
     * Remove the head of the linked list.
     * @return removes and returns the first item in the queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        Node<T> firstNode = new Node<>(front);
        front = linkedList.deleteFront(front);
        return firstNode.val;
    }

    /**
     * Time: O(1), constant.
     * @return returns a boolean indicating whether the queue is empt
     */
    public boolean isEmpty() {
        return front == null;
    }
}