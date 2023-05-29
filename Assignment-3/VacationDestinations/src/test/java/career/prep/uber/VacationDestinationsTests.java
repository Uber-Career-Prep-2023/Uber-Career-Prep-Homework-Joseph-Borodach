package career.prep.uber;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link VacationDestinations} class.
 */
public class VacationDestinationsTests {

    @Test
    public void basicTest() {
        final Object[][] pairs = {
                {"Boston", "New York", 1.0},
                {"Boston", "New York", 2.0}
        };

        final String origin = "New York";

        final int travelTime = 8;

        assertThrows(IllegalArgumentException.class, () -> new VacationDestinations().solveIt(origin, travelTime, pairs));
    }

    @Test
    public void sample1Test() {
        final Object[][] pairs = {
                {"Boston", "New York", 4},
                {"New York", "Philadelphia", 2},
                {"Boston", "Newport", 1.5},
                {"Washington, D.C.", "Harper's Ferry", 1},
                {"Boston", "Portland", 2.5},
                {"Philadelphia", "Washington, D.C.", 2.5}
        };

        final String origin = "New York";

        final int travelTime = 5;

        // (["Boston", "Philadelphia"])
        // New York --> Boston == 4
        // New York --> Philadelphia == 2
        // Note: New York --> Philadelphia --> Washington, D.C.
        //      Does not work bc you need the extra 1 hour for layover = 2 + 1 + 2.5 = 5.5
        final int expected = 2;

        final int actual = new VacationDestinations().solveIt(origin, travelTime, pairs);

        assertEquals(expected, actual);
    }

    // There was a bug in the sample:
    // "Philadelphia" had a period included in it once, making "Philadelphia." and "Philadelphia" two different places.
    // That took me a while to debug lol.
    @Test
    public void sample2Test() {
        final Object[][] pairs = {
                {"Boston", "New York", 4},
                {"New York", "Philadelphia", 2},
                {"Boston", "Newport", 1.5},
                {"Washington, D.C.", "Harper's Ferry", 1},
                {"Boston", "Portland", 2.5},
                {"Philadelphia", "Washington, D.C.", 2.5}
        };

        final String origin = "New York";

        final int travelTime = 7;

        // (["Boston", "Philadelphia", "Washington, D.C", "Newport"])
        // New York --> Boston == 4
        // New York --> Philadelphia == 2
        // New York --> Philadelphia --> Washington, D.C. = (2 + 1 + 2.5) = 5.5
        // New York --> Boston --> Newport = (4 + 1 + 1.5) = 6.5
        final int expected = 4;

        final int actual = new VacationDestinations().solveIt(origin, travelTime, pairs);

        assertEquals(expected, actual);
    }

    @Test
    public void sample3Test() {
        final Object[][] pairs = {
                {"Boston", "New York", 4},
                {"New York", "Philadelphia", 2},
                {"Boston", "Newport", 1.5},
                {"Washington, D.C.", "Harper's Ferry", 1},
                {"Boston", "Portland", 2.5},
                {"Philadelphia", "Washington, D.C.", 2.5}
        };

        final String origin = "New York";

        final int travelTime = 8;

        // (["Boston", "Philadelphia", "Washington, D.C", "Newport", "Harper's Ferry", "Portland"])
        // New York --> Boston == 4
        // New York --> Philadelphia == 2
        // New York --> Philadelphia --> Washington, D.C. = (2 + 1 + 2.5) = 5.5
        // New York --> Boston --> Newport = (4 + 1 + 1.5) = 6.5
        // New York --> Philadelphia --> Washington, D.C. --> Harper's Ferry = (2 + 1 + 2.5 + 1 + 1) = 7.5
        // New York --> Boston --> Portland = (4 + 1 + 2.5) = 7.5
        final int expected = 6;

        final int actual = new VacationDestinations().solveIt(origin, travelTime, pairs);

        assertEquals(expected, actual);
    }

    /*****************
     * Edge cases.
     *****************/
    @Test
    public void noEdgesFromOriginTest() {
        final Object[][] pairs = {
                {"Boston", "Newport", 1.5},
                {"Washington, D.C.", "Harper's Ferry", 1},
                {"Boston", "Portland", 2.5},
                {"Philadelphia", "Washington, D.C.", 2.5}
        };

        final String origin = "New York";

        final int travelTime = 8;

        final int expected = 0;

        final int actual = new VacationDestinations().solveIt(origin, travelTime, pairs);

        assertEquals(expected, actual);
    }

    @Test
    public void negativeHoursTest() {
        final Object[][] pairs = {{"Boston", "Newport", -1.5}};

        final String origin = "New York";

        final int travelTime = 8;

        assertThrows(IllegalArgumentException.class, () -> new VacationDestinations().solveIt(origin, travelTime, pairs));
    }

    @Test
    public void duplicateEdgesTest() {
        final Object[][] pairs = {
                {"Boston", "New York", 1.0},
                {"Boston", "New York", 2.0}
        };

        final String origin = "New York";

        final int travelTime = 8;

        assertThrows(IllegalArgumentException.class, () -> new VacationDestinations().solveIt(origin, travelTime, pairs));
    }

    @Test
    public void duplicateEdges2Test() {
        final Object[][] pairs = {
                {"Boston", "New York", 1.0},
                {"New York", "Boston", 2.0}
        };

        final String origin = "New York";

        final int travelTime = 8;

        assertThrows(IllegalArgumentException.class, () -> new VacationDestinations().solveIt(origin, travelTime, pairs));
    }
}
