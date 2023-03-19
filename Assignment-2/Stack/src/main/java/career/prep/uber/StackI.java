package career.prep.uber;

public interface StackI<T> {
    /**
     * @return returns the top item in the stack
     */
    T peek();

    T top();

    /**
     * @param x
     * adds x to the top of the stack
     */
    void push(T x);

    /**
     * @return removes and returns the top item in the stack
     */
    T pop();

    /**
     * @return returns a boolean indicating whether the stack is empty
     */
    boolean isEmpty();

    int size();
}
