package career.prep.uber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Notation:
 *   - m: Number of words in the dictionary.
 *   - k: Length of the average word in the dictionary.
 *   - n: Length of the input word.
 *
 * Approaches:
 * 1. Cached Trie:
 *   Algorithm:
 *      - Build a trie from the dictionary.
 *      - Iterate over the characters in the input word, simultaneously searching the trie.
 *          - If a given node does not contain the current letter, return false.
 *          - Cache the result in preceding nodes:
 *              - This avoids re-exploring the same path later.
 *              - Store the index of the word in the node which made the DFS call.
 *      - When the end of a word in the trie is reached, explore two routes:
 *          1. Continue searching down the current path in the trie.
 *          2. Continue searching from the root of the trie.
 *
 *   Time: O(m * k + n)
 *     - Building the trie takes O(m * k) time.
 *     - Searching the trie for the word takes O(n) time.
 *
 *   Space Complexity: O(m * k)
 *     - The space required to store the trie depends on the number of words and the average length of the words in the dictionary.
 *       If there are m words in the dictionary and the average length is k, the space complexity for the trie will be O(m * k).
 *     - Additional space is used for the recursive stack during the DFS traversal, which has a space complexity of O(n),
 *       where n is the length of the input word.
 *     - Overall, the space complexity is O(m * k) + O(n), which can be simplified to O(m * k) since m * k dominates n.
 *
 * Took ~45 Minutes.
 */
public class WordBreak {

    private Trie trie;
    private int length;
    private String word;

    public boolean solveIt(String word, String[] dictionary) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null.");
        }
        trie = new Trie();
        this.word = word.toLowerCase();
        length = word.length();
        // Build Trie.
        for (String validWord : dictionary) {
            // The word will be validated in the trie class.
            trie.insert(validWord);
        }
        return dfs(0, trie.root);
    }

    private boolean dfs(int index, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (index == length) {
            return node.validWord;
        }
        // We have tried searching this node with this index before,
        // If it did not work in the past, it won't work now.
        if (node.failed.contains(index)) {
            return false;
        }
        // If this is the end of a valid word,
        // try continuing with the remainder of the word from the root.
        if (node.validWord && dfs(index, trie.root)) {
            return true;
        }
        // Continue down this same path.
        char letter = word.charAt(index);

        int nextIndex = index + 1;
        // If the child node is null, then will return false in recursive call.
        TrieNode child = node.children.get(letter);
        // Continue searching for the word with the child node.
        if (dfs(nextIndex, child)) {
            return true;
        }
        // Record the search failure relationship between this node and index.
        node.failed.add(index);
        return false;
    }

    public class TrieNode {
        public boolean validWord; // Indicates if this node marks the end of a valid word.
        public Set<Integer> failed;
        public Map<Character, TrieNode> children; // Stores child nodes.

        TrieNode() {
            validWord = false;
            failed = new HashSet<>();
            children = new HashMap<>();
        }
    }

    public class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * @param word the word to be inserted into the trie
         * @throws IllegalArgumentException if the word is null, empty, or blank, or if the word includes characters that are not letters.
         */
        public void insert(String word) {
            verifyWord(word);
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                curr = curr.children.computeIfAbsent(verifyLetter(word.charAt(i)), function -> new TrieNode());
            }
            if (curr.validWord) {
                throw new IllegalArgumentException("Word already existed in trie.");
            }
            curr.validWord = true;
        }

        /**
         * @param letter the letter to be verified
         * @return the lowercase letter if the given letter is uppercase, otherwise returns the same letter
         * @throws IllegalArgumentException if the letter is not a valid letter
         */
        private Character verifyLetter(Character letter) {
            if (!Character.isLetter(letter)) {
                throw new IllegalArgumentException("Words can only contain letters.");
            }
            return Character.toLowerCase(letter);
        }

        /**
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
    }
}