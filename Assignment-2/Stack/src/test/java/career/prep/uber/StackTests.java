package career.prep.uber;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTests {

    private StackI<Integer> stack;

    private void setup() {
        stack = new Stack<>();
    }

    @Test
    public void enqueueTest() {
        setup();

        assertTrue(stack.isEmpty());

        push(1, 2, 3);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void dequeueTest() {
        setup();

        assertTrue(stack.isEmpty());

        push(1, 2, 3);

        pop(3, 2, 1);

        assertTrue(stack.isEmpty());
    }

    @Test
    public void dequeueRandomTest() {
        setup();

        assertTrue(stack.isEmpty());

        push(2, 1, 3);

        pop(3, 1, 2);

        assertTrue(stack.isEmpty());
    }

    @Test
    public void dequeueDescendingTest() {
        setup();

        assertTrue(stack.isEmpty());

        push(3, 2, 1);

        pop(1, 2, 3);

        assertTrue(stack.isEmpty());
    }

    private void push(final int v1, final int v2, final int v3) {
        // 1st insertion
        push(v1);

        // 2nd insertion
        push(v2);

        // 3rd insertion
        push(v3);
    }

    private void push(final int val) {
        stack.push(val);
        assertPeek(val);
    }

    private void pop(final int v1, final int v2, final int v3) {
        // 1st removal
        pop(v1);

        // 2nd removal
        pop(v2);

        // 3rd removal
        pop(v3);
    }

    private void pop(final int expected) {
        assertFalse(stack.isEmpty());
        final int actual = stack.pop();
        assertEquals(expected, actual);
    }

    private void assertPeek(final int expected) {
        assertFalse(stack.isEmpty());
        final int actual = stack.peek();
        assertEquals(expected, actual);
    }

    @Test
    public void completeTest() {
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
