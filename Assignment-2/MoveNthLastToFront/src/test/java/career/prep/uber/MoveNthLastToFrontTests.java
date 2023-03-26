package career.prep.uber;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.params.*;
import org.junit.runners.Parameterized;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * This class contains JUnit test cases for the `MoveNthLastToFrontTests` class.
 */
public class MoveNthLastToFrontTests {

    @ParameterizedTest
    @CsvSource({
            "1, 1, '1 -> null'",
            "1->2, 1, '2 -> 1 -> null'",
            "1->2, 2, '1 -> 2 -> null'",
            "1->2->3, 1, '3 -> 1 -> 2 -> null'",
            "1->2->3, 2, '2 -> 1 -> 3 -> null'",
            "1->2->3, 3, '1 -> 2 -> 3 -> null'",
            "1->2->3->4, 1, '4 -> 1 -> 2 -> 3 -> null'",
            "1->2->3->4, 2, '3 -> 1 -> 2 -> 4 -> null'",
            "1->2->3->4, 3, '2 -> 1 -> 3 -> 4 -> null'",
            "1->2->3->4, 4, '1 -> 2 -> 3 -> 4 -> null'",
            "1->2->3->4->5, 1, '5 -> 1 -> 2 -> 3 -> 4 -> null'",
            "1->2->3->4->5, 2, '4 -> 1 -> 2 -> 3 -> 5 -> null'",
            "1->2->3->4->5, 3, '3 -> 1 -> 2 -> 4 -> 5 -> null'",
            "1->2->3->4->5, 4, '2 -> 1 -> 3 -> 4 -> 5 -> null'",
            "1->2->3->4->5, 5, '1 -> 2 -> 3 -> 4 -> 5 -> null'",

            "1->-1->3, 2, '-1 -> 1 -> 3 -> null'",
            "15->2->8->7->20->9->11->6->19, 2, '6 -> 15 -> 2 -> 8 -> 7 -> 20 -> 9 -> 11 -> 19 -> null'",
            "15->2->8->7->20->9->11->6->19, 7, '8 -> 15 -> 2 -> 7 -> 20 -> 9 -> 11 -> 6 -> 19 -> null'",

            // invalid inputs (expect IllegalArgumentException)
            // k is zero
            "1, 0, 'null'",
            // k is negative
            "1, -1, 'null'",
            // k is too large
            "1, 2, 'null'"
    })
    void testMoveNthLastToFront(String inputList, int k, String expected) {
        Node<Integer> head = ListUtils.createLinkedList(inputList.split("->"));

        if (expected.equals("null")) {
            assertThrows(IllegalArgumentException.class, () -> new MoveNthLastToFront().solveIt(head, k));
        }
        else {
            Node<?> root = new MoveNthLastToFront().solveIt(head, k);

            String actual = ListUtils.toString(root);

            assertEquals(expected, actual);
        }
    }

    /**
     * Test that the program works for another data type than Integers.
     * There are many more factors that should be checked for in the program.
     */
    @Test
    public void charTest() {
        final LinkedListI<Character> list = new SinglyLinkedList<>();

        Node<Character> root = new Node<>('a');
        list.insertAtBack(root, 'b');
        list.insertAtBack(root, 'c');

        Node<Character> expected = new Node<>('b');
        list.insertAtBack(expected, 'a');
        list.insertAtBack(expected, 'c');

        final int k = 2;

        compare(root, k, expected);
    }

    /**
     * Tests the `solveIt()` method when the input list contains many values.
     */
    @Test
    public void largeTest() {
        final LinkedListI<Integer> list = new SinglyLinkedList<>();

        int value = Integer.MIN_VALUE;

        Node<Integer> root = new Node<>(value);

        Node<Integer> expected = new Node<>(value);

        final int iterations = getRandom(1, 10_000);

        final int k = getRandom(1, iterations);

        System.out.println("k: " + k);

        for (int i = iterations; i > 0; i--) {
            value = getRandom(Integer.MIN_VALUE, Integer.MAX_VALUE);

            list.insertAtBack(root, value);

            if (i == k) {
                expected = list.insertAtFront(expected, value);
            } else {
                list.insertAtBack(expected, value);
            }
        }

        compare(root, k, expected);
    }

    /**
     * @param min
     * @param max
     * @return
     */
    private int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Compares the output of the solveIt() method for the given input to an expected output.
     * @param root the root node of the input list.
     * @param expected the expected output list.
     */
    private void compare(final Node<?> root, final int k, Node<?> expected) {
        Node<?> actual = new MoveNthLastToFront().solveIt(root, k);

        while (expected != null) {
            assertNotNull(actual);

            // System.out.println(actual.val);

            // assertEquals(expected, actual);
            assertEquals(expected.val, actual.val);

            actual = actual.next;
            expected = expected.next;
        }

        assertNull(actual);
    }
}
