package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void testExamples() {
        assertEquals(Task1.atbash("Hello world!"), "Svool dliow!");
        assertEquals(
            Task1.atbash(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"),
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
        );
    }

    @Test
    void testEmpty() {
        assertEquals(Task1.atbash(""), "");
    }

    @Test
    void testGreek() {
        assertEquals(Task1.atbash("ἓν οἶδα ὅτι οὐδὲν οἶδα"), "ἓν οἶδα ὅτι οὐδὲν οἶδα");
    }

    @Test
    void testCapitalized() {
        assertEquals(Task1.atbash("ABC"), "ZYX");
    }
}
