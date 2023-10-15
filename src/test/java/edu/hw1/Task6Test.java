package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Task6Test {
    @Test
    @DisplayName("Проверка числа Капрекара")
    void testKaprekar() {
        assertThat(Task6.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка числа шагов")
    void testSteps() {
        assertThat(Task6.countK(6621)).isEqualTo(5);
        assertThat(Task6.countK(6554)).isEqualTo(4);
        assertThat(Task6.countK(1234)).isEqualTo(3);
        assertThat(Task6.countK(9993)).isEqualTo(6);
    }

    @Test
    @DisplayName("Проверка чисел <= 1000")
    void testLow() {
        Throwable thrown1 = catchThrowable(() -> Task6.countK(1000));
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("1000");
        Throwable thrown2 = catchThrowable(() -> Task6.countK(887));
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("digit");
    }

    @Test
    @DisplayName("Проверка чисел из одной и той же цифры")
    void testRepeating() {
        Throwable thrown1 = catchThrowable(() -> Task6.countK(1111));
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("distinct");
        Throwable thrown2 = catchThrowable(() -> Task6.countK(5555));
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("distinct");
    }
}
