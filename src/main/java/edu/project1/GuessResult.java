package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    @NotNull String message();

    char[] guessState();

    record SuccessfulGuess(char[] guessState, char c) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Guessed a letter '" + c + "'!";
        }
    }

    record FailedGuess(char[] guessState, int missesTotal, int missesLeft) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Missed, misses left: " + missesLeft + " out of " + missesTotal;
        }
    }

    record GameWin(char[] guessState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You win!";
        }
    }

    record GameOver(char[] guessState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You lose!";
        }
    }

    record GivenUp(char[] guessState, String hidden) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Given up. Anyway, the answer was \"" + hidden + "\"";
        }
    }
}
