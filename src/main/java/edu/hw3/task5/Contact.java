package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(String name, String surname) implements Comparable<Contact> {
    @Override
    public int compareTo(@NotNull Contact x) {
        String ours = surname != null ? surname : name;
        String theirs = x.surname != null ? x.surname : x.name;
        return ours.compareTo(theirs);
    }
}
