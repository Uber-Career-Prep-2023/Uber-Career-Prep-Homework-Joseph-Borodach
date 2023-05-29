package career.prep.uber;

/**
 * @author josephborodach
 *
 * Question #9: Given an array of k sorted arrays, merge the k arrays into a single sorted array.
 *
 * Approaches:
 * 1. Brute Force: Add all numbers to one array and hit sort.
 *      Time: O(n log k), similar to linearithmic.
 *      Space: O(n log k), similar to linearithmic.
 *      - Note: IMO, even though this is just about optimal time & space complexity, it is likely not what an interview would want to see implemented.
 * 2. Merge Arrays k-1 times.
 *      Time: O(n * k), similar to quadratic.
 *      Space: O(n * k), similar to quadratic.
 * 3. Min-heap.
 *      Time: O(n log k), similar to linearithmic.
 *      Space: O(n log k), similar to linearithmic.
 * 4. Divide & Conquer.
 *      Sequentely, merge the subarray, each iteration cutting the number of remaining arrays in half, k/2.
 *      Time: O(n log k), similar to linearithmic.
 *      Space: O(n log k), similar to linearithmic.
 *      - I implemented this one because it seemed the most fun.
 *      - It sort of the like the bubbling up in Mergesort.
 *
 * Note: I intentionally left out the argument for k.
 *
 * Did NOT Assume:
 *      - That array or all sub arrays were NOT null.
 *      - That array or all sub arrays were NOT empty.
 *
 * Assumptions:
 *      - Assumed that arrays were sorted as specified.
 *
 */
public class MergeKSortedArrays {

    public int[] solveIt(int[][] arrays) {
        if (arrays == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arrays.length < 1) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        while (arrays.length > 1) {
            final int len = arrays.length;
            final boolean oddLength = len % 2 != 0;

            final int consolidateLength = oddLength ? len / 2 + 1 : len / 2;
            int[][] consolidatedArray = new int[consolidateLength][];

            int idx1 = 0;
            int idx2 = 0;
            while(idx2 < len - 1) {
                consolidatedArray[idx1] = merge(arrays[idx2], arrays[idx2 + 1]);
                idx1++;
                idx2 += 2;
            }
            if (len > 1 && oddLength) {
                consolidatedArray[idx1] = arrays[idx2];
            }
            arrays = consolidatedArray;
        }
        return arrays[0];
    }

    private int[] merge(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("Arrays cannot be null");
        }
        int len1 = array1.length;
        int len2 = array2.length;
        if (len1 < 1 || len2 < 1) {
            throw new IllegalArgumentException("Arrays cannot be empty");
        }

        int i1 = 0;
        int i2 = 0;
        int idx = 0;
        int[] mergedArray = new int[len1 + len2];
        while (i1 < len1 && i2 < len2) {
            if (array1[i1] <= array2[i2]) {
                mergedArray[idx] = array1[i1];
                i1++;
            } else {
                mergedArray[idx] = array2[i2];
                i2++;
            }
            idx++;
        }
        while(i1 < len1) {
            mergedArray[idx] = array1[i1];
            i1++;
            idx++;
        }
        while(i2 < len2) {
            mergedArray[idx] = array2[i2];
            i2++;
            idx++;
        }
        return mergedArray;
    }
}