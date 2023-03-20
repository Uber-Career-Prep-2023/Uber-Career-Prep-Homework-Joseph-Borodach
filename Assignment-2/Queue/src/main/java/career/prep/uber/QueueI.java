package career.prep.uber;

public interface QueueI<T> {
    /**
     * @return returns the first item in the queue
     */
    T peek();

    /**
     * adds x to the back of the queue
     * @param x
     */
    void enqueue(T x);

    /**
     * @return removes and returns the first item in the queue
     */
    T dequeue();

    /**
     * @return returns a boolean indicating whether the queue is empt
     */
    boolean isEmpty();
}
