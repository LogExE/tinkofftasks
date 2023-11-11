package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    void testValidCars() {
        assertTrue(Task5.isValidRuCar("А123ВЕ777"));
        assertTrue(Task5.isValidRuCar("О777ОО177"));
        assertTrue(Task5.isValidRuCar("Р321ЕХ64"));
    }

    @Test
    void testInvalidCars() {
        assertFalse(Task5.isValidRuCar("123АВЕ777"));
        assertFalse(Task5.isValidRuCar("А123ВГ77"));
        assertFalse(Task5.isValidRuCar("А123ВЕ7777"));
        assertFalse(Task5.isValidRuCar("Х123ЕЙ64"));
    }
}
