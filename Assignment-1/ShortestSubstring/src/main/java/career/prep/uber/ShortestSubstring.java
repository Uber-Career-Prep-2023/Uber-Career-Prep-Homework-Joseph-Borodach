package career.prep.uber;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Approach: Reset/catch-up two-pointer
 *
 * Time: O(n), or linear, where n is the length of the 2nd string.
 * Because in the worst case, the 1st string is much longer than the 2nd string and the sub string is not until the end of the 1st string:
 * 1) Instantiating the first map is m
 * 2) Iterating over the second map is 2n, because both pointers need to traverse close to the entire string
 * 3) All of the constant operations performed throughout the program, c
 * So, m + 2n + c == O(n) bc, at states above, n > m
 *
 * Space: O(n), or linear, where n is the length of each string.
 * Because in the worst case, both strings are, equally, quite lengthy.
 *
 * Therefore, the algorithm is not considered to be in place because of the additional space used.
 *
 * Unit tests are in separate test file
 *
 * ~30 min to write solution
 * ~10 min to write tests
 *
 * Notes:
 * Is it possible to do this close to an in place algo?
 */
public class ShortestSubstring {
    private static Logger logger;
    private final String s1, s2;
    private final int len1, len2;
    private int L, R, REM;

    /**
     * @throws IllegalArgumentException if input is null
     * @param strings
     */
    ShortestSubstring(String[] strings) {
        if (strings == null || strings.length != 2) {
            throw new IllegalArgumentException();
        }
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        s1 = strings[0];
        s2 = strings[1];
        len1 = s1.length();
        len2 = s2.length();
        if (len2 > len1) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return
     * What should be returned if there is not min string?
     */
    public String solveIt() {
        Map<Character, Integer> reqChars = new HashMap<>();
        for (char c : s2.toCharArray()) {
            reqChars.put(c, reqChars.getOrDefault(c, 0) + 1);
        }
        L = 0;
        R = Integer.MAX_VALUE;
        REM = len2;
        Map<Character, Integer> curChars = new HashMap<>();
        for (int l = 0, r = 0; r < len1 && (L == 0 ? R - L : R - (L-1)) >= len2; r++) {
            grow(curChars, reqChars, r);
            l = shrink(curChars, reqChars, l, r);
        }
        return R == Integer.MAX_VALUE ? "" : s1.substring(L, R+1);
    }

    private void grow(Map<Character, Integer> curChars, Map<Character, Integer> reqChars, int r) {
        char cr = s1.charAt(r);
        if (reqChars.containsKey(cr)) {
            int count = curChars.getOrDefault(cr, 0);
            if (count < reqChars.get(cr)) {
                --REM;
            }
            curChars.put(cr, count + 1);
        }
    }

    private int shrink(Map<Character, Integer> curChars, Map<Character, Integer> reqChars, int l, int r) {
        while (REM == 0 && l < len1) {
            if ((r - l) < (R - L)) {
                L = l;
                R = r;
            }
            char cl = s1.charAt(l++);
            if (reqChars.containsKey(cl)) {
                int count = curChars.getOrDefault(cl, 0);
                if (count > 0 && count <= reqChars.get(cl)) {
                    ++REM;
                }
                curChars.put(cl, count - 1);
            }
            // logger.log(Level.INFO, s1.substring(L, R+1));
        }
        return l;
        // logger.log(Level.INFO, s1.substring(l, r+1));
    }
}