package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Task3Test {
    @Test
    @DisplayName("Проверка, когда можно вложить массив")
    void canNest() {
        boolean res1 = Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {0, 6}
        );
        assertThat(res1).isTrue();

        boolean res2 = Task3.isNestable(
            new int[] {3, 1},
            new int[] {4, 0}
        );
        assertThat(res2).isTrue();
    }

    @Test
    @DisplayName("Проверка, когда нельзя вложить массив")
    void cannotNest() {
        boolean res1 = Task3.isNestable(
            new int[] {9, 9, 8},
            new int[] {8, 9}
        );
        assertThat(res1).isFalse();

        boolean res2 = Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {2, 3}
        );
        assertThat(res2).isFalse();
    }

    @Test
    @DisplayName("Проверка, когда есть пустой массив")
    void emptyArrays() {
        Throwable thrown1 = catchThrowable(() -> Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {}
        ));
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("empty");
        Throwable thrown2 = catchThrowable(() -> Task3.isNestable(
            new int[] {},
            new int[] {1, -42}
        ));
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("empty");
        Throwable thrown3 = catchThrowable(() -> Task3.isNestable(
            new int[] {},
            new int[] {}
        ));
        assertThat(thrown3).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("empty");
    }
}
