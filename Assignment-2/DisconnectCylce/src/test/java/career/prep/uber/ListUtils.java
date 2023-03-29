package career.prep.uber;

public class ListUtils {
    /**
     * Creates a linked list from an array of strings.
     * @param arr the array of strings to create the linked list from.
     * @return the head of the linked list.
     */
    public static Node<Integer> createLinkedList(String[] arr, int node_to_create_cycle) {
        int len = arr.length;
        if (len == 0) {
            return null;
        }

        Node<Integer> head = new Node<>(Integer.parseInt(arr[0]));
        Node<?> cycle = null;
        Node<?> curr = head;

        for (int i = 1; i < len; i++) {

            if (i == node_to_create_cycle) {
                cycle = curr;
            }

            Node<Integer> next = new Node<>(Integer.parseInt(arr[i]));
            curr.next = next;
            curr = next;

        }

        if (len == node_to_create_cycle) {
            cycle = curr;
        }

        curr.next = cycle;

        // print(head);
        return head;
    }

    /**
     *
     * @param head
     */
    private static void print(Node<Integer> head) {
        Node<?> curr = head;

        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }

        System.out.println();
    }
}
