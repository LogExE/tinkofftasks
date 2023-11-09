package edu.project2;

public record Maze(int height, int width, Cell[][] cells) {
    public Maze {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Maze sizes were invalid! (must be positive in both dims)");
        }
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

    public boolean checkCoordsValid(MazeCoords coords) {
        return coords.row() >= 0 && coords.row() < height && coords.col() >= 0 && coords.col() < width;
    }
}
