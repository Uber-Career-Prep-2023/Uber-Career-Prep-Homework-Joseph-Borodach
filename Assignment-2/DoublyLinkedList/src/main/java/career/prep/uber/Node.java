package career.prep.uber;

import java.util.Objects;

public class Node<T> {
    public T val;
    public Node<T> prev;
    public Node<T> next;

    public Node(T val) {
        this.prev = null;
        this.next = null;
        this.val = val;
    }

    public Node(T val, Node<T> prev, Node<T> next) {
        this.prev = prev;
        this.next = next;
        this.val = val;
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
        return Objects.hash(val, next);
    }
}