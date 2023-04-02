package career.prep.uber;

/**
 * @author Joseph Borodach
 * @version 1
 *
 * Instructions:
 *      Question 11: FloorInBST
 *      Given a target numeric value and a binary search tree, return the floor (greatest element less than or equal to the target) in the BST.
 *
 * Assumptions:
 *      a. Permitted negative values.
 *      b. Decided to return node and not value because of the problem's description.
 *          However, both make sense.
 *      c. If there was no floor node, an IllegalArgumentException is thrown with a descriptive message.
 *
 * Pseudo Code: bfs
 *      a. Initialize:
 *          currentNode = root
 *              To iteratively explore the tree
 *          floorNode = null
 *      b. while (currentNode != null):
 *          if (currentNode.val == target):
 *              return currentNode
 *          else if (currentNode.val > target):
 *              set currentNode = left child,
 *              [don't pursue right child because it's greater than target]
 *          else:
 *              update floorNode
 *              set currentNode = right child
 *              [don't pursue left child because it's less than currentNode]
 *
 * Example #1:
 *      a. curr = 10:
 *          update greatestNode = 10
 *          set curr = 16 (right child)
 *      b. curr = 16:
 *          16 is greater than 13 (target)
 *          set curr = 13 (left child)
 *      c. curr = 13:
 *          update greatestNode = 13
 *          break out of while loop, found target
 *
 * Example #2:
 *      a. curr = 10:
 *          update greatestNode = 10
 *          set curr = 16 (right child)
 *      b. curr = 16:
 *          16 is greater than 15 (target)
 *          so, set curr = 13 (left child)
 *      c. curr = 13:
 *          update greatestNode = 13
 *          set curr = null (right child)
 *      d. curr == null:
 *          while loop ends
 *
 * @time: O(h), where is h is the height of the tree.
 * @space: O(1), constant.
 */
public class FloorInBST implements FloorInBSTI {

    /**
     * Given a target numeric value and a binary search tree, return the floor (greatest element less than or equal to the target) in the BST.
     *
     * @param root the root of the binary search tree
     * @param target the target value.
     * @return the floor node (greatest element less than or equal to the target) in the BST
     * @throws IllegalArgumentException if root is null or there is no floor node
     */
    public Node solveIt(Node root, int target) {
        if (root == null) {
            throw new IllegalArgumentException("The root cannot be null.");
        }

        Node currentNode = root;
        Node floorNode = null;
        while (currentNode != null) {
            if (currentNode.val == target) {
                return currentNode;
            } else if (currentNode.val > target) {
                currentNode = currentNode.left;
            } else {
                // update floor node
                if (floorNode == null || currentNode.val > floorNode.val) {
                    floorNode = currentNode;
                }
                currentNode = currentNode.right;
            }
        }
        if (floorNode == null) {
            throw new IllegalArgumentException("There is no floor node in the BST.");
        }
        return floorNode;
    }
}