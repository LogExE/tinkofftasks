package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

public class BFSParMazeSolver implements MazeSolver {
    private static final int THREADS = 12;

    @Override public Optional<List<MazeCoords>> solve(Maze maze, MazeCoords start, MazeCoords target) {
        if (!maze.checkCoordsValid(start) || !maze.checkCoordsValid(target)) {
            throw new IllegalArgumentException("Some of coordinates were invalid!");
        }
        Map<MazeCoords, MazeCoords> pred = new ConcurrentHashMap<>();
        pred.put(start, start);
        Queue<MazeCoords> q = new ConcurrentLinkedQueue<>();
        q.add(start);
        try (ExecutorService exec = Executors.newFixedThreadPool(THREADS)) {
            List<Future<?>> tasks = new ArrayList<>();
            while (!q.isEmpty()) {
                ConcurrentLinkedQueue<MazeCoords> nextq = new ConcurrentLinkedQueue<>();
                for (MazeCoords cur : q) {
                    tasks.add(exec.submit(() -> {
                        List<MazeCoords> nextCoords = getNextCoords(maze, cur);
                        for (MazeCoords next : nextCoords) {
                            if (maze.cells()[next.row()][next.col()] == Maze.Cell.WALL || pred.containsKey(next)) {
                                continue;
                            }
                            pred.put(next, cur);
                            nextq.add(next);
                        }
                    }));
                }
                for (Future<?> task : tasks) {
                    try {
                        task.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
                q = nextq;
            }
        }
        if (!pred.containsKey(target)) {
            return Optional.empty();
        }
        MazeCoords cur = target;
        List<MazeCoords> res = new ArrayList<>();
        while (true) {
            res.add(cur);
            MazeCoords tmp = cur;
            cur = pred.get(cur);
            if (tmp == cur) {
                break;
            }
        }
        return Optional.of(res.reversed());
    }

    @SuppressWarnings("MagicNumber")
    @NotNull private static List<MazeCoords> getNextCoords(Maze maze, MazeCoords cur) {
        List<MazeCoords> nextCoords = new ArrayList<>(4);
        if (cur.row() > 0) {
            nextCoords.add(new MazeCoords(cur.row() - 1, cur.col()));
        }
        if (cur.col() > 0) {
            nextCoords.add(new MazeCoords(cur.row(), cur.col() - 1));
        }
        if (cur.row() < maze.height() - 1) {
            nextCoords.add(new MazeCoords(cur.row() + 1, cur.col()));
        }
        if (cur.col() < maze.width() - 1) {
            nextCoords.add(new MazeCoords(cur.row(), cur.col() + 1));
        }
        return nextCoords;
    }
}
