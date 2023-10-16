package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Проверка с четной длиной строки")
    void flipEven() {
        String res1 = Task4.fixString("123456");
        assertEquals(res1, "214365");

        String res2 = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertEquals(res2, "This is a mixed up string.");

        String res3 = Task4.fixString("оПомигети псаривьтс ртко!и");
        assertEquals(res3, "Помогите исправить строки!");
    }

    @Test
    @DisplayName("Проверка с нечетной длиной строки")
    void flipOdd() {
        String res1 = Task4.fixString("badce");
        assertEquals(res1, "abcde");
    }

    @Test
    @DisplayName("Проверка пустой строки")
    void flipEmpty() {
        String res1 = Task4.fixString("");
        assertEquals(res1, "");
    }
}
