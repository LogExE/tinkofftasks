package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    void testExamples() {
        assertEquals(Task4.convertToRoman(2), "II");
        assertEquals(Task4.convertToRoman(12), "XII");
        assertEquals(Task4.convertToRoman(16), "XVI");
    }

    @Test
    void testTooLow() {
        Throwable thr = catchThrowable(() -> Task4.convertToRoman(0));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testTooHigh() {
        Throwable thr = catchThrowable(() -> Task4.convertToRoman(4251));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testOtherVariousCases() {
        assertEquals(Task4.convertToRoman(98), "XCVIII");
        assertEquals(Task4.convertToRoman(120), "CXX");
        assertEquals(Task4.convertToRoman(3999), "MMMCMXCIX");
        assertEquals(Task4.convertToRoman(34), "XXXIV");
    }
}
