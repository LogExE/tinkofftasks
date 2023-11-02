package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {
    @Test
    @DisplayName("Проверка нулевых поворотов")
    void testIdentity() {
        int res1 = Task7.rotateLeft(8, 0);
        assertEquals(8, res1);
        int res2 = Task7.rotateRight(23, 0);
        assertEquals(23, res2);
    }

    @Test
    @DisplayName("Проверка поворота влево")
    void testRotateLeft() {
        int res1 = Task7.rotateLeft(16, 1);
        assertEquals(1, res1);
        int res2 = Task7.rotateLeft(17, 2);
        assertEquals(6, res2);
        // это для проверки поворотов на число больше, чем число битов
        int res3 = Task7.rotateLeft(17, 7);
        assertEquals(6, res3);
    }

    @Test
    @DisplayName("Проверка поворота вправо")
    void testRotateRight() {
        int res1 = Task7.rotateRight(8, 1);
        assertEquals(4, res1);
        // это для проверки поворотов на число больше, чем число битов
        int res2 = Task7.rotateRight(8, 5);
        assertEquals(4, res2);
    }

    @Test
    @DisplayName("Проверка на отрицательных числах")
    void testNegative() {
        Throwable thrownLeft = catchThrowable(() -> Task7.rotateLeft(-1, 1));
        assertThat(thrownLeft).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("positive");
        Throwable thrownRight = catchThrowable(() -> Task7.rotateRight(-1, 1));
        assertThat(thrownRight).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("positive");
    }

    @Test
    @DisplayName("Проверка на отрицательных сдвигах")
    void testNegativeShifts() {
        Throwable thrownLeft = catchThrowable(() -> Task7.rotateLeft(1, -1));
        assertThat(thrownLeft).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Shift");
        Throwable thrownRight = catchThrowable(() -> Task7.rotateRight(1, -1));
        assertThat(thrownRight).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Shift");
    }
}
