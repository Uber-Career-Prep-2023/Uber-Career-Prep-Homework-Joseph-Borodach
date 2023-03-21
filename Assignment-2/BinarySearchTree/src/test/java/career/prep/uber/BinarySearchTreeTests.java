package career.prep.uber;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
public class BinarySearchTreeTests {

    private BinarySearchTreeI bst;

    public void setup() {
        bst = new BinarySearchTree();
    }

    @Test
    public void insertionTest() {
        setup();

        bst.insert(2);
        bst.insert(1);
        bst.insert(3);

        Node root = bst.getRoot();

        assertNotNull(root);
        assertEquals(2, root.val);

        assertNotNull(root.left);
        assertEquals(1, root.left.val);

        assertNotNull(root.right);
        assertEquals(3, root.right.val);
    }

    @Test
    public void minTest() {
        setup();

        insert(3, 2, 1, 4, 5);

        assertEquals(1, bst.min());
    }

    @Test
    public void insertMinFirstTest() {
        setup();

        insert(1, 2, 3, 4, 5);

        assertEquals(1, bst.min());
    }

    @Test
    public void negativeMinTest2() {
        setup();

        insert(3, -45, 1, 4, 5);

        assertEquals(-45, bst.min());
    }

    @Test
    public void maxTest() {
        setup();

        insert(3, 2, 1, 4, 5);

        assertEquals(5, bst.max());
    }

    @Test
    public void insertMaxFirstTest() {
        setup();

        insert(5, 1, 2, 3, 4);

        assertEquals(5, bst.max());
    }

    @Test
    public void negativeMaxTest2() {
        setup();

        insert(-3, -45, -1, -4, -5);

        assertEquals(-1, bst.max());
    }

    @Test
    public void containsTest() {
        setup();

        insert(3, 2, 1, 4, 5);

        contains(true, true, true, true, true);

        assertFalse(bst.contains(-1));
        assertFalse(bst.contains(0));
        assertFalse(bst.contains(6));
        assertFalse(bst.contains(7));
    }

    @Test
    public void containsNegativesTest() {
        setup();

        insert(-1, 2, 1, -4, 5);

        contains(-1, 2, 1, -4, 5);
    }

    @Test
    public void deleteTest() {
        setup();

        insert(3, 2, 1, 4, 5);
        contains(true, true, true, true, true);

        assertEquals(3, bst.delete(3));
        contains(true, true, false, true, true);

        assertEquals(2, bst.delete(2));
        contains(true, false, false, true, true);

        assertEquals(4, bst.delete(4));
        contains(true, false, false, false, true);

        assertEquals(1, bst.delete(1));
        contains(false, false, false, false, true);

        assertEquals(5, bst.delete(5));
        contains(false, false, false, false, false);

        assertTrue(bst.isEmpty());
    }

    @Test
    public void completeTest1() {
        setup();

        insert(3, 2, 1, 4, 5);
        contains(true, true, true, true, true);
        assertMinAndMax(1, 5);

        assertEquals(3, bst.delete(3));
        contains(true, true, false, true, true);
        assertMinAndMax(1, 5);

        assertEquals(2, bst.delete(2));
        contains(true, false, false, true, true);
        assertMinAndMax(1, 5);

        assertEquals(4, bst.delete(4));
        contains(true, false, false, false, true);
        assertMinAndMax(1, 5);

        assertEquals(1, bst.delete(1));
        contains(false, false, false, false, true);
        assertMinAndMax(5, 5);

        assertEquals(5, bst.delete(5));
        assertTrue(bst.isEmpty());
    }

    @Test
    public void completeTest2() {
        setup();

        insert(3, 2, 1, 4, 5);
        contains(true, true, true, true, true);
        assertMinAndMax(1, 5);

        assertEquals(5, bst.delete(5));
        contains(true, true, true, true, false);
        assertMinAndMax(1, 4);

        assertEquals(1, bst.delete(1));
        contains(false, true, true, true, false);
        assertMinAndMax(2, 4);

        assertEquals(4, bst.delete(4));
        contains(false, true, true, false, false);
        assertMinAndMax(2, 3);

        assertEquals(2, bst.delete(2));
        contains(false, false, true, false, false);
        assertMinAndMax(3, 3);

        assertEquals(3, bst.delete(3));
        assertTrue(bst.isEmpty());
    }

    @Test
    public void deleteRootReplaceRight() {
        setup();

        bst.insert(1);
        bst.insert(3);

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(3));
    }

    @Test
    @DisplayName("delete 3")
    public void deleteRootReplaceLeft() {
        setup();

        bst.insert(1);
        bst.insert(2);

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(2));
    }

    /**
     * Helper Methods
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     * @param n5
     */
    public void insert(int n1, int n2, int n3, int n4, int n5) {
        bst.insert(n1);
        bst.insert(n2);
        bst.insert(n3);
        bst.insert(n4);
        bst.insert(n5);
    }

    /**
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     * @param n5
     */
    public void contains(int n1, int n2, int n3, int n4, int n5) {
        assertTrue(bst.contains(n1));
        assertTrue(bst.contains(n2));
        assertTrue(bst.contains(n3));
        assertTrue(bst.contains(n4));
        assertTrue(bst.contains(n5));
    }

    /**
     * Checks to see if the tree contains 1-5
     * @param expected1
     * @param expected2
     * @param expected3
     * @param expected4
     * @param expected5
     */
    public void contains(boolean expected1, boolean expected2, boolean expected3, boolean expected4, boolean expected5) {
        assertEquals(expected1, bst.contains(1));
        assertEquals(expected2, bst.contains(2));
        assertEquals(expected3, bst.contains(3));
        assertEquals(expected4, bst.contains(4));
        assertEquals(expected5, bst.contains(5));
    }

    /**
     * @param expectedMin
     * @param expectedMax
     */
    private void assertMinAndMax(int expectedMin, int expectedMax) {
        assertEquals(expectedMin, bst.min());
        assertEquals(expectedMax, bst.max());
    }
}