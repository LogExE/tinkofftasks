package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Project2Test {
    @Test
    void testNegativeCoords() {
        Throwable thr = catchThrowable(() -> new MazeCoords(-1, 2));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("non-negative");
    }

    @Test
    void testInvalidMazeDims() {
        Throwable thr = catchThrowable(() -> new Maze(2, 0, new Maze.Cell[][] {{}, {}}));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("positive");
    }

    @Test
    void testInvalidMazeArraySizes() {
        Throwable thr = catchThrowable(() -> new Maze(2, 1, new Maze.Cell[][] {
            {Maze.Cell.WALL, Maze.Cell.WALL},
            {Maze.Cell.WALL}
        }));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("dimensions");
    }

    @Test
    void testRandomGenInvalidParams() {
        Throwable thr = catchThrowable(() -> new RandomMazeGen().generate(-5, -1));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("positive");
    }

    @Test
    void testSimpleRenderWrongPath() {
        MazeRenderer givenRenderer = new SimpleMazeRenderer();
        Maze givenMaze = new FullyObstructedMazeGen().generate(10, 5);
        List<MazeCoords> givenPath = List.of(new MazeCoords(0, 0), new MazeCoords(0, 1));
        Throwable thr = catchThrowable(() -> givenRenderer.render(givenMaze, givenPath));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("walls");
    }

    @Test
    void testBFSEmptyMazePath() {
        Maze givenEmptyMaze = new EmptyMazeGen().generate(5, 3);
        MazeSolver givenMazeSolver = new BFSMazeSolver();
        MazeCoords givenStart = new MazeCoords(0, 0);
        MazeCoords givenTarget = new MazeCoords(4, 2);
        assertEquals(7, givenMazeSolver.solve(givenEmptyMaze, givenStart, givenTarget).map(List::size).orElse(0));
    }

    @Test
    void testBFSFullMazePath() {
        Maze givenEmptyMaze = new FullyObstructedMazeGen().generate(5, 3);
        MazeSolver givenMazeSolver = new BFSMazeSolver();
        MazeCoords givenStart = new MazeCoords(0, 0);
        MazeCoords givenTarget = new MazeCoords(4, 2);
        assertEquals(Optional.empty(), givenMazeSolver.solve(givenEmptyMaze, givenStart, givenTarget));
    }

    @Test
    void testSimpleRenderEmpty() {
        MazeRenderer givenRenderer = new SimpleMazeRenderer();
        Maze givenEmptyMaze = new EmptyMazeGen().generate(2, 6);
        String expected = """
            @------@
            |      |
            |      |
            @------@""";
        assertEquals(expected, givenRenderer.render(givenEmptyMaze));
    }

    @Test
    void testSimpleRenderEmptyWithPath() {
        MazeRenderer givenRenderer = new SimpleMazeRenderer();
        Maze givenEmptyMaze = new EmptyMazeGen().generate(2, 6);
        List<MazeCoords> givenPath = List.of(
            new MazeCoords(0, 0),
            new MazeCoords(1, 0),
            new MazeCoords(1, 1),
            new MazeCoords(1, 2),
            new MazeCoords(1, 3),
            new MazeCoords(1, 4),
            new MazeCoords(1, 5)
        );
        String expected = """
            @------@
            |*     |
            |******|
            @------@""";
        assertEquals(expected, givenRenderer.render(givenEmptyMaze, givenPath));
    }

    @Test
    void testSimpleRenderFull() {
        MazeRenderer givenRenderer = new SimpleMazeRenderer();
        Maze givenEmptyMaze = new FullyObstructedMazeGen().generate(4, 6);
        String expected = """
            @------@
            |######|
            |######|
            |######|
            |######|
            @------@""";
        assertEquals(expected, givenRenderer.render(givenEmptyMaze));
    }
}
