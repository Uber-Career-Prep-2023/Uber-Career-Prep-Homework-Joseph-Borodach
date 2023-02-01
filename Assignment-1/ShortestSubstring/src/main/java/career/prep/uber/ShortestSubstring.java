package career.prep.uber;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShortestSubstring {
    private static Logger logger;
    private final String s1, s2;

    /**
     * Reset/catch-up two-pointer
     * Time: O(n) bc in the worst case, where the 1st string is much longer than the 2nd string and the sub string is not until the end of the 1st string:
     * 1) Instantiating the first map is m
     * 2) Iterating over the second map is 2n, because both pointers need to traverse close to the entire string
     * 3) All of the constant operations performed throughout the program, c
     * So, m + 2n + c == O(n) bc, at states above, n > m
     *
     * Space: ~2n = O(n)
     * Is not in place
     * ~30 min to write solution
     * ~10 min to write tests
     *
     * Notes:
     * Is it possible to do this close to an in place algo?
     *
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
    }

    /**
     * @return
     * What should be returned if there is not min string?
     */
    public String solveIt() {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len2 > len1) {
            throw new IllegalArgumentException();
        }
        Map<Character, Integer> requiredChars = new HashMap<>();
        for (char c : s2.toCharArray()) {
            requiredChars.put(c, requiredChars.getOrDefault(c, 0) + 1);
        }
        int L = 0;
        int R = Integer.MAX_VALUE;
        Map<Character, Integer> currentChars = new HashMap<>();
        for (int l = 0, r = 0, REM = len2; r < len1 && (L == 0 ? R - L : R - (L-1)) >= len2; r++) {
            char cr = s1.charAt(r);
            if (requiredChars.containsKey(cr)) {
                int count = currentChars.getOrDefault(cr, 0);
                if (count < requiredChars.get(cr)) {
                    --REM;
                }
                currentChars.put(cr, count + 1);
            }
            while (REM == 0 && l < len1) {
                if ((r - l) < (R - L)) {
                    L = l;
                    R = r;
                }
                char cl = s1.charAt(l++);
                if (requiredChars.containsKey(cl)) {
                    int count = currentChars.getOrDefault(cl, 0);
                    if (count > 0 && count <= requiredChars.get(cl)) {
                        ++REM;
                    }
                    currentChars.put(cl, count - 1);
                }
                logger.log(Level.INFO, s1.substring(L, R+1));
            }
            // logger.log(Level.INFO, s1.substring(l, r+1));
        }
        if (R == Integer.MAX_VALUE) {
            return "";
        }
        return s1.substring(L, R+1);
    }
}