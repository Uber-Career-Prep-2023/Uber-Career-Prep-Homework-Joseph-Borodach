package career.prep.uber;

import java.util.LinkedList;

public interface DedupSortedListI<T extends Comparable<? super T>> {
    Node<T> solveIt(Node<T> root);
}
