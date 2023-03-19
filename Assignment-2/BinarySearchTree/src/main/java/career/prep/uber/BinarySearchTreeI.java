package career.prep.uber;

public interface BinarySearchTreeI<T> {
    /**
     * @return returns the minimum value in the BST
     */
    int min();

    /**
     * @return returns the maximum value in the BST
     */
    int max();

    /**
     * @param val
     * @return returns a boolean indicating whether val is present in the BST
     */
    boolean contains(int val);

    /**
     * For simplicity, do not allow duplicates. If val is already present, insert is a no-op
     * creates a new Node with data val in the appropriate location
     * @param val
     */
    void insert(int val);

    /**
     * deletes the Node with data val, if it exists
     * @param val
     * @return
     */
    int delete(int val);
}
