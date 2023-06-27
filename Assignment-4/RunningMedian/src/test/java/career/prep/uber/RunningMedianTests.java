package career.prep.uber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunningMedianTests {

    private RunningMedian runningMedian;

    @BeforeEach
    void setup() {
        runningMedian = new RunningMedian();
    }

    @Test
    void singleValueTest() {
        insertAndAssert(5, 5.0);
    }

    @Test
    void sampleTest() {
        insertAndAssert(1, 1.0);
        insertAndAssert(11, 6.0);
        insertAndAssert(4, 4.0);
        insertAndAssert(15, 7.5);
        insertAndAssert(12, 11);
    }

    @Test
    void ascendingOrderTest() {
        insertAndAssert(1, 1.0);
        insertAndAssert(2, 1.5);
        insertAndAssert(3, 2.0);
        insertAndAssert(4, 2.5);
        insertAndAssert(5, 3.0);
    }

    @Test
    void descendingOrderTest() {
        insertAndAssert(5, 5.0);
        insertAndAssert(4, 4.5);
        insertAndAssert(3, 4.0);
        insertAndAssert(2, 3.5);
        insertAndAssert(1, 3.0);
    }

    @Test
    void repeatedValuesTest() {
        insertAndAssert(2, 2.0);
        insertAndAssert(2, 2.0);
        insertAndAssert(2, 2.0);
        insertAndAssert(2, 2.0);
        insertAndAssert(2, 2.0);
    }

    private void insertAndAssert(final int num, final double expected) {
        final double actual = runningMedian.solveIt(num);
        assertEquals(expected, actual);
    }
}
