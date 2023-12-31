package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    @Test
    @DisplayName("Проверка числа Капрекара")
    void testKaprekar() {
        assertEquals(0, Task6.countK(6174));
    }

    @Test
    @DisplayName("Проверка числа шагов")
    void testSteps() {
        assertEquals(5, Task6.countK(6621));
        assertEquals(4, Task6.countK(6554));
        assertEquals(3, Task6.countK(1234));
        assertEquals(6, Task6.countK(9993));
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
