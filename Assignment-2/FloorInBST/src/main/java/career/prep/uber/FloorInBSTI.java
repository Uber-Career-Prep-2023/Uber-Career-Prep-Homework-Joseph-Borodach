package career.prep.uber;

/**
 * @author Joseph Borodach
 * @version 1
 *
 * Instructions:
 *      Question 11: FloorInBST
 *      Given a target numeric value and a binary search tree, return the floor (greatest element less than or equal to the target) in the BST.
 *
 * <p><img src="{@FloorInBST}/ads.png"></p>
 */
public interface FloorInBSTI {
    /**
     * Given a target numeric value and a binary search tree, return the floor (greatest element less than or equal to the target) in the BST.
     *
     * @param root of the binary search tree
     * @param val target value.
     * @return the floor (greatest element less than or equal to the target) in the BST
     * @throws if root is null
     * If there was no floor node, returned null & did not throw an IAE.
     */
    Node solveIt(Node root, int val);
}
