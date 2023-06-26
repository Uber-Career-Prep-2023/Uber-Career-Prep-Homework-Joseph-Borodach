package career.prep.uber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * JUnit 5 test class to test the {@link Trie} class.
 */
public class TrieTests {

    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Nested
    class InsertionTests {

        @Test
        void invalidWordTest() {
            assertFalse(trie.isValidWord("uber"));
        }

        @Test
        void validWordTest() {
            assertDoesNotThrow(() -> trie.insert("uber"));
            assertTrue(trie.isValidWord("uber"));
        }

        @Test
        void invalidSubwordTest() {
            trie.insert("uber");
            assertFalse(trie.isValidWord("ube"));
        }

        @Test
        void notCaseSensitiveTest() {
            trie.insert("uber");
            assertTrue(trie.isValidWord("UBER"));
        }
    }

    @Nested
    class RemovalTests {

        @Test
        void validRemovalTest() {
            String word = "uber";
            trie.insert(word);
            assertDoesNotThrow(() -> trie.remove(word));
            assertFalse(trie.isValidWord(word));
        }

        @Test
        void invalidSubwordRemovalTest() {
            trie.insert("uber");
            assertThrows(IllegalArgumentException.class, () -> trie.remove("ube"));
        }

        @Test
        void notCaseSensitiveRemovalTest() {
            String word = "uber";
            trie.insert(word);
            assertDoesNotThrow(() -> trie.remove("UBER"));
            assertFalse(trie.isValidWord(word));
        }
    }

    @Nested
    class IllegalArgumentExceptionTests {

        @Test
        void nullWordTest() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(null)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(null)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(null))
            );
        }

        @Test
        void invalidCharacterTest() {
            String word = "u#er";
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(word))
            );
        }

        @Test
        void invalidNumberTest() {
            String word = "123";
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(word))
            );
        }

        @Test
        void invalidSpaceTest() {
            String word = "u er";
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(word))
            );
        }

        @Test
        void blankTest() {
            String word = " ";
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(word))
            );
        }

        @Test
        void emptyTest() {
            String word = "";
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.insert(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.remove(word)),
                    () -> assertThrows(IllegalArgumentException.class, () -> trie.isValidWord(word))
            );
        }
    }
}
