package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    void testValid() {
        assertTrue(Task4.passValid("ab*bus"));
        assertTrue(Task4.passValid("~_~"));
        assertTrue(Task4.passValid("#@&$!"));
        assertTrue(Task4.passValid("|>_<|"));
    }

    @Test
    void testInvalid() {
        assertFalse(Task4.passValid("iluvpizza"));
        assertFalse(Task4.passValid("Feddy123"));
        assertFalse(Task4.passValid("MeLoN_MuSk"));
    }
}
