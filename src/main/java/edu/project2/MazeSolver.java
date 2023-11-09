package edu.project2;

import java.util.List;
import java.util.Optional;

public interface MazeSolver {
    // результат должен включать исходную и конечную точку
    Optional<List<MazeCoords>> solve(Maze maze, MazeCoords start, MazeCoords target);
}
