package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQStockMarket implements StockMarket {
    Comparator<Stock> myComp = Comparator.comparingDouble(Stock::price).reversed();

    PriorityQueue<Stock> stocks = new PriorityQueue<>(myComp);

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
