package edu.project2;

import java.util.List;

public interface MazeRenderer {
    String render(Maze maze);

    String render(Maze maze, List<MazeCoords> path);
}
