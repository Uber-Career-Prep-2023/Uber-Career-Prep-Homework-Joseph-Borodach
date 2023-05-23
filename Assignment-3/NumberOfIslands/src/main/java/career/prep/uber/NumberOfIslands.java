package career.prep.uber;

/**
 * Question 4:
 *      Given a binary matrix in which 1s represent land and 0s represent water.
 *      Return the number of islands (contiguous 1s surrounded by 0s or the edge of the matrix).
 *
 * Data Structure for the Binary Matrix:
 *      - 2 dimensional array
 *      - List of lists
 *      - Could also use a graph.
 *      Note: Traditionally, this problem uses a 2 dimensional int array, so that is what I will do as well.
 *
 * Approaches:
 * 1. Brute Force:
 *      - Not sure
 *
 * 2. Graph Traversal (BFS, DFS)
 *      Design:
 *          - Need some way to record if a given island has already been discovered.
 *              a. Use an additional 2-dimensional boolean array.
 *              b. In place: Change the index's number to indicate - it's currently being explored or has already been explored.
 *                  - We don't need to worry about whether a number used to represent water or land
 *                    because if it was connected to more land, that land would have already been explored when exploring this piece of land.
 *          - This could be implemented recursively or iteratively.
 *              I like the recursive approach better - it is cleaner and more readable.
 *      Algorithm: For each island do the following:
 *          - if the index has already been discovered, return.
 *          - if it is water, continue - no island here.
 *          - Otherwise, it is a 1 - we have discovered an island:
 *              Increment the total number of islands by a total of 1.
 *              Explore all surrounding areas of land by calling a helper traversal method.
 *      Time: O(n), linear on the size of the input.
 *          - It is impossible to get around checking each index one time.
 *      Space: O(1), constant and in place.
 *
 * Assumptions:
 *      - Not responsible for checking that all indexes were either 0 or 1.
 *      - I'm pretty sure for the traditional problem you can assume that: Each row would have an equal number of columns.
 *          - However, I figured why not add more functionality.
 *
 */
public class NumberOfIslands {
    // Used class variables to make the code more readable.
    private int nRows;
    private int nCols;
    private int lastNRow;
    private int lastNCol;
    private int[][] islands;

    /**
     * Solve the problem.
     *
     * @param islands a binary matrix in which 1s represent land and 0s represent water.
     * @return the number of islands (contiguous 1s surrounded by 0s or the edge of the matrix).
     * @throws IllegalArgumentException if the island matrix is null.
     */
    public int solveIt(int[][] islands) {
        if (islands == null) {
            throw new IllegalArgumentException("Island matrix cannot be null");
        }
        nRows = islands.length;

        // Return If the island matrix is empty.
        if (nRows == 0) {
            return 0;
        }

        nCols = islands[0].length;

        // To reduce the number of diminish the number of constant math operations.
        lastNRow = nRows - 1;
        lastNCol = nCols - 1;

        this.islands = islands;

        int nIslands = 0;

        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                // This index is land and has not already been discovered and explored by a previous call to dfs.
                if (islands[row][col] == 1) {
                    nIslands++;
                    dfs(row, col);
                }
            }
        }
        return nIslands;
    }

    /**
     * Helper Method: Explore all surrounding islands.
     *
     * Design Comment: Checked for out of bounds before recursive call to diminish the number of constant boundary checks.
     *
     * @param row
     * @param col
     */
    private void dfs(int row, int col) {
        // This index is not land or is already in the process of being explored.
        if (islands[row][col] != 1) {
            return;
        }
        // Set to -1 so that don't explore this island again, resulting in stackoverflow exception.
        islands[row][col] = -1;

        // Explore all neighboring in bound indexes.
        // This row is not the first row in the matrix.
        if (row > 0) {
            dfs(row - 1, col);
        }
        // This column is not the first column in the matrix.
        if (col > 0) {
            dfs(row, col - 1);
        }
        // This row is not the last row in the matrix.
        if (row < lastNRow) {
            dfs(row + 1, col);
        }
        // This column is not the last column in the matrix.
        if (col < lastNCol) {
            dfs(row, col + 1);
        }
        // Mark this island as water since it has already been explored.
        islands[row][col] = 0;
    }
}