package career.prep.uber;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *  * JUnit 5 test class to test the {@link IsPalindrome} class.
 */
public class IsPalindromeTests {

    /**
     * JUnit 5 parameterized test method that tests whether the input singly linked list is a palindrome.
     *
     * @param inputList a String representing the input singly linked list
     * @param expected a boolean value indicating whether the input singly linked list is a palindrome
     */
    @ParameterizedTest
    @CsvSource({
            // single node
            "1, true",
            // two nodes
            "1->2, false",
            // three nodes
            "1->2->1, true",
            "1->2->3, false",

            // longer lengths
            "1->2->2->1, true",
            "1->2->3->2->1, true",
            "1->1->3->2->1, false",
            "1->2->3->1->1, false",
            "1->2->3->3->2->1, true",
            "1->2->3->1->2->1, false",
            "1->2->3->4->3->2->1, true",
            "1->2->3->4->2->2->1, false",

            // Samples tests
            "9->2->4->2->9, true",
            "9->12->4->2->9, false"
    })
    void testIsPalindrome(String inputList, boolean expected) {
        // Convert the inputList string to a singly linked list
        Node<Integer> head = ListUtils.createLinkedList(inputList.split("->"));

        // Call the solveIt() method of IsPalindrome class to check if the linked list is palindrome
        boolean actual = new IsPalindrome().solveIt(head);

        // Assert that the actual result matches the expected result
        assertEquals(expected, actual);
    }
}
