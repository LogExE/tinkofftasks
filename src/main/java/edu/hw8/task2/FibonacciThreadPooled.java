package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciThreadPooled {
    private FibonacciThreadPooled() {

    }

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) {
        try (ThreadPool tp = MyFixedThreadPool.create(4)) {
            tp.start();
            for (int i = 0; i < 42; ++i) {
                int finalI = i;
                tp.execute(() -> LOGGER.info("fib(" + finalI + "): " + fibonize(finalI)));
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int fibonize(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; ++i) {
            int tmp = b;
            b += a;
            a = tmp;
        }
        return a;
    }
}
