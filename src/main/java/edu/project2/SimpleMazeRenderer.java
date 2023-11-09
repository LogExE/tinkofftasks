package edu.project2;

import java.util.List;
import java.util.Map;

public class SimpleMazeRenderer implements MazeRenderer {
    private static final Map<Maze.Cell, Character> CELL_REPR = Map.of(
        Maze.Cell.PASSAGE, ' ',
        Maze.Cell.WALL, '#'
    );

    private static final char PATH_MARK = '*';

    private char[][] getMazeMatrix(Maze maze) {
        char[][] res = new char[maze.height()][];
        for (int i = 0; i < maze.height(); ++i) {
            res[i] = new char[maze.width()];
            for (int j = 0; j < maze.width(); ++j) {
                res[i][j] = CELL_REPR.get(maze.cells()[i][j]);
            }
        }
        return res;
    }

    private String mazeMatrixGlue(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append("-".repeat(matrix[0].length));
        sb.append("@");
        for (char[] row : matrix) {
            sb.append('\n');
            sb.append('|');
            sb.append(new String(row));
            sb.append('|');
        }
        sb.append('\n');
        sb.append("@");
        sb.append("-".repeat(matrix[0].length));
        sb.append("@");
        return sb.toString();
    }

    @Override
    public String render(Maze maze) {
        return mazeMatrixGlue(getMazeMatrix(maze));
    }

    @Override
    public String render(Maze maze, List<MazeCoords> path) {
        char[][] matrix = getMazeMatrix(maze);
        for (MazeCoords coord : path) {
            if (matrix[coord.row()][coord.col()] != CELL_REPR.get(Maze.Cell.WALL)) {
                matrix[coord.row()][coord.col()] = PATH_MARK;
            } else {
                throw new IllegalArgumentException("Path was passing through walls!");
            }
        }
        return mazeMatrixGlue(matrix);
    }
}
