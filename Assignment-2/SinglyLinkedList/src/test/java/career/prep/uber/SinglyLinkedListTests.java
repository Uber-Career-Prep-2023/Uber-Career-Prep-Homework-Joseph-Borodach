package career.prep.uber;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class SinglyLinkedListTests {

    @Test
    public void insertAtFrontTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();
        Node<Integer> head = null;

        // 1st Insertion
        Node<Integer> node1 = list.insertAtFront(head, 1);
        assertLength(1, node1, list);
        assertNodes(1, null, null, null, node1);
        head = node1;

        // 2nd Insertion
        Node<Integer> node2 = list.insertAtFront(head, 2);
        assertLength(2, node2, list);
        assertNodes(2, 1, null, null, node2);
        head = node2;

        // 3rd Insertion
        Node<Integer> node3 = list.insertAtFront(head, 3);
        assertLength(3, node3, list);
        assertNodes(3, 2, 1, null, node3);
    }

    @Test
    public void insertAtBackTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();
        Node<Integer> head = new Node<>(1);

        // 1st Insertion
        // It is impossible to insert a node at the back of an empty list.
        assertLength(1, head, list);
        assertNodes(1, null, null, null, head);

        // 2nd Insertion
        list.insertAtBack(head, 2);
        assertLength(2, head, list);
        assertNodes(1, 2, null, null, head);

        // 3rd Insertion
        list.insertAtBack(head, 3);
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);
    }

    @Test
    public void insertAfterTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();
        Node<Integer> head = new Node<>(1);

        // 1st Insertion
        // It is impossible to insert a node at the back of an empty list.
        assertLength(1, head, list);
        assertNodes(1, null, null, null, head);

        // 2nd Insertion
        list.insertAtBack(head, 3);
        assertLength(2, head, list);
        assertNodes(1, 3, null, null, head);

        // 3rd Insertion
        list.insertAfter(head, 2, head);
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);
    }

    @Test
    public void deleteFrontTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        // 1st removal
        head = list.deleteFront(head);
        assertLength(2, head, list);
        assertNodes(2, 3, null, null, head);

        // 2nd removal
        head = list.deleteFront(head);
        assertLength(1, head, list);
        assertNodes(3, null, null, null, head);

        // 3rd removal
        head = list.deleteFront(head);
        assertLength(0, head, list);
        assertNodes(null, null, null, null, head);
    }

    @Test
    public void deleteBackTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        // 1st removal
        list.deleteBack(head);
        assertLength(2, head, list);
        assertNodes(1, 2, null, null, head);

        // 2nd removal
        list.deleteBack(head);
        assertLength(1, head, list);
        assertNodes(1, null, null, null, head);

        // It is impossible to delete back if the list only has one node.
    }

    @Test
    public void deleteNodeTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        // Remove 2nd node
        Node<Integer> secondNode = head.next;
        head = list.deleteNode(head, secondNode);
        assertLength(2, head, list);
        assertNodes(1, 3, null, null, head);

        // Remove 1st node
        Node<Integer> firstNode = head;
        head = list.deleteNode(head, firstNode);
        assertLength(1, head, list);
        assertNodes(3, null, null, null, head);

        // Remove 3rd node
        Node<Integer> thirdNode = head;
        head = list.deleteNode(head, thirdNode);
        assertLength(0, head, list);
        assertNodes(null, null, null, null, head);
    }

    @Test
    public void deleteNodeThatDoesNotExistsTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        // Remove 2nd node
        Node<Integer> node = new Node<>(4);
        assertThrows(IllegalArgumentException.class, () -> list.deleteNode(head, node));
    }

    @Test
    public void reverseIterativeTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        Node<Integer> reversedHead = list.reverseIterative(head);
        assertLength(3, reversedHead, list);
        assertNodes(3, 2, 1, null, reversedHead);
    }

    @Test
    public void reverseRecursiveTest() {
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, null, head);

        Node<Integer> reversedHead = list.reverseRecursive(head);
        assertLength(3, reversedHead, list);
        assertNodes(3, 2, 1, null, reversedHead);
    }

    /**
     * Helper Methods
     * @param expected1
     * @param expected2
     * @param expected3
     * @param expected4
     * @param head
     */
    private void assertNodes(final Integer expected1, final Integer expected2, final Integer expected3, final Integer expected4, final Node<Integer> head) {
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
            assertValue(expected4, head.next.next.next);
        }
    }

    /**
     * @param expected
     * @param node
     * @param list
     */
    private void assertLength(final int expected, Node<Integer> node, final SinglyLinkedListI<Integer> list) {
        final int actual = list.length(node);
        assertEquals(expected, actual);
    }

    /**
     * @param expected
     * @param node
     */
    private void assertValue(final Integer expected, Node<Integer> node) {
        if (expected == null) {
            assertNull(node);
        } else {
            assertNotNull(node);
            final Integer actual = node.val;
            assertEquals(expected, actual);
        }
    }
}