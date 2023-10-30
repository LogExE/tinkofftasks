package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BackwardsIterator<T> implements Iterator<T> {
    ArrayList<T> list;
    int idx;

    public BackwardsIterator(Collection<T> col) {
        list = new ArrayList<>(col);
        idx = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return idx >= 0;
    }

    @Override
    public T next() {
        return list.get(idx--);
    }
}
