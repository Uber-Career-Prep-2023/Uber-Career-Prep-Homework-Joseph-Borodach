package career.prep.uber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class QueueTests {

    @Test
    @DisplayName("All incompessing test")
    public void t1() {
        int S = 1;
        int N = 15;
        QueueI<Integer> queue = new Queue<>();
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
