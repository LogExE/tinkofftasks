package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка функции на положительных числах")
    void positiveNumbers() {
        int dig1 = Task2.countDigits(5);
        assertThat(dig1).isEqualTo(1);

        int dig2 = Task2.countDigits(23);
        assertThat(dig2).isEqualTo(2);

        int dig3 = Task2.countDigits(5432);
        assertThat(dig3).isEqualTo(4);

        int dign = 1;
        for (int n = 0; n <= 9; ++n, dign *= 10)
            assertThat(Task2.countDigits(dign)).isEqualTo(n + 1);
    }

    @Test
    @DisplayName("Проверка функции на отрицательных числах")
    void negativeNumbers() {
        int dig1 = Task2.countDigits(-5);
        assertThat(dig1).isEqualTo(1);

        int dig2 = Task2.countDigits(-23);
        assertThat(dig2).isEqualTo(2);

        int dig3 = Task2.countDigits(-5432);
        assertThat(dig3).isEqualTo(4);

        int dign = -1;
        for (int n = 0; n <= 9; ++n, dign *= 10)
            assertThat(Task2.countDigits(dign)).isEqualTo(n + 1);
    }
}
