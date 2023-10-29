package edu.hw3;

import edu.hw3.task6.PQStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task6Test {
    @Test
    void testSimple() {
        StockMarket sm = new PQStockMarket();
        sm.add(new Stock("AAPL", 168.22));
        sm.add(new Stock("INTC", 35.54));
        sm.add(new Stock("AMD", 96.43));
        sm.add(new Stock("AMZN", 127.74));
        assertEquals(sm.mostValuableStock(), new Stock("AAPL", 168.22));
    }

    @Test
    void testRemove() {
        StockMarket sm = new PQStockMarket();
        sm.add(new Stock("AAPL", 168.22));
        sm.add(new Stock("INTC", 35.54));
        sm.add(new Stock("AMD", 96.43));
        sm.add(new Stock("AMZN", 127.74));
        sm.remove(new Stock("AAPL", 168.22));
        assertEquals(sm.mostValuableStock(), new Stock("AMZN", 127.74));
    }

    @Test
    void testEmpty() {
        StockMarket sm = new PQStockMarket();
        assertNull(sm.mostValuableStock());
    }

    @Test
    void testNegativeStock() {
        Throwable thr = catchThrowable(() -> new Stock("?", -100));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("negative");
    }
}
