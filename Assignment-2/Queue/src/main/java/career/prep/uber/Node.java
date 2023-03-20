package career.prep.uber;

public class Node<T> extends Util {
    public T val;
    public Node<T> next;
    public Node(T val) {
        verify(val);
        this.val = val;
    }

    public Node(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        verify(node.val);
        this.val = node.val;
        this.next = node.next;
    }

    public Node(T val, Node<T> next) {
        this(val);
        this.next = next;
    }

    public void setNext(Node<T> next) {
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