package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    void testFourPairs() {
        ArrayList<String> res = new ArrayList<>(List.of("()", "()", "()"));
        assertEquals(res, Task2.clusterize("()()()"));
    }

    @Test
    void testMatryoshka() {
        ArrayList<String> res = new ArrayList<>(List.of("((()))"));
        assertEquals(res, Task2.clusterize("((()))"));
    }

    @Test
    void testVariousNested() {
        ArrayList<String> res = new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"));
        assertEquals(res, Task2.clusterize("((()))(())()()(()())"));
    }

    @Test
    void testTwoClusters() {
        ArrayList<String> res = new ArrayList<>(List.of("((())())", "(()(()()))"));
        assertEquals(res, Task2.clusterize("((())())(()(()()))"));
    }

    @Test
    void testEmpty() {
        ArrayList<String> res = new ArrayList<>();
        assertEquals(res, Task2.clusterize(""));
    }

    @Test
    void testWithLetters() {
        ArrayList<String> res = new ArrayList<>(List.of("eeee(a(b1)(bu)s)", "(lol)", "(x(ooo))", "xa"));
        assertEquals(res, Task2.clusterize("eeee(a(b1)(bu)s)(lol)(x(ooo))xa"));
    }

    @Test
    void testTooMuchOpeningParentheses() {
        Throwable thr1 = catchThrowable(() -> Task2.clusterize("((())"));
        assertThat(thr1).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("opening");
    }

    @Test
    void testTooMuchClosingParentheses() {
        Throwable thr1 = catchThrowable(() -> Task2.clusterize("(())())"));
        assertThat(thr1).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("closing");
    }
}
