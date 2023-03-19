package career.prep.uber;

public class Node<T> extends Util {
    public T val;
    public Node<T> prev, next;
    public Node(T val) {
        this(val, null, null);
    }

    public Node(T val, Node<T> next) {
        this(val, null, next);
    }

    public Node(T val, Node<T> prev, Node<T> next) {
        verify(val);
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
        return (int) this.val;
    }
}