package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testFac() {
        assertEquals(24, Task2.parFactorial(4));
        assertEquals(5040, Task2.parFactorial(7));
        assertEquals(479001600, Task2.parFactorial(12));
    }
}
