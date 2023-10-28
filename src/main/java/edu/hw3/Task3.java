package edu.hw3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    private Task3() {

    }

    public static <T> Map<T, Integer> freqDicts(Collection<T> elems) {
        HashMap<T, Integer> res = new HashMap<>();
        for (T elem : elems) {
            res.merge(elem, 1, Integer::sum);
        }
        return res;
    }
}
