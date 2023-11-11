package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testFridays() {
        List<LocalDate> expected1 = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );
        assertEquals(expected1, Task2.fiendishFridays(1925));

        List<LocalDate> expected2 = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );
        assertEquals(expected2, Task2.fiendishFridays(2024));
    }

    @Test
    void testNearest() {
        LocalDate given = LocalDate.of(2024, 8, 23);
        LocalDate expected = LocalDate.of(2024, 9, 13);
        assertEquals(expected, Task2.nearestFriday13th(given));
    }
}
