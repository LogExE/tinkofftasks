package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    static Arguments[] strings() {
        return new Arguments[] {
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
        };
    }

    @ParameterizedTest
    @MethodSource("strings")
    void testValidStuff(String given, LocalDate expected) {
        assertEquals(expected, Task3.processDate(given).get());
    }
}
