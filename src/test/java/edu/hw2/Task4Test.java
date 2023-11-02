package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.Task4;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    void testMethod() {
        CallingInfo info = Task4.callingInfo();
        assertEquals("edu.hw2.Task4Test", info.className());
        assertEquals("testMethod", info.methodName());
    }
}
