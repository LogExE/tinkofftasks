package edu.project2;

public record MazeCoords(int row, int col) {
    public MazeCoords {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Maze coordinates must be non-negative!");
        }
    }
}
