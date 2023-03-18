package career.prep.uber;

public class Node<T> {
    public T val;
    private Node next;
    public Node(T val) {
        verify(val);
        this.val = val;
    }

    public Node(T val, Node next) {
        this(val);
        this.next = next;
    }

    public void setNext(Node next) {
        // verify(next);
        this.next = next;
    }

    public Node getNext() {
        return this.next;
    }

    private void verify(Node node) {
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
        final Node other = (Node) obj;
        if (other.val == null) {
            return false;
        }
        return this.val == other.val;
    }

    @Override
    public int hashCode() {
        return (int) this.val;
        /*
        int hash = 3;
        hash = 53 * hash + (this.val != null ? this.val.hashCode() : 0);
        return hash;
        */
    }
}