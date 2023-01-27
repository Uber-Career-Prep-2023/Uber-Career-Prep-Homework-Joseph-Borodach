package career.prep.uber;

import java.util.*;

public class ReverseVowels {
    private final StringBuilder sb;
    private final Set<Character> vowels;

    /**
     * Time: O(n)
     * Space: O(n)
     * Is not in place
     * Is stable
     * ~10 min to write solution
     * ~10 min to write tests
     *
     * Notes:
     * a) If solution was in place, then the runtime would have suffered:
     * could be quadratic because strings in java are immutable
     * b) Could have use a char array but was less clean of a solution
     * c) Could have chosen between upper and lowercase instead of adding both to set,
     * but that seemed unnecessarily complicated.
     * @throws IllegalArgumentException if input is null
     * @param s
     */
    ReverseVowels (String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.sb = new StringBuilder(s);
        vowels = new HashSet<>(Arrays.asList('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'));

    }

    /**
     * find left vowel
     * find right vowel
     * swap them, and update both pointers
     * @return
     * If input is empty, the for loop won't get off the ground.
     */
    public String solveIt() {
        for (int L = 0, R = sb.length() - 1; L < R; ) {
            if (!vowels.contains(sb.charAt(L))) {
                L++;
            } else if (!vowels.contains(sb.charAt(R))) {
                R--;
            } else {
                char temp = sb.charAt(L);
                sb.setCharAt(L++, sb.charAt(R));
                sb.setCharAt(R--, temp);
            }
        }
        return sb.toString();
    }
}
