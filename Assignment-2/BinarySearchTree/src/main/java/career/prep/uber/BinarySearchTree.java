package career.prep.uber;
/**
 * Instructions:
 *      In each of the methods, use pointers in languages that support pointers (e.g., Node * in C++) or references in languages that support references (e.g., Python).
 *      Your Node struct will be the same as for your doubly linked list except the two pointers/references should be left and right rather than next and prev.
 *      Note that the delete method is more difficult than the insert method; you won’t need it for the rest of the assignment so either stop or get help from your mentor if you are stuck after 40 minutes.
 *
 * Design comments:
 *      Originally, began implementing a balanced tree,
 *      but then decided that was not what the question was asking for
 *      and testing would need to be more extensive.
 *
 * Potential improvements:
 *      1. Use a balanced tree (AVL, red-black tree, etc.)
 *      2. Use an array or list to store the data instead of Node objects.
 *          Since the requirements are so simple, this would improve time & space slightly.
 *          But this does just sound like a different problem.
 *      3. Personally, I think that it would make more sense for the delete method to return a boolean or void.
 */
public class BinarySearchTree implements BinarySearchTreeI {
    private Node root;
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Go as far left as possible.
     * @return returns the minimum value in the BST
     *
     * Time: O(n), where n is the number of nodes in the tree, and
     *      all nodes are inserted in ascending order.
     *
     * Space: O(1), constant.
     */
    public int min() {
        if (root == null) {
            throw new IllegalArgumentException("[min: 2]: Root is null.");
        }
        Node node = root;
        // Go as far left as possible
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /**
     * Go as far right as possible.
     * @return returns the maximum value in the BST
     *
     * Time: O(n), where n is the number of nodes in the tree, and
     *      all nodes are inserted in descending order.
     *
     * Space: O(1), constant.
     */
    public int max() {
        if (root == null) {
            throw new IllegalArgumentException("[max: 2]: Root is null.");
        }
        Node node = root;
        // Go as far right as possible
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    /**
     * @param val
     * @return returns a boolean indicating whether val is present in the BST
     *
     * Time: O(n), where n is the number of nodes in the tree
     *      all nodes are inserted in either descending order or ascending, and
     *      the value is the lowest child in the tree.
     *
     * Space: O(1), constant.
     */
    public boolean contains(int val) {
        if (root == null) {
            return false;
        }
        Node node = root;
        while (node != null) {
            if (node.val == val) {
                return true;
            }
            node = node.val > val ? node.left : node.right;
        }
        return false;
    }

    /**
     * deletes the Node with data val, if it exists
     * @param val
     * @return the value of the deleted node
     * If tree is empty, return value. [Chose not to throw an exception.]
     *
     * If the value does not exist:
     *      The language "deletes the Node with data val, if it exists" seems to imply that the value may or may not exist.
     *      Therefore, if the value did not exist, an exception was not thrown and the value was simply returned.
     *      Personally, I would have the method either: return a boolean or throw an exception if the value does not exist in the tree.
     *
     * Time: O(h), where h is the time it takes to reinsert the right child
     *
     * Space: O(1), constant.
     */
    public int delete(int val) {
        Node parent = null;
        Node child = root;
        while(child != null && child.val != val) {
            parent = child;
            if (val < child.val) {
                child = child.left;
            } else {
                child = child.right;
            }
        }
        // delete the actual root of the tree
        if (parent == null) {
            root = deleteRoot(child);
        }
        // delete the root of the subtree
        else if (parent.left == child) {
            parent.left = deleteRoot(child);
        } else {
            parent.right = deleteRoot(child);
        }
        return val;
    }

    /**
     * @param node
     * @return the rebalanced tree with the root replaced with one of its children.
     *
     * Time: O(h), where h is the height of the subtree
     *      In the worst case, h = (n - 2)
     *          If the actual root of the tree is being deleted, and
     *          if the left child has not children, and
     *          if the right child only has increasingly valued children.
     *      The worst case scenario is a good illustration of the purpose of balanced trees.
     *
     * Space: O(1), constant.
     */
    private Node deleteRoot(Node node) {
        if (node == null) {
            return null;
        }
        // If the node to be deleted does not have 2 children,
        // then no need to worry about rebalancing.
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }
        // Find the smallest node in the right child's subtree.
        // Attach the left child's subtree to the right child's smallest grandchild.
        // Could have also done the opposite: Attach the right subtree to the left child's smallest grandchild.
        Node subtree = node.right;
        while (subtree.left != null) {
            subtree = subtree.left;
        }
        subtree.left = node.left;
        return node.right;
    }

    /**
     * For simplicity, do not allow duplicates
     * If val is already present, insert is a no-op
     * Creates a new Node with data val in the appropriate location
     * @param val
     *
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending or ascending order.
     *
     * Space: O(1), constant.
     */
    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        Node parent = null;
        Node child = root;
        while (child != null) {
            if (child.val == val) {
                return;
            }
            parent = child;
            if (val < child.val) {
                child = child.left;
            } else {
                child = child.right;
            }
        }
        if (val < parent.val) {
            parent.left = new Node(val);
        } else {
            parent.right = new Node(val);
        }
    }

    /**
     * @return the root of the tree.
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @return if the tree is empty.
     *
     * Time: O(1), constant.
     *
     * Space: O(1), constant.
     */
    public boolean isEmpty() {
        return root == null;
    }

    public void print() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }
}