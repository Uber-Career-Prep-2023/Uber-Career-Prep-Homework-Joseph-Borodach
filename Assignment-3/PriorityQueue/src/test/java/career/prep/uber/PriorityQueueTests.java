package career.prep.uber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * JUnit 5 test class to test the {@link PriorityQueue} class.
 */
public class PriorityQueueTests {
    @Test
    @DisplayName("No elements in priorityQueue test")
    public void emptyPriorityQueueTest() {
        isEmpty(new PriorityQueue());
    }

    @Test
    @DisplayName("Insert one number and get top number test")
    public void oneNumberTest() {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert("s", 1);

        final int expected = 1;

        final int actual = priorityQueue.top();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers and get top number test")
    public void twoNumbersTest() {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert("a", 1);

        // 2 should swim up to index 1
        priorityQueue.insert("b", 2);

        final int expected = 1;

        final int actual = priorityQueue.top();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers and test that top number is negative")
    public void negativeNumbersTest() {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert("a", 1);

        // 2 should swim up to index 1
        priorityQueue.insert("b", -1);

        final int expected = -1;

        final int actual = priorityQueue.top();

        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("Insert two identical numbers and test that the priorityQueue contains both")
    public void duplicatesTests() {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert("a", 1);

        // 2 should swim up to index 1
        priorityQueue.insert("a", 1);

        final int expected = 1;

        final int actual = priorityQueue.top();

        assertEquals(expected, actual);

        priorityQueue.remove();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers, remove smaller and get top number")
    public void twoNumbersRemovedTest() {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert("a", 1);

        // 2 should swim up to index 1
        priorityQueue.insert("b",2);

        priorityQueue.remove();

        final int expected = 2;

        final int actual = priorityQueue.top();

        assertEquals(expected, actual);

        priorityQueue.remove();

        isEmpty(priorityQueue);
    }

    @Test
    @DisplayName("Insert multiple numbers in random orders numbers")
    public void severalNumbersTests() {
        PriorityQueue priorityQueue = new PriorityQueue();

        isEmpty(priorityQueue);

        priorityQueue.insert("a", 5);
        priorityQueue.insert("b", 1);
        priorityQueue.insert("c", 4);
        priorityQueue.insert("d", 2);
        priorityQueue.insert("e", 3);

        for (int i = 1; i <= 5; i++) {
            final int expected = i;

            final int actual = priorityQueue.top();

            assertEquals(expected, actual);

            priorityQueue.remove();
        }

        isEmpty(priorityQueue);
    }

    private void isEmpty(PriorityQueue priorityQueue) {
        assertThrows(NoSuchElementException.class, () -> priorityQueue.top());
    }
}
