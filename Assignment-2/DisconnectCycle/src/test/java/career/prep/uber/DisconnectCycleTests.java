package career.prep.uber;

import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * JUnit 5 test class to test the {@link DisconnectCycle} class.
 */
public class DisconnectCycleTests {

    /**
     * Parameterized test method that tests whether the input singly linked list is a palindrome.
     * The method takes two parameters: the input singly linked list represented as a String, and
     * an integer indicating which node to attach to the end of the list to create a cycle. The node
     * count begins from 1 and not from 0.
     *
     * @param inputList a String representing the input singly linked list.
     * @param nodeToCreateCycle the number of the node to attach to the end of the list to create a cycle.
     */
    @ParameterizedTest
    @CsvSource({
            // 1 node

            // 2 nodes
            "1, 1",
            // no cycle
            "1->2, -1",

            // 3 nodes
            "1->2, 1",
            "1->2, 2",
            // no cycle
            "1->2->3, -1",

            // 4 nodes
            "1->2->3, 1",
            "1->2->3, 2",
            "1->2->3, 3",
            // no cycle
            "1->2->3->4, -1",

            // 5 nodes
            "1->2->3->4, 1",
            "1->2->3->4, 2",
            "1->2->3->4, 3",
            "1->2->3->4, 4",
            // no cycle
            "1->2->3->4->5, -1",

            // Samples tests
            "10->18->12->9->11->4, 3",
            "10->18->12->9->11->4, 6"
    })
    void testDisconnectCylce(String inputList, int nodeToCreateCycle) {
        // Convert the input_list string to a singly linked list
        Node<Integer> head = ListUtils.createLinkedList(inputList.split("->"), nodeToCreateCycle);

        // Convert the expected_list string to a singly linked list
        Node<Integer> expected = ListUtils.createLinkedList(inputList.split("->"), -1);

        // Call the solveIt() method of IsPalindrome class to check if the linked list is palindrome
        Node<Integer> actual = new DisconnectCycle().solveIt(head);

        // Assert that the actual result matches the expected result
        compare(expected, actual);
    }

    /**
     * Compares two singly linked lists and asserts that they have the same values in the same order.
     * If the linked lists are not the same, an assertion error is thrown.
     * @param expected the expected singly linked list to compare.
     * @param actual the actual singly linked list to compare.
     * @throws AssertionError if the linked lists are not the same.
     */
    private void compare(Node<Integer> expected, Node<Integer> actual) {
        while (expected != null) {
            assertNotNull(actual);

            assertEquals(expected.val, actual.val);

            expected = expected.next;

            actual = actual.next;
        }

        assertNull(actual);
    }
}