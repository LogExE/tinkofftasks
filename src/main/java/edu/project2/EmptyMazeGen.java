package edu.project2;

import java.util.Arrays;

public class EmptyMazeGen implements MazeGen {
    @Override
    public Maze generate(int height, int width) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Future empty maze sizes were invalid! (must be positive in both dims)");
        }
        Maze.Cell[][] cells = new Maze.Cell[height][];
        for (int i = 0; i < height; ++i) {
            cells[i] = new Maze.Cell[width];
            Arrays.fill(cells[i], Maze.Cell.PASSAGE);
        }
        return new Maze(height, width, cells);
    }
}
