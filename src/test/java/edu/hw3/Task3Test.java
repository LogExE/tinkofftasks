package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    static Arguments[] simpleTestParams() {
        return new Arguments[] {
            Arguments.of(
                List.of("a", "bb", "a", "bb"),
                Map.of(
                    "bb", 2,
                    "a", 2
                )
            ),
            Arguments.of(
                List.of("this", "and", "that", "and"),
                Map.of(
                    "this", 1,
                    "that", 1,
                    "and", 2
                )
            ),
            Arguments.of(
                List.of("код", "код", "код", "bug"),
                Map.of(
                    "код", 3,
                    "bug", 1
                )
            ),
            Arguments.of(
                List.of(1, 1, 2, 2),
                Map.of(
                    1, 2,
                    2, 2
                )
            )
        };
    }

    @ParameterizedTest
    @MethodSource("simpleTestParams") <T> void testSimple(Collection<T> arr, Map<T, Integer> res) {
        assertEquals(res, Task3.freqDicts(arr));
    }

    @Test
    void testEmptyCollection() {
        assertEquals(Map.of(), Task3.freqDicts(List.of()));
    }

    @Test
    void testWithNull() {
        Collection<Boolean> given = new ArrayList<>();
        given.add(true);
        given.add(null);
        given.add(null);
        Map<Boolean, Integer> expected = new HashMap<>();
        expected.put(true, 1);
        expected.put(null, 2);
        assertEquals(expected, Task3.freqDicts(given));
    }
}
