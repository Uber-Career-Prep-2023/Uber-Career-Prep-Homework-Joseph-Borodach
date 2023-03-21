package career.prep.uber;

public class Node<T> {
    public T val;
    public Node<T> next;

    /**
     * @param val
     * @throws IllegalArgumentException if val is null.
     */
    public Node(T val) {
        if (val == null) {
            throw new IllegalArgumentException("[Node: 2]: val is null.");
        }
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

    public Node getNext() {
        return this.next;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Node<T> other = (Node) obj;
        if (other.val == null) {
            return false;
        }
        return this.val == other.val;
    }

    @Override
    public int hashCode() {
        return (int) this.val;
    }
}