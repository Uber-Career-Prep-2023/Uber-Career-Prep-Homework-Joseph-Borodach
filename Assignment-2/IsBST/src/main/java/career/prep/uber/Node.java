package career.prep.uber;

/**
 * @param <T> a comparable and serializable data type.
 */
public class Node<T extends Comparable<T>> {
    public T val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(T val) {
        this.val = val;
    }
}
