package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void testExamples() {
        assertEquals("Svool dliow!", Task1.atbash("Hello world!"));
        assertEquals(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
            Task1.atbash(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler")
        );
    }

    @Test
    void testEmpty() {
        assertEquals("", Task1.atbash(""));
    }

    @Test
    void testGreek() {
        assertEquals("ἓν οἶδα ὅτι οὐδὲν οἶδα", Task1.atbash("ἓν οἶδα ὅτι οὐδὲν οἶδα"));
    }

    @Test
    void testCapitalized() {
        assertEquals("ZYX", Task1.atbash("ABC"));
    }
}
