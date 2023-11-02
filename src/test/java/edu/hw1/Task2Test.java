package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Проверка функции на положительных числах")
    void positiveNumbers() {
        int dig1 = Task2.countDigits(5);
        assertEquals(1, dig1);

        int dig2 = Task2.countDigits(23);
        assertEquals(2, dig2);

        int dig3 = Task2.countDigits(5432);
        assertEquals(4, dig3);

        int n = 1;
        for (int i = 0; i <= 9; ++i, n *= 10) {
            int dign = Task2.countDigits(n);
            assertEquals(i + 1, dign);
        }
    }

    @Test
    @DisplayName("Проверка функции на отрицательных числах")
    void negativeNumbers() {
        int dig1 = Task2.countDigits(-5);
        assertEquals(1, dig1);

        int dig2 = Task2.countDigits(-23);
        assertEquals(2, dig2);

        int dig3 = Task2.countDigits(-5432);
        assertEquals(4, dig3);

        int n = -1;
        for (int i = 0; i <= 9; ++i, n *= 10) {
            int dign = Task2.countDigits(n);
            assertEquals(i + 1, dign);
        }
    }

    @Test
    @DisplayName("Проверка на Integer.MIN_VALUE")
    void minValue() {
        int dig = Task2.countDigits(Integer.MIN_VALUE);
        assertEquals(10, dig); // -2 ^ 31 = 2147483648
    }
}
