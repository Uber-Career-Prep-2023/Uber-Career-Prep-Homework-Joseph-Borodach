package career.prep.uber;

/**
 * Instructions:
 *      Question 5: IsBST
 *      Given a binary tree, determine if it is a binary search tree.
 *
 * @param <T> a number.
 */
public interface IsBSTI<T extends Comparable<? super T>> {
    boolean solveIt(Node<T> root);
}
