package edu.project2;

import java.util.Arrays;

public class FullyObstructedMazeGen implements MazeGen {
    @Override
    public Maze generate(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Future fully obstructed maze sizes were invalid! (must be positive in both dims)");
        }
        Maze.Cell[][] cells = new Maze.Cell[height][];
        for (int i = 0; i < height; ++i) {
            cells[i] = new Maze.Cell[width];
            // путей не будет...
            Arrays.fill(cells[i], Maze.Cell.WALL);
        }
        return new Maze(width, height, cells);
    }
}
