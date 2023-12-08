package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyFixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public static ThreadPool create(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Threads count must be positive!");
        }
        return new MyFixedThreadPool(n);
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable mustn't be null!");
        }
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private MyFixedThreadPool(int n) {
        threads = new Thread[n];
        for (int i = 0; i < threads.length; ++i) {
            Runnable tasksDo = () -> {
                while (true) {
                    try {
                        tasks.take().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } catch (RuntimeException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            };
            threads[i] = new Thread(tasksDo);
        }
    }
}
