package career.prep.uber;

public class BackspaceStringCompare {
    private final String s1;
    private final String s2;

    /**
     * Time: O(n)
     * Space: O(1)
     * In place
     * ~30 min to write solution
     * ~10 min to write tests
     *
     * Approaches:
     * 1) More brute force oriented: Remove any backspaces
     * 2) Only pointers
     *
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
    }

    /**
     * Approach #2
     * Reset/catch-up two-pointer
     * Time: O(n)
     * Space: O(1)
     * In place
     * Compare as you go
     * @return
     */
    public boolean solveIt() {
        int R1 = s1.length() - 1;
        int R2 = s2.length() - 1;
        while (R1 >= 0 && R2 >= 0) {
            R1 = getNextChar(s1, R1);
            R2 = getNextChar(s2, R2);
            if (R1 < 0 || R2 < 0) {
                break;
            }
            if (s1.charAt(R1) != s2.charAt(R2)) {
                return false;
            }
            R1--;
            R2--;
        }
        if (R1 >= 0) {
            R1 = getNextChar(s1, R1);
        } else if (R2 >= 0) {
            R2 = getNextChar(s2, R2);
        }
        return R1 < 0 && R2 < 0;
    }

    private int getNextChar(String s, int R) {
        if (s.charAt(R) != '#') {
            return R;
        }
        int S = 0;
        for (int L = R; L >= 0 && s.charAt(L) == '#'; L--) {
            S++;
        }
        return (R - ((S * 2) - 1)) - 1;
    }

    /**
     * Approach #1: More brute force oriented: Remove any backspaces
     * @return
     */
    public boolean solveIt1() {
        StringBuilder sb1 = new StringBuilder(s1);
        StringBuilder sb2 = new StringBuilder(s2);
        removeBackSpaces(sb1);
        removeBackSpaces(sb2);
        return sb1.compareTo(sb2) == 0;
    }

    /**
     * 1) Base Case: There is no sb.charAt(R) != '#', continue moving to the right
     * 2) Count the number of
     * {0 1 2 3 4 5  6  7  8  9  10 11 12 13 14 15 16 17 18 19}
     * {u # U b e r \s  C  a  r  e  e  e  #  r  \s P  r  e  p}
     * {ab##AB##}
     * @param sb
     */
    private void removeBackSpaces (StringBuilder sb) {
        int R = sb.length() - 1;
        while (R >= 0) {
            if (sb.charAt(R) != '#') {
                R--;
                continue;
            }
            int S = 0;
            for (int L = R; L >= 0 && sb.charAt(L) == '#'; L--) {
                S++;
            }
            S = (R - ((S * 2) - 1));
            if (S < 0) {
                throw new IllegalArgumentException();
            }
            sb.delete(S, R+1);
            R = S - 1;
        }
    }
}
