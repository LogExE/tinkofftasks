package edu.hw4;

public record ValidationError(String field, String errType) {
    @Override
    public String toString() {
        return "Incorrect field \"" + field + "\": " + errType;
    }
}
