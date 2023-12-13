package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class BigDirectoriesSearch extends RecursiveTask<List<Path>> {
    private final Path path;

    private final static int BIG = 1000;

    public BigDirectoriesSearch(Path path) {
        this.path = path;
    }

    @Override
    protected List<Path> compute() {
        File[] subdirs = path.toFile().listFiles(File::isDirectory);
        File[] files = path.toFile().listFiles(File::isFile);
        if (subdirs == null || files == null) {
            return List.of();
        }
        Collection<BigDirectoriesSearch> col =
            ForkJoinTask.invokeAll(Arrays.stream(subdirs)
                .map(x -> new BigDirectoriesSearch(x.toPath()))
                .toList());
        Stream<Path> st = col.stream().flatMap(x -> x.join().stream());
        if (files.length > BIG) {
            st = Stream.concat(st, Stream.of(path));
        }
        return st.toList();
    }
}
