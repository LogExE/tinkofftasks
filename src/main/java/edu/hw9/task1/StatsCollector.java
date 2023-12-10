package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    ConcurrentHashMap<String, ProducedStats> metrics;

    public StatsCollector() {
        metrics = new ConcurrentHashMap<>();
    }

    public void push(String metricName, double[] vals) {
        if (vals.length == 0) {
            throw new IllegalArgumentException("Array should not be empty!");
        }
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
        }).start();
    }

    public Map<String, ProducedStats> stats() {
        return Map.copyOf(metrics);
    }
}
