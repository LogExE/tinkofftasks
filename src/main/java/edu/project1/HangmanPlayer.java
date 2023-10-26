package edu.project1;

public interface HangmanPlayer {
    GuessResult tryGuess(HangmanGameSession session);

    void evaluateState(GuessResult guessResult);
}
