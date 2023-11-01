package edu.hw3;

import java.util.Comparator;

public class Task7 {
    private Task7() {

    }

    public static <T extends Comparable<? super T>> Comparator<T> comparatorWithNull() {
        return Comparator.nullsFirst(Comparator.naturalOrder());
    }
}
