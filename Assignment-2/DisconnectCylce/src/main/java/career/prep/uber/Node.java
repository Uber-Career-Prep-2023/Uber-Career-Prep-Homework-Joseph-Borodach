package career.prep.uber;

/**
 * @param <T> a comparable and serializable data type.
 */
public class Node<T extends Comparable<? super T>> {
    public T val;
    public Node next;

    public Node(T val) {
        this.val = val;
        this.next = null;
    }
}
