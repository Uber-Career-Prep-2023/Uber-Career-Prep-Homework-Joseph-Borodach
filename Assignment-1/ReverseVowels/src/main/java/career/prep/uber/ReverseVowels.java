package career.prep.uber;

/**
 * Approach: Variable size (shrinking/growing) sliding window.
 *
 * Time: O(n), or linear, where n is equal to the size of the input string.
 * In every case, the entire string will need to be iterated over by the 2 pointers.
 * The numerous constant operations performed throughout the program are negligent to the overall runtime of the program.
 *
 * Space: O(n), or linear, where n is equal to the size of the input string.
 * In every case, a char array is created containing the chars comprising the string.
 *
 * Therefore, is not considered to be in place.
 *
 * Unit tests are in separate test file
 *
 * ~10 min to write solution
 * ~10 min to write tests
 *
 * Notes:
 * a) If solution was in place, then the runtime would have suffered: could be quadratic because strings in java are immutable.
 * b) Could have use a char array but was less clean of a solution
 * c) Could have chosen between upper and lowercase instead of adding both to set, but that seemed unnecessarily complicated.
 * d) Originally, I used a set of vowels to check if a given char was a vowel, but then I realized that
 *      (1) it would be faster to just use an if statement and
 *      (2) I could add in an addition check to see if the given char was even a letter
 */
public class ReverseVowels {
    private String s;

    /**
     * @throws IllegalArgumentException if input is null
     * @param s
     */
    ReverseVowels (String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.s = s;

    }

    /**
     * find left vowel
     * find right vowel
     * swap them, and update both pointers
     * Notes:
     * a) 3 ways to convert char[] to string: char[] a = {'a', 'b'};
     * 1) new String(a)
     * 2) String.valueOf(a)
     * 3) String.copyValueOf(a)
     *
     * @return
     * If input is empty, the for loop won't get off the ground.
     */
    public String solveIt() {
        char[] chars = s.toCharArray();
        for (int L = 0, R = s.length() - 1; L < R; ) {
            if (!isVowel(chars[L])) {
                L++;
            } else if (!isVowel(chars[R])) {
                R--;
            } else {
                char temp = chars[L];
                chars[L++] = chars[R];
                chars[R--] = temp;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Character.isLetter(c) and isAlphabetic would not include things like periods
     * @param c
     * @return
     */
    public static boolean isVowel(char c) {
        return (c == 'a' || c == 'e' ||c == 'i' || c == 'o'||c == 'u' || c == 'A'|| c == 'E'||c == 'I' || c == 'O' || c=='U');
    }

    /**
     * The methods below are not used
     * @return
     */
    public String solveIt1() {
        char[] chars = s.toCharArray();
        for (int L = 0, R = s.length() - 1; L < R; ) {
            while (L < R && !isVowel(chars[L])) L++;
            while (R > L && !isVowel(chars[R])) R--;
            if (L < R) {
                char temp = chars[L];
                chars[L++] = chars[R];
                chars[R--] = temp;
            }
        }
        return String.valueOf(chars);
    }

    public String solveIt2() {
        StringBuilder sb = new StringBuilder(s);
        for (int L = 0, R = sb.length() - 1; L < R; ) {
            if (!isVowel(sb.charAt(L))) {
                L++;
            } else if (!isVowel(sb.charAt(R))) {
                R--;
            } else {
                char temp = sb.charAt(L);
                sb.setCharAt(L++, sb.charAt(R));
                sb.setCharAt(R--, temp);
            }
        }
        return sb.toString();
    }

    /**
     * Time: O(n^2)
     * In the end, in terms of space this solution was not better
     * @return
     */
    public String solveIt3() {
        int len = s.length();
        for (int L = 0, R = len - 1; L < R; ) {
            if (!isVowel(s.charAt(L))) {
                L++;
            } else if (!isVowel(s.charAt(R))) {
                R--;
            } else {
                char left = s.charAt(L);
                char right = s.charAt(R);
                s = s.substring(0, L) + right + s.substring(L+1, R) + left + s.substring(R+1, len);
                L++;
                R--;
            }
        }
        return s;
    }
}
