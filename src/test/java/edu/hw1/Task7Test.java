package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Task7Test {
    @Test
    @DisplayName("Проверка нулевых поворотов")
    void testIdentity() {
        int res1 = Task7.rotateLeft(8, 0);
        assertThat(res1).isEqualTo(8);
        int res2 = Task7.rotateRight(23, 0);
        assertThat(res2).isEqualTo(23);
    }

    @Test
    @DisplayName("Проверка поворота влево")
    void testRotateLeft() {
        int res1 = Task7.rotateLeft(16, 1);
        assertThat(res1).isEqualTo(1);
        int res2 = Task7.rotateLeft(17, 2);
        assertThat(res2).isEqualTo(6);
        // это для проверки поворотов на число больше, чем число битов
        int res3 = Task7.rotateLeft(17, 7);
        assertThat(res3).isEqualTo(6);
    }

    @Test
    @DisplayName("Проверка поворота вправо")
    void testRotateRight() {
        int res1 = Task7.rotateRight(8, 1);
        assertThat(res1).isEqualTo(4);
        // это для проверки поворотов на число больше, чем число битов
        int res2 = Task7.rotateRight(8, 5);
        assertThat(res2).isEqualTo(4);
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
