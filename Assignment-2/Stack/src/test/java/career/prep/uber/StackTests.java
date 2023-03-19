package career.prep.uber;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class StackTests {

    @Test
    @DisplayName("All incompessing test")
    public void t1() {
        int S = 1;
        int N = 15;
        StackI<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        for (int i = S; i <= N; i++) {
            stack.push(i);
            assertFalse(stack.isEmpty());
            assertEquals(i, stack.size());
        }
        for (int i = N; i >= S; i--) {
            assertFalse(stack.isEmpty());
            assertEquals(i, (int) stack.peek());
            assertEquals(i, (int) stack.top());
            assertEquals(i, (int) stack.pop());
            assertEquals(i - 1, stack.size());
        }
        assertTrue(stack.isEmpty());
    }
}
