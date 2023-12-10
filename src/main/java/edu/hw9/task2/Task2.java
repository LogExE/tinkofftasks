package edu.hw9.task2;

import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class Task2 {
    public static void main(String[] args) {
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            System.out.println(pool.invoke(new BigDirectoriesSearch(Paths.get("/home"))));
        }
    }
}
