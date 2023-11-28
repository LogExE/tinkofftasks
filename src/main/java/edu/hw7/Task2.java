package edu.hw7;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    public static long parFactorial(int n) {
        List<Long> range = new ArrayList<>(n);
        for (int i = 1; i <= n; ++i) {
            range.add((long) i);
        }
        return range.parallelStream().reduce(1L, Math::multiplyExact);
    }
}
