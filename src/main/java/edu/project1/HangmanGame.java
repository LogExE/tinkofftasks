package edu.project1;

public class HangmanGame {
    HangmanPlayer player;
    WordsDict dict;

    public HangmanGame(WordsDict dict, HangmanPlayer player) {
        this.dict = dict;
        this.player = player;
    }

    public void run() {
        String word = dict.randomWord();
        int misses = Math.max(1, word.length() / 2);
        HangmanGameSession session = new HangmanGameSession(word, misses);

        while (!session.finished()) {
            GuessResult guessRes = player.tryGuess(session);
            player.evaluateState(guessRes);
        }
    }
}
