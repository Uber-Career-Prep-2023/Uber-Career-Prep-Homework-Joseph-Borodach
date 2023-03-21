package career.prep.uber;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTests {

    private QueueI<Integer> queue;

    private void setup() {
        queue = new Queue<>();
    }

    @Test
    public void enqueueTest() {
        setup();

        assertTrue(queue.isEmpty());

        // 1st insertion
        queue.enqueue(1);
        assertPeek(1);

        // 2nd insertion
        queue.enqueue(2);
        assertPeek(2);

        // 3rd insertion
        queue.enqueue(3);
        assertPeek(3);
    }

    @Test
    public void dequeueTest() {
        setup();

        assertTrue(queue.isEmpty());

        // 1st insertion
        queue.enqueue(1);
        assertPeek(1);

        // 2nd insertion
        queue.enqueue(2);
        assertPeek(2);

        // 3rd insertion
        queue.enqueue(3);
        assertPeek(3);

        // 1st removal
        assertDeque(1);
        assertPeek(3);

        // 2nd removal
        assertDeque(2);
        assertPeek(3);

        // 3rd removal
        assertDeque(3);

        assertTrue(queue.isEmpty());
    }

    private void assertDeque(final int expected) {
        final int actual = queue.dequeue();
        assertEquals(expected, actual);
    }

    private void assertPeek(final int expected) {
        assertFalse(queue.isEmpty());
        final int actual = queue.peek();
        assertEquals(expected, actual);
    }

    @Test
    public void completeTest() {
        int S = 1;
        int N = 15;

        setup();

        assertTrue(queue.isEmpty());
        for (int i = S; i <= N; i++) {
            queue.enqueue(i);
            assertFalse(queue.isEmpty());
            assertEquals(i, (int) queue.peek());
        }
        for (int i = S; i <= N; i++) {
            assertFalse(queue.isEmpty());
            assertEquals(i, (int) queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }
}
