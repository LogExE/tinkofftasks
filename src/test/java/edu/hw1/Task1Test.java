package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Проверка на правильных данных")
    void validInput() {
        long res1 = Task1.getVideoDurationSeconds("00:30");
        assertEquals(30, res1);

        long res2 = Task1.getVideoDurationSeconds("04:23");
        assertEquals(263, res2);

        long res3 = Task1.getVideoDurationSeconds("999:59");
        assertEquals(59999, res3);
    }

    @Test
    @DisplayName("Проверка при количестве секунд >= 60")
    void minutesTooMuch() {
        long res = Task1.getVideoDurationSeconds("00:60");
        assertEquals(-1, res);
    }

    @Test
    @DisplayName("Проверка при неверном формате входа")
    void invalidFormat() {
        long res1 = Task1.getVideoDurationSeconds("ab:4e");
        assertEquals(-1, res1);

        long res2 = Task1.getVideoDurationSeconds("1000");
        assertEquals(-1, res2);
    }

    @Test
    @DisplayName("Проверка обработки отрицательных чисел")
    void negativeTime() {
        long res = Task1.getVideoDurationSeconds("-10:00");
        assertEquals(-1, res);
    }
}
