package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка с четной длиной строки")
    void flipEven() {
        String res1 = Task4.fixString("123456");
        assertThat(res1).isEqualTo("214365");

        String res2 = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(res2).isEqualTo("This is a mixed up string.");

        String res3 = Task4.fixString("оПомигети псаривьтс ртко!и");
        assertThat(res3).isEqualTo("Помогите исправить строки!");
    }

    @Test
    @DisplayName("Проверка с нечетной длиной строки")
    void flipOdd() {
        String res1 = Task4.fixString("badce");
        assertThat(res1).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Проверка пустой строки")
    void flipEmpty() {
        String res1 = Task4.fixString("");
        assertThat(res1).isEqualTo("");
    }
}
