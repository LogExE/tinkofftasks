package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class Task2 {
    private Task2() {

    }

    public static void main(String[] args) {
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            Path path = Path.of("/home");
            System.out.println("Searching " + path);
            System.out.println(pool.invoke(new BigDirectoriesSearch(path)));
            System.out.println();

            String ext = ".txt";
            Predicate<File> pathPred = f -> f.getPath().endsWith(ext);
            System.out.println("Searching extension \"" + ext + "\" in " + path);
            System.out.println(pool.invoke(new FilePredicateSearch(path, pathPred)));
            System.out.println();

            int bytesMax = 2;
            Predicate<File> sizePred = f -> f.length() <= bytesMax;
            System.out.println("Searching files with size not greater than " + bytesMax + " bytes");
            System.out.println(pool.invoke(new FilePredicateSearch(path, sizePred)));
        }
    }
}
