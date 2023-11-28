package edu.hw7;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class Task4 {
    private Task4() {

    }

    @SuppressWarnings("MagicNumber")
    public static double piCalc(int iters) {
        long totalCount = 0;
        long circleCount = 0;
        while (totalCount < iters) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            if (x * x + y * y <= 1) {
                ++circleCount;
            }
            ++totalCount;
        }
        return (4.0 * circleCount) / totalCount;
    }

    private static Runnable getPiRunnable(int iters, AtomicLong total, AtomicLong circle) {
        return () -> {
            long localTotal = 0;
            long localCircle = 0;
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            while (localTotal < iters) {
                double x = rand.nextDouble() * 2 - 1;
                double y = rand.nextDouble() * 2 - 1;
                if (x * x + y * y <= 1) {
                    ++localCircle;
                }
                ++localTotal;
            }
            total.addAndGet(localTotal);
            circle.addAndGet(localCircle);
        };
    }

    @SuppressWarnings("MagicNumber")
    public static double piCalcPar(int iters, int threads) {
        AtomicLong totalCount = new AtomicLong(0);
        AtomicLong circleCount = new AtomicLong(0);

        Thread[] thrs = new Thread[threads];
        int itersForCore = iters / threads;
        for (int i = 0; i < thrs.length; ++i) {
            thrs[i] = new Thread(getPiRunnable(itersForCore, totalCount, circleCount));
            thrs[i].start();
        }
        for (int i = 0; i < thrs.length; ++i) {
            try {
                thrs[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int remainder = iters % threads;
        getPiRunnable(remainder, totalCount, circleCount).run();
        return (4.0 * circleCount.get()) / totalCount.get();
    }

    private static final List<Integer> ITERS_TO_TEST = List.of(10_000_000, 100_000_000, 1_000_000_000);

/*
    public static void main(String[] args) {
        System.out.println("For sequential version");
        System.out.printf("%-11s %-11s %-6s\n", "Value", "Difference", "Time");
        double[] timesSeq = new double[ITERS_TO_TEST.size()];
        for (int i = 0; i < ITERS_TO_TEST.size(); ++i) {
            int iters = ITERS_TO_TEST.get(i);
            long nanos1 = System.nanoTime();
            double res = piCalc(iters);
            long nanos2 = System.nanoTime();
            double secs = (nanos2 - nanos1) / 1e9;
            System.out.printf("%.9f %.9f %.3f\n", res, Math.abs(res - Math.PI), secs);
            timesSeq[i] = secs;
        }

        System.out.println("For parallel version");
        int cores = Runtime.getRuntime().availableProcessors();
        for (int thr = 2; thr <= cores; ++thr) {
            System.out.printf("%d cores:\n", thr);
            for (int i = 0; i < ITERS_TO_TEST.size(); ++i) {
                int iters = ITERS_TO_TEST.get(i);
                long nanos1 = System.nanoTime();
                double res = piCalcPar(iters, thr);
                long nanos2 = System.nanoTime();
                double secs = (nanos2 - nanos1) / 1e9;
                System.out.printf(
                    "%.9f %.9f %.3f speedup: %f\n",
                    res,
                    Math.abs(res - Math.PI),
                    secs,
                    timesSeq[i] / secs
                );
            }
        }
    }
*/
}
