package career.prep.uber;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * JUnit 5 test class to test the {@link LeftView} class.
 */
public class LeftViewTests {

    private BinarySearchTreeI bst;

    public void setup() {
        bst = new BinarySearchTree();
    }

    @Test
    public void singleNodeTest() {
        setup();

        bst.insert(2);

        final int[] expected = new int[]{2};

        compare(expected);
    }

    @Test
    public void rightChildIsLeftMostTest() {
        setup();

        bst.insert(2);
        bst.insert(3);

        final int[] expected = new int[]{2, 3};

        compare(expected);
    }

    @Test
    public void threeNodeTest() {
        setup();

        bst.insert(2);
        bst.insert(1);
        bst.insert(3);

        final int[] expected = new int[]{2, 1};

        compare(expected);
    }

    @Test
    public void fiveNodeTest() {
        setup();

        bst.insert(3);
        bst.insert(2);
        bst.insert(4);
        bst.insert(1);
        bst.insert(5);

        final int[] expected = new int[]{3, 2, 1};

        compare(expected);
    }

    private void compare(int[] expected) {
        Node root = bst.getRoot();

        final int[] actual = new LeftView().solveIt(root);

        assertNotNull(actual);

        assertEquals(expected.length, actual.length);

        int n = expected.length;

        for (int i = 0; i < n; i++) {

            assertEquals(expected[i], actual[i]);

        }
    }

}









