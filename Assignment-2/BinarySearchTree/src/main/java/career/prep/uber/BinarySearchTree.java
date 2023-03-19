package career.prep.uber;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree implements BinarySearchTreeI {
    public Node root;             // root of BST
    public BinarySearchTree() {
        root = null;
    }

    /**
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
        Node prev = null;
        Node node = root;
        while (node != null) {
            if (node.val == val) {
                return;
            }
            prev = node;
            node = node.val > val ? node.left : node.right;
        }
        if (prev.val > val) {
            prev.left = new Node(val);
        } else {
            prev.right = new Node(val);
        }
    }

    /**
     * deletes the Node with data val, if it exists
     * @param val
     * @return
     */
    public int delete(int val) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root.val == val) {
            Node right = root.right;
            root = root.left;
            addNodes(right);
            return val;
        }
        Node node = root;
        while (node != null) {
            if (node.val > val) {
                if (node.left == null) {
                    throw new IllegalArgumentException();
                }
                if (node.left.val == val) {
                    Node right = node.left.right;
                    node.left = node.left.left;
                    addNodes(right);
                    return val;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    throw new IllegalArgumentException();
                }
                if (node.right.val == val) {
                    Node right = node.right.right;
                    node.right = node.right.left;
                    addNodes(right);
                    return val;
                }
                node = node.right;
            }
        }
        throw new IllegalArgumentException();
    }

    private void addNodes(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> toAdd = new LinkedList<>();
        toAdd.add(node);
        while (!toAdd.isEmpty()) {
            node = toAdd.poll();
            insert(node.val);
            if (node.left != null) {
                toAdd.add(node.left);
            }
            if (node.right != null) {
                toAdd.add(node.right);
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }
}