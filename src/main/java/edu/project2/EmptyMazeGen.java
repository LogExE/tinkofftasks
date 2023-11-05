package edu.project2;

import java.util.Arrays;

public class EmptyMazeGen implements MazeGen {
    @Override
    public Maze generate(int width, int height) {
        Maze.Cell[][] cells = new Maze.Cell[height][];
        for (int i = 0; i < height; ++i) {
            cells[i] = new Maze.Cell[width];
            Arrays.fill(cells[i], Maze.Cell.PASSAGE);
        }
        return new Maze(width, height, cells);
    }
}
