package career.prep.uber;

import java.util.Objects;

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

    /**
     * @param obj
     * @return
     */
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

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}