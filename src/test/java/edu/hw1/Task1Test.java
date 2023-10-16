package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Проверка на правильных данных")
    void validInput() {
        long res1 = Task1.getVideoDurationSeconds("00:30");
        assertEquals(res1, 30);

        long res2 = Task1.getVideoDurationSeconds("04:23");
        assertEquals(res2, 263);

        long res3 = Task1.getVideoDurationSeconds("999:59");
        assertEquals(res3, 59999);
    }

    @Test
    @DisplayName("Проверка при количестве секунд >= 60")
    void minutesTooMuch() {
        long res = Task1.getVideoDurationSeconds("00:60");
        assertEquals(res, -1);
    }

    @Test
    @DisplayName("Проверка при неверном формате входа")
    void invalidFormat() {
        long res1 = Task1.getVideoDurationSeconds("ab:4e");
        assertEquals(res1, -1);

        long res2 = Task1.getVideoDurationSeconds("1000");
        assertEquals(res2, -1);
    }

    @Test
    @DisplayName("Проверка обработки отрицательных чисел")
    void negativeTime() {
        long res = Task1.getVideoDurationSeconds("-10:00");
        assertEquals(res, -1);
    }
}
