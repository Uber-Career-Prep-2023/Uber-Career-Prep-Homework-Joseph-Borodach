package career.prep.uber;

/**
 * @author josephborodach
 *
 * Question #7: Given a string, return the string with the order of the space-separated words reversed.
 *
 * Approaches:
 * 1. Stack.
 *      Algorithm:
 *      - Iterate over the string, pushing sub strings (seperated by spaces) to the stack.
 *      - Iterate over the stack, adding sub strings to a return string.
 *      Time: O(iterate over the string + get each substring to push + add each substring to a StringBuilder + convert StringBuilder to a string)
 *          = O(n + n + n + n) = O(4n) = O(n), linear.
 *          Note: Cannot fundementally do better than O(n), linear time.
 *      Space: O(Space in stack to store each string + StringBuilder + answer string)
 *           = O(n + n + n) = O(3n) = O(n), linear.
 * 2. Just a String Builder & 3 Pointers
 *      Algorithm:
 *      - Begin from the end of the string, move left pointer left until reaching a space.
 *      - Add subword to StringBuilder.
 *      - Do the above steps until reaching the beginning of the string.
 *      - Convert StringBuilder to string.
 *      Time: O(iterate over the string + adding each substring to StringBuilder + convert StringBuilder to a string)
 *          = O(n + n + n) = O(3n) = O(n), linear.
 *          Note: That is a constant improvement! O(3n) is better than O(4n)!
 *      Space: O(StringBuilder + answer string)
 *           = O(n + n) = O(2n) = O(n), linear.
 *          Note: That is a constant improvement! O(2n) is better than O(3n)!
 * 3. Use built in methods.
 *      - Trim string, removing leading spaces.
 *      - Split string into list of strings.
 *      - Reverse the words in the list.
 *      - Join the list into a string seperated by spaces.
 *      Time: O(n), linear.
 *      Space: O(n), linear.
 *
 * Note: I implemented 2 solutions, both of which were variations of the 2 approach to the problem.
 *      1. Did NOT use java functions: Since many interviewers would probably prefer doing the groundwork oneself.
 *      2. Used java functions: Much neater and cleaner.
 *
 *
 * Some Thoughts about the Problem:
 *      - I liked this problem!
 *      - I think this problem would be even more interesting if we were given an array because then it could be done in place.
 *      - Java strings are frusturating to deal with because insertion and removal is O(n).
 */
public class ReverseWords {

    /**
     * Reverses the order of space-separated words in the input string.
     *
     * @param str the input string.
     * @return the string with the order of space-separated words reversed.
     * @throws IllegalArgumentException if the string argument is null.
     */
    public String solveIt(final String str) {
        if (str == null) {
            throw new IllegalArgumentException("String argument cannot be null");
        }
        // Ignored these scenarios - just returned an empty string.
        if (str.isEmpty() || str.isBlank()) {
            return "";
        }
        int algorithm = 0;
        if (algorithm == 0) {
            return functionLessSolution(str);
        }
        return functionsSolution(str);
    }

    /**
     * Did NOT use java functions: Since many interviewers would probably prefer doing the groundwork oneself.
     *
     * @param str the input string.
     * @return the string with the order of space-separated words reversed.
     */
    private String functionLessSolution(String str) {
        StringBuilder sb = new StringBuilder();
        int end = str.length() - 1;
        int start = end;

        while (start >= 0) {
            if (str.charAt(start) != ' ') {
                start--;
                continue;
            }

            // Consecutive spaces or ended with space: Ignored them.
            if (start == end) {
                start--;
                end--;
                continue;
            }

            // start + 1: Don't want to add the space in front of the word.
            addWord(start + 1, end, sb, str);

            // Move Pointers.
            while (start >= 0 && str.charAt(start) == ' ') {
                start--;
            }
            end = start;

            // Not the first word.
            if (start >= 0) {
                sb.append(' ');
            }
        }

        // If String began with space: Ignored it.
        if (start != end) {
            // First word in the string. start + 1 because start will be at -1.
            addWord(start + 1, end, sb, str);
        }

        return sb.toString();
    }

    /**
     * Used java functions: Much neater and cleaner.
     *
     * @param str the input string.
     * @return the string with the order of space-separated words reversed.
     */
    private String functionsSolution(String str) {
        // Remove spaces from before and after the string.
        str = str.trim();

        String[] words = str.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }

        // Remove last space appended to the string.
        return sb.toString().trim();
    }

    /**
     * Adds the word from the input string to the StringBuilder.
     *
     * @param start the pointer at the left-most index of the word.
     * @param end   the pointer at the right-most index of the word.
     * @param sb    the StringBuilder with the reversed words.
     * @param str   the argument string.
     */
    private void addWord(final int start, final int end, StringBuilder sb, final String str) {
        String word = str.substring(start, end + 1);
        sb.append(word);
    }
}