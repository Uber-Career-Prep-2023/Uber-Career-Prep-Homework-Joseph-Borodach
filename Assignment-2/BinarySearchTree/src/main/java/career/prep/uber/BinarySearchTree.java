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
     * If tree is empty, either throw an exception or return value.
     */
    public int delete(int val) {
        Node pre = null;
        Node cur = root;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = val < cur.val ? cur.left : cur.right;
        }
        if (pre == null) {
            root = delete(cur);
        } else if (pre.left == cur) {
            pre.left = delete(cur);
        } else {
            pre.right = delete(cur);
        }
        return val;
    }

    private Node delete(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        Node next = min(node.right);
        next.left = node.left;
        return node.right;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Time: O(n), where n is the number of nodes in the tree and all nodes are inserted in descending order.
     * Space: O(1), constant.
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

    private Node getParent(Node prev, Node node, int val) {
        while (node != null && node.val != val) {
            prev = node;
            node = val < node.val ? node.left : node.right;
        }
        if (node != null && node.val == val) {
            return null;
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