package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Task5Test {
    @Test
    @DisplayName("Проверка, когда число - особый палиндром")
    void testTruthy() {
        boolean res1 = Task5.isPalindromeDescendant(11211230);
        assertThat(res1).isTrue();
        boolean res2 = Task5.isPalindromeDescendant(13001120);
        assertThat(res2).isTrue();
        boolean res3 = Task5.isPalindromeDescendant(23336014);
        assertThat(res3).isTrue();
        boolean res4 = Task5.isPalindromeDescendant(11);
        assertThat(res4).isTrue();
        boolean res5 = Task5.isPalindromeDescendant(9);
        assertThat(res5).isTrue();
    }

    @Test
    @DisplayName("Проверка, когда число - не особый палиндром")
    void testFalsy() {
        boolean res1 = Task5.isPalindromeDescendant(123);
        assertThat(res1).isFalse();
        boolean res2 = Task5.isPalindromeDescendant(35);
        assertThat(res2).isFalse();
        boolean res3 = Task5.isPalindromeDescendant(62);
        assertThat(res3).isFalse();
        boolean res4 = Task5.isPalindromeDescendant(1111113);
        assertThat(res4).isFalse();
    }

    @Test
    @DisplayName("Проверка, когда число - отрицательное")
    void testNegative() {
        Throwable thrown = catchThrowable(() -> Task5.isPalindromeDescendant(-42));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("negative");
    }
}
