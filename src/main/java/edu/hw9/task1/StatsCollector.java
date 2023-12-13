package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector {
    private final ConcurrentHashMap<String, ProducedStats> metrics;
    private final AtomicInteger working;

    public StatsCollector() {
        metrics = new ConcurrentHashMap<>();
        working = new AtomicInteger(0);
    }

    public void push(String metricName, double[] vals) {
        if (vals.length == 0) {
            throw new IllegalArgumentException("Array should not be empty!");
        }
        working.incrementAndGet();
        new Thread(() -> {
            double max = -Double.MAX_VALUE;
            double min = Double.MAX_VALUE;
            double sum = 0;
            for (double val : vals) {
                max = Math.max(max, val);
                min = Math.min(min, val);
                sum += val;
            }
            double avg = sum / vals.length;
            metrics.put(metricName, new ProducedStats(min, max, avg, sum));
            working.decrementAndGet();
        }).start();
    }

    public Map<String, ProducedStats> stats() {
        while (working.get() != 0) {

        }
        return Map.copyOf(metrics);
    }
}
