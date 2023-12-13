package edu.project2;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Project2 {
    private Project2() {

    }

    @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        MazeRenderer mr = new SimpleMazeRenderer();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Enter dimensions (width height)");
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        Maze mz = new RandomMazeGen().generate(w, h);
        System.out.println(mr.render(mz));
        System.out.println("Enter start point (row column)");
        int r1 = scanner.nextInt();
        int c1 = scanner.nextInt();
        MazeCoords cor1 = new MazeCoords(r1, c1);
        System.out.println("Enter target point (row column)");
        int r2 = scanner.nextInt();
        int c2 = scanner.nextInt();
        MazeCoords cor2 = new MazeCoords(r2, c2);
        Optional<List<MazeCoords>> path = new BFSParMazeSolver().solve(mz, cor1, cor2);
        if (path.isPresent()) {
            System.out.println("The path:");
            System.out.println(mr.render(mz, path.get()));
        } else {
            System.out.println("No path...");
        }
    }
}
