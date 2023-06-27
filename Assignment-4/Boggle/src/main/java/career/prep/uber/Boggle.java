package career.prep.uber;

import career.prep.uber.Trie.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Boggle is a word game in which players compete to find the most words on a square grid of random letters.
 * Valid words must be at least three characters and formed from non-overlapping (i.e., a position on the board can only be used once in a word) adjacent (including diagonal) letters.
 * Given a Boggle board and a dictionary of valid words, return all valid words on the board.
 *
 * Notation:
 *      r = number of rows in the matrix.
 *      c = number of columns in the matrix.
 *      n = Length of all the words in the dictionary.
 * Time: O(Building the trie + Traversing the Matrix)
 *      = O(Length of all the words + (r * c))
 *      = O(n + (r * c)) = O(r * c).
 *      Defining the runtime:
 *          - Linear on the number of cells in the matrix, O(r * c).
 *          - Quadratic on the number of rows OR columns in the matrix, O(r^r) or O(c^c).
 *              In other words, if the number of rows and columns are equal,
 *              then the runtime is quadratic on the number of rows AND/OR columns.
 *              ["AND" because in such a case they are equal, and one and the same.]
 * Space: O(Size of the trie + return list)
 *      = O(Length of all the words + Length of all the words)
 *      = O(n + n) = O(n), linear.
 *
 * This implementation took approximately 45 minutes to complete.
 *
 * Please note that the documentation and testing may not be as comprehensive due to time constraints.
 */
public class Boggle {
    private int lenR; // Number of rows in the board
    private int lenC; // Number of columns in the board
    private Trie trie; // Trie data structure for storing the dictionary
    private char[][] board; // The Boggle board
    private List<String> validWords; // List to store the valid words found on the board

    /**
     * Solves the Boggle game by finding all valid words on the board.
     *
     * @param board the Boggle board represented as a 2D character array
     * @param words an array of strings representing the dictionary of valid words
     * @return a list of valid words found on the board
     * @throws IllegalArgumentException if the board or words parameter is null
     */
    public List<String> solveIt (char[][] board, String[] words) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null.");
        }
        if (words == null) {
            throw new IllegalArgumentException("Words cannot be null.");
        }

        // Initialize the return list.
        validWords = new ArrayList<>();
        if (board.length == 0) {
            return validWords;
        }
        trie = new Trie();

        // Build the trie from the dictionary words.
        for (String word : words) {
            if (word == null) {
                throw new IllegalArgumentException("Words cannot be null.");
            }
            if (word.length() < 3) {
                throw new IllegalArgumentException("Words must be comprised of at least 3 letters.");
            }
            // Other relevant exceptions are checked in the trie class.
            trie.insert(word);
        }

        lenR = board.length;
        lenC = board[0].length;
        this.board = board;

        // Perform depth-first search (DFS) on each cell of the board.
        for (int row = 0; row < lenR; row++) {
            for (int col = 0; col < lenC; col++) {
                dfs(row, col, trie.root, new StringBuilder());
            }
        }
        return validWords;
    }

    /**
     * Performs depth-first search (DFS) on the Boggle board to find valid words.
     *
     * @param row the current row index
     * @param col the current column index
     * @param node the current TrieNode in the Trie
     * @param sb the StringBuilder to build the word being formed
     * @throws IllegalStateException if the TrieNode is null
     * @throws IllegalArgumentException if the rows are null or the columns have different lengths
     */
    private void dfs(int row, int col, TrieNode node, StringBuilder sb) {
        if (row < 0 || col < 0 || row >= lenR || col >= lenC) {
            return;
        }
        // This should never happen.
        if (node == null) {
            throw new IllegalStateException("TrieNode cannot be null.");
        }
        if (node.validWord) {
            node.validWord = false;
            validWords.add(sb.toString());
        }
        if (board[row] == null) {
            throw new IllegalArgumentException("Rows cannot be null.");
        }
        if (board[row].length != lenC) {
            throw new IllegalArgumentException("Columns cannot be different lengths.");
        }
        // Check if the current cell is already being visited.
        if (board[row][col] == '#') {
            return;
        }
        char letter = verifyLetter(board[row][col]);
        if (!node.children.containsKey(letter)) {
            return;
        }
        TrieNode child = node.children.get(letter);

        // Append the current letter to the word being formed.
        // This check was added just to satisfy the samples.
        // In an interview I would discuss the tradeoffs of doing things one way versus another.
        // The logic behind this approach is that words should always begin with a capital letter.
        if (sb.length() == 0) {
            sb.append(Character.toUpperCase(letter));
        } else {
            sb.append(letter);
        }

        // Mark the current cell as visited.
        board[row][col] = '#';

        // Perform DFS in all possible directions.
        dfs(row - 1, col, child, new StringBuilder(sb)); // Left.
        dfs(row + 1, col, child, new StringBuilder(sb)); // Right.
        dfs(row, col - 1, child, new StringBuilder(sb)); // Up.
        dfs(row, col + 1, child, new StringBuilder(sb)); // Down.
        dfs(row + 1, col + 1, child, new StringBuilder(sb)); // Diagonal.
        dfs(row - 1, col - 1, child, new StringBuilder(sb)); // Diagonal.
        dfs(row - 1, col + 1, child, new StringBuilder(sb)); // Diagonal.
        dfs(row + 1, col - 1, child, new StringBuilder(sb)); // Diagonal.

        // Restore the cell value after backtracking.
        board[row][col] = Character.toUpperCase(letter);
    }

    /*****************
     * Helper Methods.
     *****************/
    /**
     * Verifies if the given letter is a valid letter.
     * Converts uppercase letters to lowercase if necessary.
     *
     * Time: O(1), constant.
     * Space: O(1), constant.
     *
     * @param letter the letter to be verified
     * @return the lowercase letter if the given letter is uppercase, otherwise returns the same letter
     * @throws IllegalArgumentException if the letter is not a valid letter
     */
    private Character verifyLetter(Character letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Words can only contain letters.");
        }
        // If letter is uppercase, convert it to lowercase.
        return Character.toLowerCase(letter);
    }
}