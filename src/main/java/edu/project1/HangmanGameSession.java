package edu.project1;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class HangmanGameSession {
    private final String hiddenWord;
    private final char[] wordState;
    private final int missesTotal;
    private int missesLeft;
    private int guessedCount;

    private boolean givenUp;

    public HangmanGameSession(String word, int misses) {
        hiddenWord = word;
        wordState = "*".repeat(hiddenWord.length()).toCharArray();
        missesTotal = misses;
        missesLeft = missesTotal;
        guessedCount = 0;
        givenUp = false;
    }

    boolean wordGuessed() {
        return guessedCount == wordState.length;
    }

    boolean outOfMisses() {
        return missesLeft == 0;
    }

    public boolean finished() {
        return wordGuessed() || outOfMisses() || givenUp;
    }

    @NotNull GuessResult guess(char c) {
        if (finished()) {
            return Objects.requireNonNull(endOutcome());
        }
        boolean guessed = false;
        for (int i = 0; i < wordState.length; ++i) {
            if (wordState[i] == '*' && hiddenWord.charAt(i) == c) {
                wordState[i] = c;
                ++guessedCount;
                guessed = true;
            }
        }
        if (guessed) {
            return guessedCount == hiddenWord.length() ? new GuessResult.GameWin(wordState)
                : new GuessResult.SuccessfulGuess(wordState, c);
        }
        --missesLeft;
        return missesLeft == 0 ? new GuessResult.GameOver(wordState)
            : new GuessResult.FailedGuess(wordState, missesTotal, missesLeft);
    }

    @NotNull
    public GuessResult giveUp() {
        if (finished()) {
            return Objects.requireNonNull(endOutcome());
        }
        givenUp = true;
        return new GuessResult.GivenUp(wordState, hiddenWord);
    }

    private GuessResult endOutcome() {
        if (outOfMisses()) {
            return new GuessResult.GameOver(wordState);
        }
        if (wordGuessed()) {
            return new GuessResult.GameWin(wordState);
        }
        if (givenUp) {
            return new GuessResult.GivenUp(wordState, hiddenWord);
        }
        return null;
    }
}
