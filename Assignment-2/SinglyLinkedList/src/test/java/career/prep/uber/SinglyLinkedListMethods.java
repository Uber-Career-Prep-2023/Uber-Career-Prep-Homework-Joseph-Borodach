package career.prep.uber;

import static org.junit.Assert.*;

public abstract class SinglyLinkedListMethods {

    public LinkedListI<Integer> create() {
        return new SinglyLinkedList<>();
    }

    /**
     * @param expected1
     * @param expected2
     * @param expected3
     * @param head
     */
    public void assertNodes(final Integer expected1, final Integer expected2, final Integer expected3, final Node<Integer> head) {
        // 1st Node
        assertValue(expected1, head);
        // 2nd Node
        if (expected1 != null) {
            assertValue(expected2, head.next);
        }
        // 3rd Node
        if (expected2 != null) {
            assertValue(expected3, head.next.next);
        }
        // 4th Node
        if (expected3 != null) {
            assertValue(null, head.next.next.next);
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
