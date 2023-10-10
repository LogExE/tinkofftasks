package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка на правильных данных")
    void validInput() {
        long res1 = Task1.getVideoDurationSeconds("00:30");
        assertThat(res1).isEqualTo(30);

        long res2 = Task1.getVideoDurationSeconds("04:23");
        assertThat(res2).isEqualTo(263);

        long res3 = Task1.getVideoDurationSeconds("999:59");
        assertThat(res3).isEqualTo(59999);
    }

    @Test
    @DisplayName("Проверка при количестве секунд >= 60")
    void minutesTooMuch() {
        long res = Task1.getVideoDurationSeconds("00:60");
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка при неверном формате входа")
    void invalidFormat() {
        long res1 = Task1.getVideoDurationSeconds("ab:4e");
        assertThat(res1).isEqualTo(-1);

        long res2 = Task1.getVideoDurationSeconds("1000");
        assertThat(res2).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка обработки отрицательных чисел")
    void negativeTime() {
        long res = Task1.getVideoDurationSeconds("-10:00");
        assertThat(res).isEqualTo(-1);
    }
}
