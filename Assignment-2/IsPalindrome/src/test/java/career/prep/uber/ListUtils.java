package career.prep.uber;

public class ListUtils {
    /**
     * Creates a linked list from an array of strings.
     * @param arr the array of strings to create the linked list from.
     * @return the head of the linked list.
     */
    public static Node<Integer> createLinkedList(String[] arr) {
        if (arr.length == 0) {
            return null;
        }
        Node<Integer> head = new Node<>(Integer.parseInt(arr[0]));
        Node<?> curr = head;
        for (int i = 1; i < arr.length; i++) {
            Node<Integer> next = new Node<>(Integer.parseInt(arr[i]));
            next.prev = curr;
            curr.next = next;
            curr = next;
        }
        print(head);
        return head;
    }

    private static void print(Node<Integer> head) {
        Node<?> prev = null;
        Node<?> curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            prev = curr;
            curr = curr.next;
        }

        System.out.println();

        curr = prev;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.prev;
        }

        System.out.println();
    }
}
