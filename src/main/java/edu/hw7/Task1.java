package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {

    }

    @SuppressWarnings("MagicNumber")
    public static int getHundredsByThreads(int hundreds) {
        AtomicInteger cnt = new AtomicInteger();
        Thread[] threads = new Thread[hundreds];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100; ++k) {
                    cnt.incrementAndGet();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return cnt.get();
    }
}
