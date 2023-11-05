package edu.project2;

public record Maze(int width, int height, Cell[][] cells) {
    public Maze {
        boolean rightSize = true;
        if (cells.length != height) {
            rightSize = false;
        } else {
            for (int i = 0; i < height; ++i) {
                if (cells[i].length != width) {
                    rightSize = false;
                    break;
                }
            }
        }
        if (!rightSize) {
            throw new IllegalArgumentException("Maze cells dimensions are invalid!");
        }
    }

    public enum Cell {
        PASSAGE,
        WALL
    }
}
