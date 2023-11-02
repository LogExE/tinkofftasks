package edu.hw3;

import edu.hw3.task8.BackwardsIterator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task8Test {
    @Test
    void testExample() {
        Iterator<Integer> it = new BackwardsIterator<>(List.of(1, 2, 3));
        ArrayList<Integer> al = Lists.newArrayList(it);
        assertEquals(List.of(3, 2, 1), al);
    }
}
