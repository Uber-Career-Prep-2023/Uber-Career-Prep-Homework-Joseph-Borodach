package career.prep.uber;
/**
 * Approach: Reset/catch-up two-pointer
 *
 * Time: O(n), or linear, where n is equal to the size of the strings.
 * In the worst case, both strings are ultimately the same but have numerous backspaces throughout them:
 * 1) Both strings need to be iterated over, but not simultaneously: Each string demands removing chars at different points.
 * Therefore, both strings are iterated over, essentially, separately == 2n.
 * 2) There are numerous other constant operations which are negligent to the overall runtime of the program.
 * Overall runtime == 2n + c == O(n)
 *
 * Space: O(1)
 *
 * Therefore, the algorithm is considered to be in place.
 *
 * Unit tests are in separate test file.
 *
 * ~30 min to write solution
 * ~10 min to write tests
 *
 */
public class BackspaceStringCompare {
    private final String s1;
    private final String s2;
    private final int len1;
    private final int len2;


    /**
     * @throws IllegalArgumentException if input is null
     * @param s1
     * @param s2
     */
    BackspaceStringCompare(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException();
        }
        this.s1 = s1;
        this.s2 = s2;
        len1 = s1.length();
        len2 = s2.length();
    }

    /**
     * @return
     */
    public boolean solveIt() {
        for (int P1 = len1 - 1, P2 = len2 - 1; P1 >= 0 || P2 >= 0; P1--, P2--) {
            if (P1 >= 0 && s1.charAt(P1) == '#' || P2 >= 0 && s2.charAt(P2) == '#') {
                P1 = getNextChar(s1, P1, 0);
                P2 = getNextChar(s2, P2, 0);
            } else if (P1 < 0 || P2 < 0 || s1.charAt(P1) != s2.charAt(P2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param s
     * @param P
     * @param d
     * @return
     */
    private int getNextChar(final String s, int P, int d) {
        if (P < 0 || s.charAt(P) != '#') {
            return P + 1;
        }
        for (P -= 1, d = 1; P >= 0 && d > 0; P--) {
            d = s.charAt(P) == '#' ? d + 1 : d - 1;
        }
        return P + 1;
    }
}