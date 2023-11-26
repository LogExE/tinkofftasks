package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    static Arguments[] dummyPars() {
        return new Arguments[10];
    }

    @ParameterizedTest
    @MethodSource("dummyPars")
    void testMultipleTimes() {
        assertEquals(300, Task1.getHundredsByThreads(3));
    }
}
