package career.prep.uber;
import java.util.EmptyStackException;

/**
 * Instructions:
 *      Stacks are also not a “first-class” data structure and should also have O(1) insertion and deletion.
 *      Because insertion and deletion occur at the same end, this can be achieved with either an array or a linked list.
 *      Implement a queue class according to the following definition using a linked list as the underlying data structure;
 *      you may copy-paste and reuse any parts of your solution to Question 1.
 *      Again, for simplicity, you can make your stack store integers rather than generic data types.
 *      You will likely implement your depth-first traversals of graphs and trees recursively; however, if you chose to do them iteratively, you would need to use a stack.
 *
 * @time all methods are O(1), constant.
 *
 * @space all methods are O(1), constant.
 *
 * @param <T>
 */
public class Stack<T> extends SinglyLinkedList<T> implements StackI<T> {
    private int N;
    private Node<T> head;

    /**
     * Constructor
     */
    public Stack() {
        head = null;
    }

    /**
     * @return returns the top item in the stack
     * @throws EmptyStackException if the stack is empty
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
     */
    @Override
    public synchronized T top() {
        if (N == 0) {
            throw new EmptyStackException();
        }
        return head.val;
    }

    /**
     * @return returns the top item in the stack
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
     */
    @Override
    public synchronized T peek() {
        return top();
    }

    /**
     * adds x to the top of the stack
     * @param x
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
     */
    @Override
    public void push(T x) {
        head = insertAtFront(head, x);
        N++;
    }

    /**
     * @return removes and returns the top item in the stack
     * @throws EmptyStackException if the stack is empty
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
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
     * @return returns a boolean indicating whether the stack is empty
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
     */
    @Override
    public synchronized boolean isEmpty() {
        return N == 0;
    }

    /**
     * If the SinglyLinkedList API were used completely, then retrieving the length would be O(n).
     * @return size of the stack
     *
     * @time O(1), constant.
     *
     * @space O(1), constant.
     */
    @Override
    public synchronized int size() {
        return N;
    }
}