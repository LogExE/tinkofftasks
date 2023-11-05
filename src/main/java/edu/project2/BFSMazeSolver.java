package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class BFSMazeSolver implements MazeSolver {
    @Override public Optional<List<MazeCoords>> solve(Maze maze, MazeCoords start, MazeCoords target) {
        Map<MazeCoords, MazeCoords> pred = new HashMap<>();
        Set<MazeCoords> visited = new HashSet<>();
        Queue<MazeCoords> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            MazeCoords cur = q.poll();
            if (maze.cells()[cur.row()][cur.col()] == Maze.Cell.WALL) {
                continue;
            }
            visited.add(cur);
            List<MazeCoords> nextCoords = getNextCoords(maze, cur);
            for (MazeCoords next : nextCoords) {
                if (!visited.contains(next)) {
                    q.add(next);
                    pred.put(next, cur);
                }
            }
        }
        if (!pred.containsKey(target)) {
            return Optional.empty();
        }
        MazeCoords cur = target;
        List<MazeCoords> res = new ArrayList<>();
        while (cur != null) {
            res.add(cur);
            cur = pred.get(cur);
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
