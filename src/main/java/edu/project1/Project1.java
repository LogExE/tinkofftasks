package edu.project1;

public class Project1 {
    private Project1() {

    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        new HangmanGame(new ITWordsDict(), new ConsoleHangman()).run();
    }
}
