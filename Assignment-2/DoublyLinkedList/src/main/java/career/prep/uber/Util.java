package career.prep.uber;

public class Util<T> {
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
