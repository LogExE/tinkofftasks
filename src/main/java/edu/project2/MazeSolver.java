package edu.project2;

import java.util.List;
import java.util.Optional;

public interface MazeSolver {
    Optional<List<MazeCoords>> solve(Maze maze, MazeCoords start, MazeCoords target);
}
