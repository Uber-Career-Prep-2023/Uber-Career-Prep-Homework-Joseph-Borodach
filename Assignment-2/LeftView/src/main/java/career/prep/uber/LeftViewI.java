package career.prep.uber;

/**
 * @author Joseph Borodach
 * @version 1
 *
 * Instructions:
 *      Question 10: LeftView
 *      Given a binary tree, create an array of the left view (leftmost elements in each level) of the tree.
 *
 * <p><img src="examples.png"></p>
 *
 */
public interface LeftViewI {
    /**
     * Create and return an array of the leftmost view of the tree
     *
     * @param root of the binary tree
     * @return an array of the leftmost view of the tree
     * @throws IllegalArgumentException if the root is null
     */
    int[] solveIt(Node root);
}
