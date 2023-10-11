package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task3Test {
    @Test
    @DisplayName("Проверка, когда можно вложить массив")
    void canNest() {
        boolean res1 = Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {0, 6}
        );
        assertThat(res1).isEqualTo(true);

        boolean res2 = Task3.isNestable(
            new int[] {3, 1},
            new int[] {4, 0}
        );
        assertThat(res2).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка, когда нельзя вложить массив")
    void cannotNest() {
        boolean res1 = Task3.isNestable(
            new int[] {9, 9, 8},
            new int[] {8, 9}
        );
        assertThat(res1).isEqualTo(false);

        boolean res2 = Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {2, 3}
        );
        assertThat(res2).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка, когда есть пустой массив")
    void emptyArrays() {
        assertThatThrownBy(() -> Task3.isNestable(
            new int[] {1, 2, 3, 4},
            new int[] {}
        )).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task3.isNestable(
            new int[] {},
            new int[] {1, -42}
        )).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task3.isNestable(
            new int[] {},
            new int[] {}
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
