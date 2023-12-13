package edu.hw9;

import edu.hw9.task1.ProducedStats;
import edu.hw9.task1.StatsCollector;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    void test() throws InterruptedException {
        StatsCollector sc = new StatsCollector();
        Thread t1 = new Thread(() -> sc.push("t1", new double[] {1, 2, 3, 4}));
        Thread t2 = new Thread(() -> sc.push("t2", new double[] {-1, -2, -3, -4}));
        Thread t3 = new Thread(() -> sc.push("t3", new double[] {0, 1, 0}));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        Map<String, ProducedStats> expected = Map.of(
            "t1", new ProducedStats(1, 4, 2.5, 10),
            "t2", new ProducedStats(-4, -1, -2.5, -10),
            "t3", new ProducedStats(0, 1, 1.0 / 3, 1)
        );
        assertEquals(expected, sc.stats());
    }

    @Test
    void testEmpty() {
        StatsCollector sc = new StatsCollector();
        Throwable th = catchThrowable(() -> sc.push("test", new double[] {}));
        assertThat(th).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("empty");
    }
}
