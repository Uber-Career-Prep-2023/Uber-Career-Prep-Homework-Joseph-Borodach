package career.prep.uber;

import java.util.*;

/**
 * The `Trie` class represents a data structure used for efficient string storage and retrieval operations.
 * It supports operations such as inserting a word into the trie, checking if a word is a valid word in the trie,
 * and removing a word from the trie.
 *
 * Design & Architecture:
 * - Children Data Structure:
 *    - The trie uses a `Map<Character, TrieNode>` called `children` to store child nodes.
 *      This choice was made for its flexibility, as it allows for efficient traversal and manipulation of the trie.
 *      Although the worst-case time complexity remains the same as using a fixed-size data structure,
 *      the average case performance is often better.
 * - Letter Casing:
 *    - Letter casing is ignored in this implementation because the API specifies that the trie should support only lowercase letters.
 *      However, in a real-world scenario, this decision would depend on the specific application requirements.
 * - Invalid Words:
 *    - Invalid words, including null, empty, or blank strings, are not allowed in the trie.
 *    - For any invalid word passed into a method, an `IllegalArgumentException` is thrown.
 *    - In an interview, it is important to discuss and understand the rationale behind handling invalid words differently,
 *      as it may vary depending on the application.
 *
 * Time and space complexities are discussed on a method basis.
 */
public class Trie {
    private TrieNode root;

    /**
     * Creates a new instance of the `Trie` class with an empty root node.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Adds a word to the trie.
     *
     * Edge Cases:
     *      - If a word already existed, an exception was thrown.
     *          - This decision should be made on an application basis.
     *          - The reason I did this was because when deleting a word,
     *            we were instructed to get rid of all extra nodes.
     *            In order to accomplish removing the nodes, I simply able to check how many remaining words they had!
     *
     * Time: O(length of the word) = O(n), linear.
     * Space: O(length of the word) = O(n), linear.
     *
     * Adds a word to the trie.
     * @param word the word to be inserted into the trie
     * @throws IllegalArgumentException if the word is null, empty, or blank
     *                                  if the word includes characters that are not letters.
     */
    public void insert(String word) {
        verifyWord(word);

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            // Verify the letter and convert it to lowercase if necessary.
            char letter = verifyLetter(word.charAt(i));

            // Retrieve the next node.
            TrieNode childNode = curr.children.computeIfAbsent(
                    // If the letter is there, simply retrieve the TrieNode for that letter.
                    letter,
                    // Otherwise, put the letter with a new TrieNode into the children map.
                    function -> new TrieNode()
            );
            // Increment the number of words in this trie.
            curr.words++;
            curr = childNode;
        }
        if (curr.validWord) {
            throw new IllegalArgumentException("Word already existed in trie.");
        }
        curr.validWord = true;
    }

    /**
     * Checks if a word is a valid word in the trie.
     *
     * Time: O(length of the word) = O(n), linear.
     * Space: O(1), constant.
     *      There is not more than additional constant amount of space needed.
     *
     * @param word the word to be checked
     * @return true if the word is a valid word in the trie, false otherwise.
     * @throws IllegalArgumentException if the word is null, empty, or blank
     *                                  if the word includes characters that are not letters.
     */
    public boolean isValidWord(String word) {
        verifyWord(word);

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            // verify the letter and convert it to lowercase if necessary.
            char letter = verifyLetter(word.charAt(i));

            if (!curr.children.containsKey(letter)) {
                // Verify the rest of the word.
                for (int j = i + 1; j < word.length(); j++) {
                    verifyLetter(word.charAt(j));
                }
                return false;
            }
            // Retrieve and move to the next node.
            curr = curr.children.get(letter);
        }
        return curr.validWord;
    }

    /**
     * "removes word, from the trie & deletes unused nodes."
     *
     * Note: The API instructed that the method "deletes unused nodes".
     *      - Personally, I would not have chosen to have the method actually remove the nodes,
     *        however, there are reasons to do so.
     *      - In order to do this, a stack will be used to maintain the previous nodes.
     *      - When arriving at the last node, we will remove all nodes that only had 1 child (in a reverse fashion)
     *        until arriving at a node which has more than 1 node.
     *
     * Chose to the throw an exception if the word did not exist in the trie.
     *      - It is also logical to just ignore the removal.
     *      - This decision should be made on application basis.
     *
     * Time: O(length of the word) = O(n), linear.
     * Space: O(1), constant.
     *      There is not more than additional constant amount of space needed.
     *
     * @param word the word to be removed from the trie
     * @throws IllegalArgumentException if the word is null, empty, or blank
     *                                  if the word includes characters that are not letters,
     *                                  if the word did not exist in the trie.
     */
    public void remove(String word) {
        verifyWord(word);

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            // verify the letter and convert it to lowercase if necessary.
            char letter = verifyLetter(word.charAt(i));
            if (!curr.children.containsKey(letter)) {
                throw new IllegalArgumentException("Word does not exist in the trie.");
            }
            TrieNode child = curr.children.get(letter);
            curr.words--;
            if (child != root && child.words <= 1) {
                curr.children.remove(letter);
            }
            curr = child;
        }
        if (!curr.validWord) {
            throw new IllegalArgumentException("Cannot remove a word which is not in the trie.");
        }
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

    /**
     * Verifies if the given word is a valid word.
     *
     * Time: O(1), constant.
     * Space: O(1), constant.
     *
     * @param word the word to be verified
     * @throws IllegalArgumentException if the word is null, empty, or blank, or contains non-letter characters
     */
    private void verifyWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Words cannot be null.");
        }
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Words cannot be empty.");
        }
        if (word.isBlank()) {
            throw new IllegalArgumentException("Words cannot be blank.");
        }
    }

    /************
     * Sub Class.
     ************/
    private class TrieNode {
        private int words; // A counter to track the number of words ending at this node.
        public boolean validWord; // Indicates if this node marks the end of a valid word.
        public Map<Character, TrieNode> children; // Stores child nodes.

        TrieNode() {
            words = 0;
            validWord = false;
            children = new HashMap<>();
        }
    }
}