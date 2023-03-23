package career.prep.uber;

public interface CopyTreeI<T extends Comparable<T> & java.io.Serializable> {
    Node<T> solveIt(Node<T> root);
}
