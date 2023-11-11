package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void testValidInput() {
        String given = """
            2022-03-12, 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20""";
        String expected = "3ч 40м";
        assertEquals(expected, Task1.meanDuration(given));
    }

    @Test
    void testWrongOrder() {
        String given = """
            2022-03-12, 23:50 - 2022-03-12, 20:20
            2022-04-01, 21:30 - 2022-04-02, 01:20""";
        Throwable thr = catchThrowable(() -> Task1.meanDuration(given));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("order");
    }

    @Test
    void testInvalidDateTime() {
        String given = """
            2022-03-12 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20""";
        Throwable thr = catchThrowable(() -> Task1.meanDuration(given));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("DateTime");
    }

    @Test
    void testInvalidSession() {
        String given = """
            2022-03-12, 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20 - 2022-03-12, 04:20""";
        Throwable thr = catchThrowable(() -> Task1.meanDuration(given));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Session");
    }
}
