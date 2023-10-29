package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class BackwardsIterator<T> implements Iterator<T> {
    Stack<T> stack = new Stack<>();

    public BackwardsIterator(Collection<T> col) {
        for (T el : col) {
            stack.push(el);
        }
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0;
    }

    @Override
    public T next() {
        return stack.pop();
    }
}
