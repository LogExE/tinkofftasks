package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHangman {
    private final WordsDict dict;

    public ConsoleHangman(WordsDict dict) {
        this.dict = dict;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void run() {
        String word = dict.randomWord();
        int misses = Math.max(1, word.length() / 2);
        HangmanGameSession session = new HangmanGameSession(word, misses);
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
            while (!session.finished()) {
                System.out.println("Guess the letter");
                System.out.print("> ");
                String line = reader.readLine();
                try {
                    GuessResult guessResult = tryGuess(session, line);
                    printState(guessResult);
                } catch (RuntimeException ex) {
                    System.out.println("You must provide valid input! " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("IOException thrown!");
        }
    }

    private GuessResult tryGuess(HangmanGameSession session, String line) {
        if (line == null) {
            return session.giveUp();
        } else {
            if (line.length() != 1) {
                throw new RuntimeException("Expected 1 character, got " + line.length() + " instead");
            }
            return session.guess(line.charAt(0));
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printState(GuessResult guessResult) {
        System.out.println(guessResult.message());
        System.out.println("Guess state: " + String.valueOf(guessResult.guessState()));
    }
}
