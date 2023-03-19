package career.prep.uber;

public class Util<T> {
    public void verify(Node<T> head, T val, Node<T> loc) {
        verify(head);
        verify(loc);
        verify(val);
    }

    public void verify(Node<T> head, T val) {
        verify(head);
        verify(val);
    }

    public void verify(Node<T> n1, Node<T> n2) {
        verify(n1);
        verify(n2);
    }

    public void verify(Node<T> node) {
        if (node == null) {
            throw new IllegalStateException();
        }
    }

    public void verify(T val) {
        if (val == null) {
            throw new IllegalStateException();
        }
    }
}
