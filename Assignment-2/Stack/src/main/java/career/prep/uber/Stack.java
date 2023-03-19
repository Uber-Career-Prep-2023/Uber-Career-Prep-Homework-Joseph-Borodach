package career.prep.uber;
import java.util.EmptyStackException;
public class Stack<T> implements StackI<T> {
    private int N;
    private Node<T> head;
    private SinglyLinkedListI<T> list;
    public Stack() {
        head = null;
        list = new SinglyLinkedList<T>();
    }

    /**
     * Time: O(1), constant.
     * @return returns the top item in the stack
     */
    @Override
    public T top() {
        if (head == null) {
            throw new EmptyStackException();
        }
        return head.val;
    }

    /**
     * Time: O(1), constant.
     * @return returns the top item in the stack
     */
    @Override
    public T peek() {
        return top();
    }

    /**
     * Time: O(1), constant.
     * adds x to the top of the stack
     * @param x
     */
    @Override
    public void push(T x) {
        head = list.insertAtFront(head, x);
        N++;
    }

    /**
     * Time: O(1), constant.
     * @return removes and returns the top item in the stack
     */
    @Override
    public T pop() {
        if (head == null) {
            throw new EmptyStackException();
        }
        T val = head.val;
        head = list.deleteFront(head);
        N--;
        return val;
    }

    /**
     * Time: O(1), constant.
     * @return returns a boolean indicating whether the stack is empty
     */
    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    /**
     * Time: O(1), constant.
     * @return size of the stack
     */
    @Override
    public int size() {
        return N;
    }
}