package career.prep.uber;
import java.util.EmptyStackException;
public class Stack<T> extends SinglyLinkedList<T> implements StackI<T> {
    private int N;
    private Node<T> head;
    public Stack() {
        head = null;
    }

    /**
     * Time: O(1), constant.
     * @return returns the top item in the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public synchronized T top() {
        if (N == 0) {
            throw new EmptyStackException();
        }
        return head.val;
    }

    /**
     * Time: O(1), constant.
     * @return returns the top item in the stack
     */
    @Override
    public synchronized T peek() {
        return top();
    }

    /**
     * Time: O(1), constant.
     * adds x to the top of the stack
     * @param x
     * // @see SinglyLinkedListI.insertAtFront(Node<T> head, T val)
     */
    @Override
    public void push(T x) {
        head = insertAtFront(head, x);
        N++;
    }

    /**
     * Time: O(1), constant.
     * @return removes and returns the top item in the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public synchronized T pop() {
        if (N == 0) {
            throw new EmptyStackException();
        }
        T val = head.val;
        head = deleteFront(head);
        N--;
        return val;
    }

    /**
     * Time: O(1), constant.
     * @return returns a boolean indicating whether the stack is empty
     */
    @Override
    public synchronized boolean isEmpty() {
        return N == 0;
    }

    /**
     * Time: O(1), constant.
     * If the SinglyLinkedList API were used completley, then retrieving the length would be O(n).
     * @return size of the stack
     */
    @Override
    public synchronized int size() {
        return N;
    }
}