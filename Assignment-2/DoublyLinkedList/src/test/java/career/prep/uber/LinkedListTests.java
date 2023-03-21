package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTests extends DoublyLinkedListMethods {

    @Test
    public void insertAtFrontTest() {
        final LinkedListI<Integer> list = create();
        Node<Integer> head = null;

        // 1st Insertion
        Node<Integer> node1 = list.insertAtFront(head, 1);
        assertLength(1, node1, list);
        assertNodes(1, null, null, node1);
        head = node1;

        // 2nd Insertion
        Node<Integer> node2 = list.insertAtFront(head, 2);
        assertLength(2, node2, list);
        assertNodes(2, 1, null, node2);
        head = node2;

        // 3rd Insertion
        Node<Integer> node3 = list.insertAtFront(head, 3);
        assertLength(3, node3, list);
        assertNodes(3, 2, 1, node3);
    }

    @Test
    public void insertAtBackTest() {
        final LinkedListI<Integer> list = create();
        Node<Integer> head = new Node<>(1);

        // 1st Insertion
        // It is impossible to insert a node at the back of an empty list.
        assertLength(1, head, list);
        assertNodes(1, null, null, head);

        // 2nd Insertion
        list.insertAtBack(head, 2);
        assertLength(2, head, list);
        assertNodes(1, 2, null, head);

        // 3rd Insertion
        list.insertAtBack(head, 3);
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);
    }

    @Test
    public void insertAfterTest() {
        final LinkedListI<Integer> list = create();
        Node<Integer> head = new Node<>(1);

        // It is impossible to insert a node at the back of an empty list.

        // 1st Insertion
        assertLength(1, head, list);
        assertNodes(1, null, null, head);

        // 2nd Insertion
        list.insertAtBack(head, 3);
        assertLength(2, head, list);
        assertNodes(1, 3, null, head);

        // 3rd Insertion
        list.insertAfter(head, 2, head);
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);
    }

    @Test
    public void deleteFrontTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // 1st removal
        head = list.deleteFront(head);
        assertLength(2, head, list);
        assertNodes(2, 3, null, head);

        // 2nd removal
        head = list.deleteFront(head);
        assertLength(1, head, list);
        assertNodes(3, null, null, head);

        // 3rd removal
        head = list.deleteFront(head);
        assertLength(0, head, list);
        assertNodes(null, null, null, head);
    }

    @Test
    public void deleteBackTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // 1st removal
        list.deleteBack(head);
        assertLength(2, head, list);
        assertNodes(1, 2, null, head);

        // 2nd removal
        list.deleteBack(head);
        assertLength(1, head, list);
        assertNodes(1, null, null, head);

        // It is impossible to delete back if the list only has one node.
    }

    @Test
    public void deleteNodeTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // Remove 2nd node
        Node<Integer> secondNode = head.next;
        head = list.deleteNode(head, secondNode);
        assertLength(2, head, list);
        assertNodes(1, 3, null, head);

        // Remove 1st node
        Node<Integer> firstNode = head;
        head = list.deleteNode(head, firstNode);
        assertLength(1, head, list);
        assertNodes(3, null, null, head);

        // Remove 3rd node
        Node<Integer> thirdNode = head;
        head = list.deleteNode(head, thirdNode);
        assertLength(0, head, list);
        assertNodes(null, null, null, head);
    }

    @Test
    public void deleteNodeTest2() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // Remove 1st node
        head = list.deleteNode(head, head);
        assertLength(2, head, list);
        assertNodes(2, 3, null, head);

        // Remove 1st node
        head = list.deleteNode(head, head);
        assertLength(1, head, list);
        assertNodes(3, null, null, head);

        // Remove 1st node
        head = list.deleteNode(head, head);
        assertLength(0, head, list);
        assertNodes(null, null, null, head);
    }

    @Test
    public void deleteNodeTest3() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // Remove 3rd node
        head = list.deleteNode(head, head);
        assertLength(2, head, list);
        assertNodes(2, 3, null, head);

        // Remove 2nd node
        head = list.deleteNode(head, head);
        assertLength(1, head, list);
        assertNodes(3, null, null, head);

        // Remove 1st node
        head = list.deleteNode(head, head);
        assertLength(0, head, list);
        assertNodes(null, null, null, head);
    }

    @Test
    public void deleteHeadNodeTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // Remove 1st node
        Node<Integer> firstNode = head;
        head = list.deleteNode(head, firstNode);
        assertLength(2, head, list);
        assertNodes(2, 3, null, head);

        // Remove 2nd node
        Node<Integer> secondNode = head;
        head = list.deleteNode(head, secondNode);
        assertLength(1, head, list);
        assertNodes(3, null, null, head);

        // Remove 3rd node
        Node<Integer> thirdNode = head;
        head = list.deleteNode(head, thirdNode);
        assertLength(0, head, list);
        assertNodes(null, null, null, head);
    }

    @Test
    public void deleteNodeThatDoesNotExistsTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before removing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        // Remove 2nd node
        Node<Integer> node = new Node<>(4);
        assertThrows(IllegalArgumentException.class, () -> list.deleteNode(head, node));
    }

    @Test
    public void reverseIterativeTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before reversing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        Node<Integer> reversedHead = list.reverseIterative(head);
        assertLength(3, reversedHead, list);
        assertNodes(3, 2, 1, reversedHead);
    }

    @Test
    public void reverseIterativeSingleTest() {
        final LinkedListI<Integer> list = create();

        Node<Integer> head = new Node<>(1);

        assertLength(1, head, list);
        assertNodes(1, null, null, head);

        Node<Integer> reversedHead = list.reverseIterative(head);
        assertLength(1, reversedHead, list);
        assertNodes(1, null, null, reversedHead);
    }

    @Test
    public void reverseRecursiveTest() {
        final LinkedListI<Integer> list = create();

        // Insert 3 nodes: 1, 2, 3
        Node<Integer> head = new Node<>(1);
        list.insertAtBack(head, 2);
        list.insertAtBack(head, 3);

        // Before reversing any nodes
        assertLength(3, head, list);
        assertNodes(1, 2, 3, head);

        Node<Integer> reversedHead = list.reverseRecursive(head);
        assertLength(3, reversedHead, list);
        assertNodes(3, 2, 1, reversedHead);
    }

    /**
     * Helper Methods
     * @param expected
     * @param node
     * @param list
     */
    private void assertLength(final int expected, Node<Integer> node, final LinkedListI<Integer> list) {
        final int actual = list.length(node);
        assertEquals(expected, actual);
    }
}