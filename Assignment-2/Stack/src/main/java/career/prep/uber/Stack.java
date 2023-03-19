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
     * @return returns the top item in the stack
     */
    @Override
    public T top() {
        if (head == null) {
            throw new EmptyStackException();
        }
        return head.val;
    }

    @Override
    public T peek() {
        return top();
    }

    /**
     * @param x
     * adds x to the top of the stack
     */
    @Override
    public void push(T x) {
        head = list.insertAtFront(head, x);
        N++;
    }

    /**
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

    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return N;
    }
}
