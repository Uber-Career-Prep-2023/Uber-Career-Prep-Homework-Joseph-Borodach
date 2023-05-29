package career.prep.uber;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author josephborodach
 *
 * JUnit 5 test class to test the {@link PrerequisiteCourses} class.
 */
public class PrerequisiteCoursesTests {

    @Test
    public void sample1Test() {
        final String[] courses = {"Intro to Programming", "Data Structures",
                "Advanced Algorithms", "Operating Systems", "Databases"};

        final Map<String, List<String>> preRequisites = Map.of(
                "Data Structures", List.of("Intro to Programming"),
                "Advanced Algorithms", List.of("Data Structures"),
                "Operating Systems", List.of("Advanced Algorithms"),
                "Databases", List.of("Advanced Algorithms")
        );

        final String[][] expected = {
                {"Intro to Programming", "Data Structures", "Advanced Algorithms", "Operating Systems", "Databases"},
                {"Intro to Programming", "Data Structures", "Advanced Algorithms", "Databases", "Operating Systems"}
        };

        check(courses, preRequisites, expected);
    }

    @Test
    public void sample2Test() {
        final String[] courses = {"Intro to Writing", "Contemporary Literature",
                "Ancient Literature", "Comparative Literature", "Plays & Screenplays"};

        final Map<String, List<String>> preRequisites = Map.of(
                "Contemporary Literature", List.of("Intro to Writing"),
                "Ancient Literature", List.of("Intro to Writing"),
                "Comparative Literature", List.of("Ancient Literature", "Contemporary Literature"),
                "Plays & Screenplays", List.of("Intro to Writing")
        );

        final String[][] expected = {
                {"Intro to Writing", "Plays & Screenplays", "Contemporary Literature", "Ancient Literature", "Comparative Literature"},
                {"Intro to Writing", "Contemporary Literature", "Plays & Screenplays", "Ancient Literature", "Comparative Literature"},
                {"Intro to Writing", "Contemporary Literature", "Ancient Literature", "Plays & Screenplays", "Comparative Literature"},
                {"Intro to Writing", "Ancient Literature", "Contemporary Literature",  "Plays & Screenplays", "Comparative Literature"},
                {"Intro to Writing", "Ancient Literature",  "Plays & Screenplays",  "Contemporary Literature", "Comparative Literature"},
                {"Intro to Writing", "Plays & Screenplays", "Ancient Literature",  "Contemporary Literature", "Comparative Literature"},
                {"Intro to Writing", "Ancient Literature",  "Contemporary Literature", "Comparative Literature", "Plays & Screenplays"},
                {"Intro to Writing", "Contemporary Literature",  "Ancient Literature", "Comparative Literature", "Plays & Screenplays"}
        };

        check(courses, preRequisites, expected);
    }

    /*****************
     * Edge cases.
     *****************/
    @Test
    public void disconnectedGraphTest() {
        final String[] courses = {"Intro", "DS", "Algs"};

        final Map<String, List<String>> preRequisites = Map.of(
                "DS", List.of("Intro")
        );

        final String[][] expected = {
                {"Intro", "DS", "Algs"},
                {"Intro", "Algs", "DS"},
                {"Algs", "Intro", "DS"}
        };

        check(courses, preRequisites, expected);
    }

    @Test
    public void noCoursesTest() {
        final String[] courses = {};

        final Map<String, List<String>> preRequisites = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> new PrerequisiteCourses().solveIt(courses, preRequisites));
    }

    @Test
    public void courseNotInCoursesTest() {
        final String[] courses = {"Intro to Programming"};

        final Map<String, List<String>> preRequisites = Map.of(
                "Data Structures", List.of("Intro to Programming")
        );

        assertThrows(IllegalArgumentException.class, () -> new PrerequisiteCourses().solveIt(courses, preRequisites));
    }

    @Test
    public void preRequisiteNotInCoursesTest() {
        final String[] courses = {"Intro to Programming"};

        final Map<String, List<String>> preRequisites = Map.of(
                "Intro to Programming", List.of("Data Structures")
        );

        assertThrows(IllegalArgumentException.class, () -> new PrerequisiteCourses().solveIt(courses, preRequisites));
    }

    /*****************
     * Helper methods.
     *****************/
    private void check(final String[] courses, final Map<String, List<String>> preRequisites, final String[][] validOrders) {
        final String[] actual = new PrerequisiteCourses().solveIt(courses, preRequisites);

        for (String[] validOrder : validOrders) {
            if (Arrays.equals(validOrder, actual)) {
                return;
            }
        }
        fail("");
    }
}