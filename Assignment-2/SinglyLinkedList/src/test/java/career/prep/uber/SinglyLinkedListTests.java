package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;
public class SinglyLinkedListTests {

    /**
     * For simplicity, my tests assumed no duplicate nodes.
     * Change tests to be more academic.
     */
    @Test
    @DisplayName("insertAtFront")
    public void t1() {
        final int N = 10;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();
        Node<Integer> head = null;
        for (int i = N; i >= 1; i--) {
            Node<Integer> node = list.insertAtFront(head, i);
            int actual = node.val;
            assertEquals(i, actual);
            assertEquals((N - i) + 1, list.length(node));
            head = node;
        }
        assertOrder(1, N, head);
    }

    @Test
    @DisplayName("insertAtBack")
    public void t2() {
        final int N = 10;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(1);
        assertEquals(1, list.length(head));

        for (int i = 2; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
            assertOrder(1, i, head);
        }
    }

    @Test
    @DisplayName("insertAfter")
    public void t3() {
        final int N = 15;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(1);
        assertEquals(1, list.length(head));

        int third = N/3;
        for (int i = 2; i <= third; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }

        int twoThirds = third * 2;
        for (int i = twoThirds + 1; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i - third, list.length(head));
        }

        int count = twoThirds;

        for (int i = third + 1; i <= twoThirds; i++) {
            list.insertAfter(head, i, new Node<>(i-1));
            assertEquals(++count, list.length(head));
        }
        assertOrder(1, N, head);
    }

    @Test
    @DisplayName("deleteFront")
    public void t4() {
        final int N = 10;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(1);

        for (int i = 2; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }
        for (int i = 1; i <= N; i++) {
            head = list.deleteFront(head);
            assertEquals(N - i, list.length(head));
            assertOrder(i+1, N, head);
        }
        // assertEquals(1, list.length(head));
    }

    @Test
    @DisplayName("deleteFront")
    public void t5() {
        final int S = 1;
        final int N = 15;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(S);

        for (int i = S + 1; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }

        assertOrder(S, N, head);

        for (int i = N - 1; i >= 1; i--) {
            list.deleteBack(head);
            assertEquals(i, list.length(head));
            assertOrder(S, i, head);
        }
    }

    @Test
    @DisplayName("deleteNode")
    public void t6() {
        final int S = 1;
        final int N = 15;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(S);

        for (int i = S + 1; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }

        assertOrder(S, N, head);

        int count = N;
        int startAt = S + 3;
        for (int i = startAt; i < N; i++) {
            list.deleteNode(head, new Node<>(i));
            assertEquals(--count, list.length(head));
            assertOrder(S, startAt - 1, i + 1, N, head);
        }
    }

    @Test
    @DisplayName("reverseIterative")
    public void t7() {
        final int S = 1;
        final int N = 15;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(S);

        for (int i = S + 1; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }

        assertOrder(S, N, head);

        head = list.reverseIterative(head);
        assertDescendingOrder(S, N, head);
    }

    @Test
    @DisplayName("reverseRecursive")
    public void t8() {
        final int S = 1;
        final int N = 15;
        final SinglyLinkedListI<Integer> list = new SinglyLinkedList<>();

        Node<Integer> head = new Node<>(S);

        for (int i = S + 1; i <= N; i++) {
            list.insertAtBack(head, i);
            assertEquals(i, list.length(head));
        }

        assertOrder(S, N, head);

        head = list.reverseRecursive(head);
        assertDescendingOrder(S, N, head);
    }

    private void assertOrder(int S, int M1, int M2, int N, Node head) {
        Node curr = head;
        for (int i = S; i <= M1; i++) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            curr = curr.getNext();
        }
        for (int i = M2; i <= N; i++) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            curr = curr.getNext();
        }
        System.out.println();
    }

    private void assertOrder(int S, int N, Node head) {
        Node curr = head;
        for (int i = S; i <= N; i++) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            curr = curr.getNext();
        }
        System.out.println();
    }

    private void assertDescendingOrder(int S, int N, Node head) {
        Node curr = head;
        for (int i = N; i >= S; i--) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            curr = curr.getNext();
        }
        System.out.println();
    }


    private void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.getNext();
        }
        System.out.println();
    }
}
