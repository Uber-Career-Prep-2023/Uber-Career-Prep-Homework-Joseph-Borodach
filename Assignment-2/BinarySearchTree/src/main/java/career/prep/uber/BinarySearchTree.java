package career.prep.uber;

public class BinarySearchTree implements BinarySearchTreeI {
    public Node root;
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending order.
     * Space: O(1), constant.
     * Go as far left as possible.
     * @return returns the minimum value in the BST
     */
    public int min() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /**
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending order.
     * Space: O(1), constant.
     * Go as far right as possible.
     * @return returns the maximum value in the BST
     */
    public int max() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    /**
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending order.
     * Space: O(1), constant.
     * @param val
     * @return returns a boolean indicating whether val is present in the BST
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
     * Time: O(h), where h is the time it takes to reinsert the right child
     * Space: O(1), constant.
     * deletes the Node with data val, if it exists
     * @param val
     * @return
     */
    public int delete(int val) {
        if (root == null) {
            throw new IllegalArgumentException("[delete 2]: Value does not exist in tree.");
        }
        if (root.val == val) {
            Node right = root.right;
            root = root.left;
            insert(right);
            return val;
        }
        Node node = root;
        while (node != null) {
            if (node.val > val) {
                if (node.left == null) {
                    throw new IllegalArgumentException("[delete 12]: Value does not exist in tree.");
                }
                if (node.left.val == val) {
                    Node right = node.left.right;
                    node.left = node.left.left;
                    insert(right);
                    return val;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    throw new IllegalArgumentException("[delete 23]: Value does not exist in tree.");
                }
                if (node.right.val == val) {
                    Node right = node.right.right;
                    node.right = node.right.left;
                    insert(right);
                    return val;
                }
                node = node.right;
            }
        }
        throw new IllegalArgumentException("[delete 36]: Value does not exist in tree.");
    }

    /**
     * For simplicity, do not allow duplicates
     * If val is already present, insert is a no-op
     * creates a new Node with data val in the appropriate location
     * @param val
     */
    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        Node prev = getParent(null, root, val);
        if (prev != null) {
            insertNode(prev, new Node(val));
        }
    }

    /**
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending order.
     * Space: O(1), constant.
     * @param child
     */
    private void insert(Node child) {
        if (child == null) {
            return;
        }
        if (root == null) {
            root = child;
            return;
        }
        Node prev = getParent(null, root, child.val);
        if (prev != null) {
            insertNode(prev, child);
        }
    }

    private Node getParent(Node prev, Node node, int val) {
        while (node != null) {
            if (node.val == val) {
                return null;
            }
            prev = node;
            node = node.val > val ? node.left : node.right;
        }
        return prev;
    }

    private void insertNode(Node parent, Node child) {
        if (parent.val > child.val) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     * Time: O(1), constant.
     * Space: O(1), constant.
     * @return the root of the tree.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Time: O(1), constant.
     * Space: O(1), constant.
     * @return if the tree is empty.
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