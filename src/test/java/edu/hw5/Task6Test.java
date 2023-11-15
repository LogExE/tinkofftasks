package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    void testTrue() {
        assertTrue(Task6.isSubstring("abc", "achfdbaabgabcaabg"));
        assertTrue(Task6.isSubstring("stas", "pizzapastasodachocolate"));
        assertTrue(Task6.isSubstring("", "there is me eveywhere"));
    }

    @Test
    void testFalse() {
        assertFalse(Task6.isSubstring("xyz", "achfdbaabgabcaabg"));
        assertFalse(Task6.isSubstring("fedya", "pizzapastasodachocolate"));
    }
}
