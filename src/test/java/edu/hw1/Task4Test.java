package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Проверка с четной длиной строки")
    void flipEven() {
        String res1 = Task4.fixString("123456");
        assertEquals("214365", res1);

        String res2 = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertEquals("This is a mixed up string.", res2);

        String res3 = Task4.fixString("оПомигети псаривьтс ртко!и");
        assertEquals("Помогите исправить строки!", res3);
    }

    @Test
    @DisplayName("Проверка с нечетной длиной строки")
    void flipOdd() {
        String res = Task4.fixString("badce");
        assertEquals("abcde", res);
    }

    @Test
    @DisplayName("Проверка пустой строки")
    void flipEmpty() {
        String res = Task4.fixString("");
        assertEquals( "", res);
    }
}
