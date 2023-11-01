package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @Test
    void testExample() {
        TreeMap<String, String> tree = new TreeMap<>(Task7.comparatorWithNull());
        tree.put(null, "test");

        assertTrue(tree.containsKey(null));
    }
}
