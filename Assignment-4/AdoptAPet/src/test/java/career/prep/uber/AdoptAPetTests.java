package career.prep.uber;

import org.junit.Test;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * JUnit 5 test class to test the {@link AdoptAPet} class.
 */
public class AdoptAPetTests {

    private AdoptAPet adoptAPet;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;

    @Before
    public void setUp() {
        // Redirect System.out to the outputStream
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        // Restore System.out
        System.setOut(originalOutput);
    }

    @Test
    public void basicTest() {
        List<String[]> pets = new ArrayList<>(Arrays.<String[]>asList(
                new String[]{"Sadie", "dog", "4 days"}
        ));

        adoptAPet = new AdoptAPet(pets);

        String[] person = {"Bob", "person", "dog"};

        String expectedPet = "Sadie, dog";

        check(person, expectedPet);
    }

    @Test
    public void sample1Test() {
        // Initial Input.
        List<String[]> pets = (List<String[]>) Arrays.asList(
                new String[]{"Sadie", "dog", "4 days"},
                new String[]{"Woof", "cat", "7 days"},
                new String[]{"Chirpy", "dog", "2 days"},
                new String[]{"Lola", "dog", "1 days"}
        );

        adoptAPet = new AdoptAPet(pets);

        // Part 1.
        String[] person1 = {"Bob", "person", "dog"};
        check(person1, "Sadie, dog");

        // Section 2.
        String[] pet = {"Floofy", "cat"};
        check(pet, "");

        // Section 3.
        String[] person2 = {"Sally", "person", "cat"};
        check(person2, "Woof, cat");

        // Section 4.
        String[] person3 = {"Ji", "person", "cat"};
        check(person3, "Floofy, cat");

        // Section 5.
        String[] person4 = {"Ali", "person", "cat"};
        check(person4, "Chirpy, dog");
    }

    @Test
    public void emptyPetsTest() {
        List<String[]> pets = new ArrayList<>();

        adoptAPet = new AdoptAPet(pets);

        String[] person = {"Bob", "person", "dog"};

        assertThrows(IllegalArgumentException.class, () -> adoptAPet.solveIt(person));
    }

    private void check(String[] entity, String expected) {
        outputStream.reset();
        adoptAPet.solveIt(entity);
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}