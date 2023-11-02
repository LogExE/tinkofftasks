package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    void testExamples() {
        assertEquals("II", Task4.convertToRoman(2));
        assertEquals("XII", Task4.convertToRoman(12));
        assertEquals("XVI", Task4.convertToRoman(16));
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
        assertEquals("XCVIII", Task4.convertToRoman(98));
        assertEquals("CXX", Task4.convertToRoman(120));
        assertEquals("MMMCMXCIX", Task4.convertToRoman(3999));
        assertEquals("XXXIV", Task4.convertToRoman(34));
    }
}
