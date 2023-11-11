package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @Test
    void testAtLeast3CharsAnd0On3False() {
        assertFalse(Task7.atLeast3CharsAnd0On3(""));
        assertFalse(Task7.atLeast3CharsAnd0On3("0"));
        assertFalse(Task7.atLeast3CharsAnd0On3("01"));
        assertFalse(Task7.atLeast3CharsAnd0On3("101"));
        assertFalse(Task7.atLeast3CharsAnd0On3("111111"));
    }

    @Test
    void testAtLeast3CharsAnd0On3True() {
        assertTrue(Task7.atLeast3CharsAnd0On3("000"));
        assertTrue(Task7.atLeast3CharsAnd0On3("110111"));
    }

    @Test
    void testEndStartSameFalse() {
        assertFalse(Task7.endStartSame(""));

        assertFalse(Task7.endStartSame("011"));
        assertFalse(Task7.endStartSame("010101"));
        assertFalse(Task7.endStartSame("101010"));
    }

    @Test
    void testEndStartSameTrue() {
        assertTrue(Task7.endStartSame("0"));
        assertTrue(Task7.endStartSame("1"));

        assertTrue(Task7.endStartSame("101"));
        assertTrue(Task7.endStartSame("010"));
    }

    @Test
    void testLenBetween1And3False() {
        assertFalse(Task7.lenBetween1And3(""));
        assertFalse(Task7.lenBetween1And3("0000"));
        assertFalse(Task7.lenBetween1And3("010101001010"));
    }

    @Test
    void testLenBetween1And3True() {
        assertTrue(Task7.lenBetween1And3("0"));
        assertTrue(Task7.lenBetween1And3("1"));
        assertTrue(Task7.lenBetween1And3("10"));
        assertTrue(Task7.lenBetween1And3("111"));
        assertTrue(Task7.lenBetween1And3("010"));
    }
}
