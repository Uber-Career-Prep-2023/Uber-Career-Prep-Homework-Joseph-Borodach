package career.prep.uber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link Heap} class.
 */
public class HeapTests {

    @Test
    @DisplayName("No elements in heap test")
    public void emptyHeapTest() {
        isEmpty(new Heap());
    }

    @Test
    @DisplayName("Insert one number and get top number test")
    public void oneNumberTest() {
        Heap heap = new Heap();

        heap.insert(1);

        final int expected = 1;

        final int actual = heap.top();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers and get top number test")
    public void twoNumbersTest() {
        Heap heap = new Heap();

        heap.insert(1);

        // 2 should swim up to index 1
        heap.insert(2);

        final int expected = 1;

        final int actual = heap.top();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers and test that top number is negative")
    public void negativeNumbersTest() {
        Heap heap = new Heap();

        heap.insert(1);

        // 2 should swim up to index 1
        heap.insert(-1);

        final int expected = -1;

        final int actual = heap.top();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two identical numbers and test that the heap contains both")
    public void duplicatesTests() {
        Heap heap = new Heap();

        heap.insert(1);

        // 2 should swim up to index 1
        heap.insert(1);

        final int expected = 1;

        final int actual = heap.top();

        assertEquals(expected, actual);

        heap.remove();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Insert two numbers, remove smaller and get top number")
    public void twoNumbersRemovedTest() {
        Heap heap = new Heap();

        heap.insert(1);

        // 2 should swim up to index 1
        heap.insert(2);

        heap.remove();

        final int expected = 2;

        final int actual = heap.top();

        assertEquals(expected, actual);

        heap.remove();

        isEmpty(heap);
    }

    @Test
    @DisplayName("Insert multiple numbers in random orders numbers")
    public void severalNumbersTests() {
        Heap heap = new Heap();

        isEmpty(heap);

        heap.insert(5);
        heap.insert(1);
        heap.insert(4);
        heap.insert(2);
        heap.insert(3);

        for (int i = 1; i <= 5; i++) {
            final int expected = i;

            final int actual = heap.top();

            assertEquals(expected, actual);

            heap.remove();
        }

        isEmpty(heap);
    }

    private void isEmpty(Heap heap) {
        assertThrows(NoSuchElementException.class, () -> heap.top());
    }
}