package career.prep.uber;

/**
 * @param <T> a comparable and serializable data type.
 */
public class Node<T extends Comparable<? super T>> {
    public T val;
    public Node next;

    public Node(T val) {
        this.val = val;
    }

    /**
     * @param val
     * @param next
     * @throws IllegalArgumentException if val is null.
     * An exception was not thrown if next was null. However, that too would make sense.
     */
    public Node(T val, Node<T> next) {
        if (val == null) {
            throw new IllegalArgumentException("[Node: 2]: val is null.");
        }
        this.val = val;
        this.next = next;
    }
}
