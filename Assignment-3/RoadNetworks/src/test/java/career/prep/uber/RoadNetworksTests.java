package career.prep.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link RoadNetworks} class.
 */
public class RoadNetworksTests {
    /**
     * Note: The provided example did not list "Anchorage"! That's why I checked for it!
     */
    @Test
    public void sample1Test() {
        final List<String> towns = new ArrayList<>(Arrays.asList(
                "Anchorage", "Skagway", "Juneau", "Gustavus", "Homer", "Port Alsworth",
                "Glacier Bay", "Fairbanks", "McCarthy", "Copper Center", "Healy"));

        final String[][] roads = {
                {"Anchorage", "Homer"},
                {"Glacier Bay", "Gustavus"},
                {"Copper Center", "McCarthy"},
                {"Anchorage", "Copper Center"},
                {"Copper Center", "Fairbanks"},
                {"Healy", "Fairbanks"},
                {"Healy", "Anchorage"}};

        final int expected = 2;

        final int actual = new RoadNetworks().solveIt(towns, roads);

        assertEquals(expected, actual);
    }

    @Test
    public void sample2Test() {
        final List<String> towns = new ArrayList<>(Arrays.asList(
                "Kona", "Hilo", "Volcano", "Lahaina", "Hana", "Haiku",
                "Kahului", "Princeville", "Lihue", "Waimea"));

        final String[][] roads = {
                {"Kona", "Volcano"},
                {"Volcano", "Hilo"},

                {"Lahaina", "Hana"},
                {"Kahului", "Lahaina"},
                {"Hana", "Haiku"},
                {"Kahului", "Haiku"},

                {"Princeville", "Lihue"},
                {"Lihue", "Waimea"}};

        final int expected = 3;

        final int actual = new RoadNetworks().solveIt(towns, roads);

        assertEquals(expected, actual);
    }

    @Test
    public void oneConnectedStateTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList(
                "Kona", "Hilo", "Volcano", "Lahaina", "Hana", "Haiku",
                "Kahului", "Princeville", "Lihue", "Waimea"));

        final String[][] roads = {
                {"Kona", "Volcano"},
                {"Volcano", "Hilo"},

                {"Hilo", "Lahaina"},

                {"Lahaina", "Hana"},
                {"Kahului", "Lahaina"},
                {"Hana", "Haiku"},
                {"Kahului", "Haiku"},

                {"Haiku", "Princeville"},

                {"Princeville", "Lihue"},
                {"Lihue", "Waimea"}};

        final int expected = 1;

        final int actual = new RoadNetworks().solveIt(towns, roads);

        assertEquals(expected, actual);
    }

    @Test
    public void noRoadsTestTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList(
                "Kona", "Hilo", "Volcano", "Lahaina", "Hana", "Haiku",
                "Kahului", "Princeville", "Lihue", "Waimea"));

        final String[][] roads = {};

        final int expected = 0;

        final int actual = new RoadNetworks().solveIt(towns, roads);

        assertEquals(expected, actual);
    }

    /*************
     * Edge cases.
     *************/
    @Test
    public void roadMissingTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList("Homer"));

        final String[][] roads = {{"Anchorage", "Homer"}};

        assertThrows(IllegalArgumentException.class, () -> new RoadNetworks().solveIt(towns, roads));
    }

    @Test
    public void townListedTwiceTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList("Homer", "Anchorage", "Homer"));

        final String[][] roads = {{"Anchorage", "Homer"}};

        assertThrows(IllegalArgumentException.class, () -> new RoadNetworks().solveIt(towns, roads));
    }

    @Test
    public void nullTownTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList("Homer", null));

        final String[][] roads = {};

        assertThrows(IllegalArgumentException.class, () -> new RoadNetworks().solveIt(towns, roads));
    }

    @Test
    public void edgeConnectingSameVertex() {
        final List<String> towns = new ArrayList<>(Arrays.asList("Homer", "Anchorage"));

        final String[][] roads = {{"Homer", "Homer"}};

        assertThrows(IllegalArgumentException.class, () -> new RoadNetworks().solveIt(towns, roads));
    }

    @Test
    public void duplicateEdgeTest() {
        final List<String> towns = new ArrayList<>(Arrays.asList("Homer", "Anchorage"));

        final String[][] roads = {{"Homer", "Anchorage"}, {"Anchorage", "Homer"}};

        assertThrows(IllegalArgumentException.class, () -> new RoadNetworks().solveIt(towns, roads));
    }
}