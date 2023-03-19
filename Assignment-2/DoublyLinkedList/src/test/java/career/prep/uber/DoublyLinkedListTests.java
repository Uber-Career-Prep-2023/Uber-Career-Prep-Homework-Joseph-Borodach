package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;
public class DoublyLinkedListTests {
    @Test
    @DisplayName("insertAtFront")
    public void t1() {
        int S = 1;
        int N = 15;
        DoublyLinkedListI list = new DoublyLinkedList();
        Node<Integer> head = null;
        Node<Integer> curr = null;
        for (int i = S; i <= N; i++) {
            curr = list.insertAtFront(curr, i);
            if (i == S) head = curr;
            Node<Integer> last = prevAscending(S, i, head);
            nextDescending(S, i, last);
        }

    }

    @Test
    @DisplayName("insertAtBack")
    public void t2() {
        int S = 1;
        int N = 15;
        DoublyLinkedListI list = new DoublyLinkedList();
        Node<Integer> head = null;
        Node<Integer> curr = null;
        for (int i = N; i >= S; i--) {
            curr = list.insertAtFront(curr, i);
            if (i == N) head = curr;
            Node<Integer> last = prevDescending(i, N, head);
            nextAscending(i, N, last);
        }

    }

    private Node<Integer> prevAscending(int S, int N, Node head) {
        Node curr = head;
        for (int i = S; i <= N; i++) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            if (i != N) curr = curr.prev;
        }
        System.out.println();
        return curr;
    }

    private Node<Integer> nextAscending(int S, int N, Node head) {
        Node curr = head;
        for (int i = S; i <= N; i++) {
            System.out.println(curr.val);
            assertEquals(i, curr.val);
            if (i != N) curr = curr.next;
        }
        System.out.println();
        return curr;
    }

    private Node<Integer> prevDescending(int S, int N, Node head) {
        Node<Integer> curr = head;
        for (int i = N; i >= S; i--) {
            System.out.println(curr.val);
            assertEquals(i, (int) curr.val);
            if (i != S) curr = curr.prev;
        }
        System.out.println();
        return curr;
    }

    private Node<Integer> nextDescending(int S, int N, Node head) {
        Node<Integer> curr = head;
        for (int i = N; i >= S; i--) {
            System.out.println(curr.val);
            assertEquals(i, (int) curr.val);
            if (i != S) curr = curr.next;
        }
        System.out.println();
        return curr;
    }
    /*
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

    private void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.getNext();
        }
        System.out.println();
    }
    */
}
