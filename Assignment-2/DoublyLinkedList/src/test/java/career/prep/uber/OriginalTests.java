package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class OriginalTests {
    @Test
    @DisplayName("insertAtFront")
    public void t1() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 3);
        head = L.insertAtFront(head, 2);
        head = L.insertAtFront(head, 1);

        assertBothDirections(L, head);
    }

    @Test
    @DisplayName("insertAtBack")
    public void t2() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 2);
        L.insertAtBack(head, 3);

        assertBothDirections(L, head);
    }

    @Test
    @DisplayName("insertAfter")
    public void t3() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 3);
        L.insertAfter(head, 2, head);

        assertBothDirections(L, head);
    }

    @Test
    @DisplayName("deleteFront")
    public void t4() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> n1 = L.insertAtFront(null, 1);
        L.insertAtBack(n1, 2);
        L.insertAtBack(n1, 3);

        assertBothDirections(L, n1);

        Node<Integer> n2 = n1.next;
        assertEquals(n2, L.deleteFront(n1));
        assertEquals(2, L.length(n2));
        assertEquals(2, (int) n2.val);
        assertNull(n2.prev);
        assertNotNull(n2.next);

        Node<Integer> n3 = n2.next;
        assertEquals(n3, L.deleteFront(n2));
        assertEquals(1, L.length(n3));
        assertEquals(3, (int) n3.val);
        assertNull(n3.prev);
        assertNull(n3.next);
    }

    @Test
    @DisplayName("deleteBack")
    public void t5() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        // Part 1: Create list with 3 nodes, make necessary assertions
        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 2);
        L.insertAtBack(head, 3);

        assertBothDirections(L, head);

        // Part 2: Remove the 3rd node
        L.deleteBack(head);

        assertEquals(2, L.length(head));
        assertEquals(1, (int) head.val);
        assertNull(head.prev);
        assertNotNull(head.next);
        assertEquals(2, (int) head.next.val);
        assertNotNull(head.next.prev);
        assertEquals(1, (int) head.next.prev.val);
        assertNull(head.next.next);

        // Part 3: Remove the 2nd node
        L.deleteBack(head);

        assertEquals(1, L.length(head));
        assertEquals(1, (int) head.val);
        assertNull(head.prev);
        assertNull(head.next);
    }

    @Test
    @DisplayName("deleteNode")
    public void t6() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 2);
        L.insertAtBack(head, 3);

        assertBothDirections(L, head);

        // remove second node
        head = L.deleteNode(head, head.next);

        assertEquals(2, L.length(head));
        assertEquals(1, (int) head.val);
        assertNull(head.prev);
        assertNotNull(head.next);
        assertEquals(3, (int) head.next.val);
        assertNotNull(head.next.prev);
        assertEquals(1, (int) head.next.prev.val);
        assertNull(head.next.next);

        // remove the 3rd node
        head = L.deleteNode(head, head.next);

        assertEquals(1, L.length(head));
        assertEquals(1, (int) head.val);
        assertNull(head.prev);
        assertNull(head.next);
    }

    @Test
    @DisplayName("length")
    public void t7() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        assertEquals(1, L.length(head));

        L.insertAtBack(head, 2);
        assertEquals(2, L.length(head));

        L.insertAtBack(head, 3);
        assertEquals(3, L.length(head));

        L.deleteBack(head);
        assertEquals(2, L.length(head));

        head = L.deleteFront(head);
        assertEquals(1, L.length(head));

        head = L.deleteFront(head);
        assertEquals(0, L.length(head));
    }

    @Test
    @DisplayName("reverseIterative")
    public void t8() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 2);
        L.insertAtBack(head, 3);

        assertBothDirections(L, head);

        head = L.reverseIterative(head);

        assertReverseDirections(L, head);
    }

    @Test
    @DisplayName("reverseRecursive")
    public void t9() {
        DoublyLinkedListI<Integer> L = new DoublyLinkedList<>();

        Node<Integer> head = L.insertAtFront(null, 1);
        L.insertAtBack(head, 2);
        L.insertAtBack(head, 3);

        assertBothDirections(L, head);

        head = L.reverseRecursive(head);

        assertReverseDirections(L, head);
    }

    private void assertBothDirections(DoublyLinkedListI<Integer> L, Node<Integer> head) {
        assertEquals(3, L.length(head));
        assertEquals(1, (int) head.val);
        assertNull(head.prev);
        assertNotNull(head.next);
        assertEquals(2, (int) head.next.val);
        assertNotNull(head.next.prev);
        assertEquals(1, (int) head.next.prev.val);
        assertNotNull(head.next.next);
        assertEquals(3, (int) head.next.next.val);
        assertNotNull(head.next.next.prev);
        assertEquals(2, (int) head.next.next.prev.val);
        assertNotNull(head.next.next.prev.prev);
        assertEquals(1, (int) head.next.next.prev.prev.val);
        assertNull(head.next.next.next);
    }

    private void assertReverseDirections(DoublyLinkedListI<Integer> L, Node<Integer> head) {
        assertEquals(3, L.length(head));
        assertEquals(3, (int) head.val);
        assertNull(head.prev);
        assertNotNull(head.next);
        assertEquals(2, (int) head.next.val);
        assertNotNull(head.next.prev);
        assertEquals(3, (int) head.next.prev.val);
        assertNotNull(head.next.next);
        assertEquals(1, (int) head.next.next.val);
        assertNotNull(head.next.next.prev);
        assertEquals(2, (int) head.next.next.prev.val);
        assertNotNull(head.next.next.prev.prev);
        assertEquals(3, (int) head.next.next.prev.prev.val);
        assertNull(head.next.next.next);
    }
}