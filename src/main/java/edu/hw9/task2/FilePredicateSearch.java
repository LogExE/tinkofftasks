package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilePredicateSearch extends RecursiveTask<List<Path>> {
    private final Path path;

    private final Predicate<File> pred;

    public FilePredicateSearch(Path path, Predicate<File> pred) {
        this.path = path;
        this.pred = pred;
    }

    @Override
    protected List<Path> compute() {
        File[] subdirs = path.toFile().listFiles(File::isDirectory);
        File[] files = path.toFile().listFiles(File::isFile);
        if (subdirs == null || files == null) {
            return List.of();
        }
        Collection<FilePredicateSearch> col =
            ForkJoinTask.invokeAll(Arrays.stream(subdirs)
                .map(x -> new FilePredicateSearch(x.toPath(), pred))
                .toList());
        List<Path> local = new ArrayList<>();
        for (File file : files) {
            if (pred.test(file)) {
                local.add(file.toPath());
            }
        }
        Stream<Path> st = col.stream().flatMap(x -> x.join().stream());
        st = Stream.concat(st, local.stream());
        return st.toList();
    }
}
