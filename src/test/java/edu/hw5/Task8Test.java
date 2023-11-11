package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    void testBinOddLen() {
        assertTrue(Task8.binaryOddLen("0"));
        assertFalse(Task8.binaryOddLen("10"));
        assertTrue(Task8.binaryOddLen("010"));
        assertFalse(Task8.binaryOddLen("0001"));
    }

    @Test
    void testOdd0Even1() {
        assertTrue(Task8.odd0even1("010"));
        assertTrue(Task8.odd0even1("1101"));

        assertFalse(Task8.odd0even1("01"));
        assertFalse(Task8.odd0even1("11010"));
    }

    @Test
    void testNullsDivs3() {
        assertTrue(Task8.nullsDivs3("0111111100"));
        assertTrue(Task8.nullsDivs3("1000"));
        assertTrue(Task8.nullsDivs3("111"));
        assertTrue(Task8.nullsDivs3(""));

        assertFalse(Task8.nullsDivs3("1010"));
        assertFalse(Task8.nullsDivs3("010"));
        assertFalse(Task8.nullsDivs3("111111111111111110"));
    }

    @Test
    void testNoRepOnes() {
        assertTrue(Task8.noRepOnes("00000000000000"));
        assertTrue(Task8.noRepOnes("1010000"));
        assertTrue(Task8.noRepOnes("010101"));

        assertFalse(Task8.noRepOnes("11"));
        assertFalse(Task8.noRepOnes("10110"));
        assertFalse(Task8.noRepOnes("0101011"));
        assertFalse(Task8.noRepOnes("111111111111"));
    }
}
