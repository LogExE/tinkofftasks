package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyFixedThreadPool implements ThreadPool {
    private Thread[] threads;
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public static ThreadPool create(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Threads count must be positive!");
        }
        MyFixedThreadPool pool = new MyFixedThreadPool();
        pool.threads = new Thread[n];
        return pool;
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        tasks.take().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            threads[i].start();
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
}
