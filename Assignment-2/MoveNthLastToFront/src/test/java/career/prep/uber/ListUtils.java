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
            curr.next = next;
            curr = next;
        }
        return head;
    }

    /**
     * Converts a linked list to a string in the format "1 -> 2 -> 3 -> null".
     * @param head the head of the linked list to convert.
     * @return the string representation of the linked list.
     */
    public static String toString(Node<?> head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" -> ");
            head = head.next;
        }
        sb.append("null");
        return sb.toString();
    }

    /**
     * Calculates the length of a linked list.
     *
     * @param head the head of the linked list.
     * @return the length of the linked list.
     */
    public static int getLength(Node<Integer> head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
