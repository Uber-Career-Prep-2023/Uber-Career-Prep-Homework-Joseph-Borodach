package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class BinarySearchTreeTests {
    @Test
    @DisplayName("Insertions")
    public void t1() {
        BinarySearchTreeI bst = new BinarySearchTree();
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
    @DisplayName("min")
    public void t2() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);
        assertEquals(1, bst.min());
    }

    @Test
    @DisplayName("contains")
    public void t3() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);

        assertTrue(bst.contains(5));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(1));

        assertFalse(bst.contains(-1));
        assertFalse(bst.contains(0));
        assertFalse(bst.contains(6));
        assertFalse(bst.contains(7));
    }

    @Test
    @DisplayName("delete")
    public void t4() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);

        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));

        assertEquals(3, bst.delete(3));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertFalse(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));

        assertEquals(2, bst.delete(2));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));

        assertEquals(4, bst.delete(4));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(4));
        assertTrue(bst.contains(5));

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(4));
        assertTrue(bst.contains(5));

        assertEquals(5, bst.delete(5));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(4));
        assertFalse(bst.contains(5));
    }

    @Test
    @DisplayName("All 1")
    public void t5() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);

        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(5, bst.max());

        assertEquals(3, bst.delete(3));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertFalse(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(5, bst.max());

        assertEquals(2, bst.delete(2));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(5, bst.max());

        assertEquals(4, bst.delete(4));
        assertTrue(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(5, bst.max());

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(3));
        assertFalse(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(5, bst.min());
        assertEquals(5, bst.max());

        assertEquals(5, bst.delete(5));
        assertTrue(bst.isEmpty());
    }

    @Test
    @DisplayName("All 2")
    public void t6() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);

        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(5, bst.max());

        assertEquals(5, bst.delete(5));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertFalse(bst.contains(5));
        assertEquals(1, bst.min());
        assertEquals(4, bst.max());

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(4));
        assertFalse(bst.contains(5));
        assertEquals(2, bst.min());
        assertEquals(4, bst.max());

        assertEquals(4, bst.delete(4));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(3));
        assertFalse(bst.contains(4));
        assertFalse(bst.contains(5));
        assertEquals(2, bst.min());
        assertEquals(3, bst.max());

        assertEquals(2, bst.delete(2));
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(2));
        assertTrue(bst.contains(3));
        assertFalse(bst.contains(4));
        assertFalse(bst.contains(5));
        assertEquals(3, bst.min());
        assertEquals(3, bst.max());

        assertEquals(3, bst.delete(3));
        assertTrue(bst.isEmpty());
    }

    @Test
    @DisplayName("delete 2")
    public void t7() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(1);
        bst.insert(3);

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(3));
    }

    @Test
    @DisplayName("delete 3")
    public void t8() {
        BinarySearchTreeI bst = new BinarySearchTree();
        bst.insert(1);
        bst.insert(2);

        assertEquals(1, bst.delete(1));
        assertFalse(bst.contains(1));
        assertTrue(bst.contains(2));
    }
}