package career.prep.uber;

import static org.junit.Assert.*;

public abstract class DoublyLinkedListMethods {

    public LinkedListI<Integer> create() {
        return new DoublyLinkedList<>();
    }

    /**
     * Helper Methods
     * @param expected1
     * @param expected2
     * @param expected3
     * @param head
     */
    public void assertNodes(final Integer expected1, final Integer expected2, final Integer expected3, final Node<Integer> head) {
        // 1st Node
        assertNode(null, expected1, expected2, head);
        // 2nd Node
        if (expected1 != null) {
            assertNode(expected1, expected2, expected3, head.next);
        }
        // 3rd Node
        if (expected2 != null) {
            assertNode(expected2, expected3, null, head.next.next);
        }
        // 4th Node
        if (expected3 != null) {
            assertNode(expected3, null, null, head.next.next.next);
        }
    }

    /**
     * @param expected_val
     * @param expected_prev
     * @param expected_next
     * @param node
     */
    public void assertNode(Integer expected_prev, Integer expected_val, Integer expected_next, Node<Integer> node) {
        assertValue(expected_val, node);
        if (expected_val != null) {
            assertValue(expected_prev, node.prev);
            assertValue(expected_next, node.next);
        }
    }

    /**
     * @param expected
     * @param node
     */
    public void assertValue(final Integer expected, Node<Integer> node) {
        if (expected == null) {
            assertNull(node);
        } else {
            assertNotNull(node);
            final Integer actual = node.val;
            assertEquals(expected, actual);
        }
    }
}