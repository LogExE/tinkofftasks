package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHangman implements HangmanPlayer {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public GuessResult tryGuess(HangmanGameSession session) {
        System.out.println("Guess the letter:");
        System.out.print("> ");
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (line == null) {
            return session.giveUp();
        } else if (line.length() != 1) {
            System.out.println("Expected 1 character, got " + line.length() + " instead");
            return tryGuess(session);
        }
        return session.guess(line.charAt(0));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    @Override
    public void evaluateState(GuessResult guessResult) {
        System.out.println(guessResult.message());
        System.out.println("Guess state: " + String.valueOf(guessResult.guessState()));
    }
}
