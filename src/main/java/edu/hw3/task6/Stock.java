package edu.hw3.task6;

public record Stock(String name, double price) {
    public Stock {
        if (price < 0) {
            throw new IllegalArgumentException("Price of stock can't be negative!");
        }
    }
}
