package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
public class DoublyLinkedListTests {

    /**
     * For simplicity, my tests assumed no duplicate nodes.
     */
    @Test
    @DisplayName("insertAtFront")
    public void t1() {

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
    */
}
