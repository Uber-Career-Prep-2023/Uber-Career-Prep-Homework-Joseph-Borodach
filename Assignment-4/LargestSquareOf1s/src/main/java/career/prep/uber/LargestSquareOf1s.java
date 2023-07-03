package career.prep.uber;

/**
 * Excited that I've never done this problem before & it seems like a good challenge.
 *
 * Note Square Sizes: 1 (1 x 1), 4 (2 x 2), 9 (3 x 3), etc.
 *
 * Notation:
 *   r: Number of rows in the matrix.
 *   c: Number of columns in the matrix.
 *
 * Approaches:
 * 1. Brute Force:
 *   Algorithm:
 *      For each entry:
 *          if entry == 0:
 *              continue
 *          Grow the square: Expand one row & column at a time.
 *              Iterate down the "newest" row and column.
 *              End If any entry != 1.
 *              Otherwise, continuing growing the square.
 *   Time: O((r * c) ^ 2), quadratic.
 *   Space: O(1), constant.
 *
 * 2. Tabulation: Dynamic Programming:
 *   Note: In an interview it's important to ask the interviewer if you can do this in place, and to discuss the tradeoffs.
 *          Originally, I implemented it in place.
 *          But, then I felt sort of guilty about how messy & awkward the conversions from char to integers were.
 *          In the end, I kept the in place solution because my inclination for optimality was too strong.
 *          I'm sorry for the mess! In an interview I will make sure it's ok with the interviewer!
 *
 *   Basic Idea:
 *      Instead of manually/explicitly checking if the square can be expanded/grown,
 *      Iterate in the same manner as before, but this time "look over you're shoulder":
 *          Take the min size of the 3 "previous" entries.
 *          Add that to the cache.
 *
 *   Time: O(r * c), linear.
 *   Space: O(1), constant.
 *
 *
 *   To make sure that this idea is not nonsense & to work out the logic,
 *   lets go through a few examples:
 *   Example #1:
 *      1  1  1
 *      1  1  1
 *      1  1  1
 *
 *      We skip the first row and column since they cannot be the larger than 1x1.
 *
 *
 *      Move to 1,1.
 *          Up = 1
 *          Left = 1
 *          Diagonal = 1
 *          It upgrades to 2!
 *      1  1  1          1  1  1
 *      1  _  1     =    1  2  1
 *      1  1  1          1  1  1
 *
 *
 *      Move to 1,2.
 *          Up = 1
 *          Left = 2
 *          Diagonal = 1
 *          It upgrades to 2!
 *      1  1  1          1  1  1
 *      1  2  _     =    1  2  2
 *      1  1  1          1  1  1
 *
 *
 *      Move to 2,1.
 *          Up = 2
 *          Left = 1
 *          Diagonal = 1
 *          It upgrades to 2!
 *      1  1  1          1  1  1
 *      1  2  2     =    1  2  2
 *      1  _  1          1  2  1
 *
 *
 *      HERES FOR THE FUN!!!
 *      Move to 2,2.
 *          Up = 2
 *          Left = 2
 *          Diagonal = 2
 *          It upgrades to 3!!!!! Hurray!
 *      1  1  1          1  1  1
 *      1  2  2     =    1  2  2
 *      1  2  _          1  2  3
 *
 *
 * Error Checks:
 *    1. I know I don't need to check that all entries are 0s & 1s,
 *       but the defensive programmer in me can't help it.
 *
 *
 * Time Spent: Over an Hour.
 *   - I got the brute force solution working in 20 minutes.
 *   - To actually figure out how the dynamic programming solution should work,
 *     meaning the logic, not the actual code, took me a lot of time.
 *     ~ 45 minutes.
 *     Especially, walking through the examples.
 *     Coding it took < 10 minutes
 *
 *   - I got say, even though I spent a decent amount of time on this,
 *     I'm proud I got the dynamic programming solution to work.
 *
 */
public class LargestSquareOf1s {

    private int lenR;
    private int lenC;
    private char[][] matrix;

    /**
     * @param matrix
     * @return
     */
    public int solveIt(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null.");
        }
        this.matrix = matrix;

        lenR = matrix.length;
        if (lenR == 0) {
            return 0;
        }

        // Could check that all columns are equal length, but not generally necessary.
        // Could mention the idea in an interview.
        lenC = matrix[0].length;
        if (lenC == 0) {
            return 0;
        }

        int algorithm = 1;
        if (algorithm == 0) {
            return bruteForce();
        }
        return tabulation();
    }

    /**
     *
     * @return
     */
    private int tabulation() {
        int largestSquare = 0;
        // Edge case.
        if (lenR == 1 && lenC == 1) {
            verify(matrix[0][0]);
            return matrix[0][0] - '0';
        }
        // Convert from char to int.
        // As long as we convert each one to an int,
        // we will be dealing with ints which allows for in place dynamic programming.

        // Since we are skipping the first row and col,
        // Need to convert them now, otherwise they will still be
        // chars later when we look over our shoulders.

        // Convert the first row.
        for (int col = 0; col < lenC; col++) {
            verify(matrix[0][col]);
            matrix[0][col] -= '0';
            largestSquare = Math.max(matrix[0][col], largestSquare);
        }
        // Convert the first column
        // Skip the first entry because that was done when converting the first row.
        for (int row = 1; row < lenR; row++) {
            verify(matrix[row][0]);
            matrix[row][0] -= '0';
            largestSquare = Math.max(matrix[row][0], largestSquare);
        }

        for (int row = 1; row < lenR; row++) {
            for (int col = 1; col < lenC; col++) {
                verify(matrix[row][col]);
                // Convert to int.
                matrix[row][col] -= '0';
                if (matrix[row][col] == 1) {
                    int min = matrix[row - 1][col - 1];         // Diagonal.
                    min = Math.min(min, matrix[row - 1][col]);  // Up.
                    min = Math.min(min, matrix[row][col - 1]);  // Left.
                    matrix[row][col] += min;
                    largestSquare = Math.max(matrix[row][col], largestSquare);
                }
            }
        }
        return largestSquare;
    }

    private void verify(char c) {
        if (c != '0' && c != '1') {
            throw new IllegalArgumentException("Entries must be 0s & 1s. Cannot be: " + c);
        }
    }

    private int bruteForce() {
        int largestSquare = 0;
        for (int row = 0; row < lenR; row++) {
            for (int col = 0; col < lenC; col++) {
                verify(matrix[row][col]);
                if (matrix[row][col] != '1') {
                    continue;
                }
                // Expand the square as much as possible.
                int nextRow = row + 1;
                int nextCol = col + 1;
                while (canGrowSquare(row, col, nextRow, nextCol)) {
                    nextRow++;
                    nextCol++;
                }
                int currentSquare = nextRow - row;
                largestSquare = Math.max(currentSquare, largestSquare);
            }
        }
        return largestSquare;
    }

    private boolean canGrowSquare(int startRow, int startCol, int lastRow, int lastCol) {
        if (lastRow == lenR || lastCol == lenC) {
            return false;
        }
        // Check new column.
        for (int i = startRow; i <= lastRow; i++) {
            verify(matrix[i][lastCol]);
            if (matrix[i][lastCol] != '1') {
                return false;
            }
        }
        // Check new row.
        for (int i = startCol; i < lastCol; i++) {
            verify(matrix[lastRow][i]);
            if (matrix[lastRow][i] != '1') {
                return false;
            }
        }
        return true;
    }
}



















