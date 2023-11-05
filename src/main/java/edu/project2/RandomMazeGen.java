package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

// Алгоритм Олдоса-Бродера
public class RandomMazeGen implements MazeGen {
    Random rand = new Random();

    @Override public Maze generate(int height, int width) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "Future random maze sizes were invalid! (must be positive in both dims)");
        }
        Maze.Cell[][] cells = new Maze.Cell[height][];
        for (int i = 0; i < height; ++i) {
            cells[i] = new Maze.Cell[width];
            Arrays.fill(cells[i], Maze.Cell.WALL);
        }
        // + 1 для округления вверх
        int blocked = ((width + 1) / 2) * ((height + 1) / 2) - 1;
        cells[0][0] = Maze.Cell.PASSAGE;
        MazeCoords current = new MazeCoords(0, 0);
        int lastCol = width % 2 == 0 ? width - 2 : width - 1;
        int lastRow = height % 2 == 0 ? height - 2 : height - 1;
        while (blocked > 0) {
            List<MazeCoords> choices = getNextCoords(current, lastCol, lastRow);
            MazeCoords next = choices.get(rand.nextInt(choices.size()));
            if (cells[next.row()][next.col()] == Maze.Cell.WALL) {
                int dr = next.row() - current.row();
                int dc = next.col() - current.col();
                cells[current.row() + dr / 2][current.col() + dc / 2] = Maze.Cell.PASSAGE;
                cells[next.row()][next.col()] = Maze.Cell.PASSAGE;
                blocked--;
            }
            current = next;
        }
        return new Maze(height, width, cells);
    }

    @SuppressWarnings("MagicNumber") @NotNull
    private static List<MazeCoords> getNextCoords(MazeCoords current, int lastCol, int lastRow) {
        List<MazeCoords> choices = new ArrayList<>(4);
        if (current.col() > 0) {
            choices.add(new MazeCoords(current.row(), current.col() - 2));
        }
        if (current.row() > 0) {
            choices.add(new MazeCoords(current.row() - 2, current.col()));
        }
        if (current.col() < lastCol) {
            choices.add(new MazeCoords(current.row(), current.col() + 2));
        }
        if (current.row() < lastRow) {
            choices.add(new MazeCoords(current.row() + 2, current.col()));
        }
        return choices;
    }
}
