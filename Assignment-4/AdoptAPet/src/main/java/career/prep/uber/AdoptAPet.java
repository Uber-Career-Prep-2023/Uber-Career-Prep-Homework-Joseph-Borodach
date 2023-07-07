package career.prep.uber;

import java.util.*;
import java.util.PriorityQueue;


/**
 * Description:
 *   An animal shelter that houses cats and dogs wants to ensure no pet has to wait too long for a forever home.
 *   Therefore, anyone who comes to adopt a pet can pick the species (cat or dog) but not the specific animal;
 *   they are assigned the animal of that species that has been in the shelter longest.
 *   If there are no animals available of the desired species, they must take the other species.
 *   You are given a list of pets in the shelter with their names, species, and time in the shelter at the start of a week.
 *   You receive a sequence of incoming people (to adopt pets) and animals (new additions to the shelter) one at a time.
 *   Print the names and species of the pets as they are adopted out.
 *
 * Assumptions:
 *   The descriptions seems to limit the number of species to 2: Dogs and Cats.
 *   a. It specifically mentions that the animal shelter houses cats and dogs.
 *   b. It states that anyone who comes to adopt a pet can choose between these two species.
 *   c. Additionally, if there are no animals available of the desired species, they must take the other species (cats or dogs).
 *
 * Notation:
 *   n: The total number of pets.
 *
 * Original Insertion
 *   Time: O(n log n), linearithmic.
 *     - If all the initial pets are of the same type, inserting each one is O(log n).
 *   Space: O(2n) = O(n), linear.
 *     - Extra space is used to sort the initial pets.
 *
 * Putting & Adopting Pets:
 *   Time: O(1), constant.
 *   Space: O(1), constant.
 *
 * Took ~45 Minutes.
 */
public class AdoptAPet {
    private Queue<Pet> dogs;
    private Queue<Pet> cats;

    /**
     * Represents a pet with a name and number of days in the shelter.
     */
    public class Pet {
        public int days;
        public String name;

        public Pet(final String name, final int days) {
            this.name = name;
            this.days = days;
        }
    }

    /**
     * Initializes the AdoptAPet class with the given list of pets at the start of the week.
     * @param pets The list of pets in the shelter with their names, species, and time in the shelter.
     */
    public AdoptAPet(List<String[]> pets) {
        if (pets == null) {
            throw new IllegalArgumentException("Initial pets list cannot be null.");
        }

        dogs = new PriorityQueue<>((a, b) -> b.days - a.days);
        cats = new PriorityQueue<>((a, b) -> b.days - a.days);

        for (String[] petString : pets) {
            verify(petString);

            String species = convert(petString[1]);
            addPet(species, petString);
        }
    }

    /**
     * Processes an incoming person or pet and prints the adopted pet's name and species.
     * @param entry The incoming person or pet with their name and species.
     */
    public void solveIt(String[] entry) {
        verify(entry);
        String type = convert(entry[1]);
        if (type.equals("person")) {
            adoptPet(entry);
        } else {
            addPet(type, entry);
        }
    }

    private void addPet(String species, String[] entry) {
        Pet pet = createPet(entry);

        if (species.equals("dog")) {
            dogs.offer(pet);
        } else if (species.equals("cat")) {
            cats.offer(pet);
        } else {
            throw new IllegalArgumentException("Species are limited to cats and dogs: " + species);
        }
    }

    private Pet createPet(String[] entry) {
        verify(entry);
        String name = entry[0];
        int days = 0;
        if (entry.length > 2) {
            days = getDays(entry[2]);
        }
        return new Pet(name, days);
    }

    private int getDays(String str) {
        int idx = 0;
        while (idx < str.length()) {
            if (!Character.isDigit(str.charAt(idx))) {
                break;
            }
            idx++;
        }
        String stringDays = str.substring(0, idx);
        int days = Integer.parseInt(stringDays);
        return days;
    }
    
    private void adoptPet(String[] entry) {
        if (entry.length !=  3) {
            throw new IllegalArgumentException("People objects must have 3 properties.");
        }
        String preferenceType = convert(entry[2]);
        
        String pet = getPet(preferenceType);

        System.out.println(pet);
    }
    
    private String getPet(String preferenceType) {
        if (dogs.isEmpty() && cats.isEmpty()) {
            throw new IllegalArgumentException("There are no pets available at this time.");
        }
        if (preferenceType == "dog") {
            return !dogs.isEmpty() ? getDog() : getCat();
        }
        if (preferenceType == "cat") {
            return !cats.isEmpty() ? getCat() : getDog();
        }
        throw new IllegalArgumentException("Preference types are limited to cats and dogs: " + preferenceType);
    }

    private String getDog() {
        return dogs.poll().name + ", dog";
    }

    private String getCat() {
        return cats.poll().name + ", cat";
    }

    private String convert(String species) {
        verify(species);
        return species.toLowerCase();
    }

    private void verify(String[] entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entries cannot be null.");
        }
        if (entry.length < 2) {
            throw new IllegalArgumentException("Entries must contain at least 2 properties.");
        }
    }

    private void verify(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Strings cannot be null.");
        }
        if (str.isEmpty() || str.isBlank()) {
            throw new IllegalArgumentException("Strings cannot be empty or blank.");
        }
    }
}