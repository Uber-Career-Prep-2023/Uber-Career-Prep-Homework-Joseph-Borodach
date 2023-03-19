package career.prep.uber;

public class Node<T> {
    public T val;
    private Node<T> next;
    public Node(T val) {
        verify(val);
        this.val = val;
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

    private void verify(Node<T> node) {
        if (node == null) {
            throw new IllegalStateException();
        }
    }

    private void verify(T val) {
        if (val == null) {
            throw new IllegalStateException();
        }
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