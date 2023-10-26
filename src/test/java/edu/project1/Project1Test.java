package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Project1Test {
    @Test
    void testRightGuess() {
        HangmanGameSession session = new HangmanGameSession("pizza", 3);

        GuessResult res = session.guess('p');

        assertThat(res).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    void testWrongGuess() {
        HangmanGameSession session = new HangmanGameSession("melon", 100);

        GuessResult res = session.guess('p');

        assertThat(res).isInstanceOf(GuessResult.FailedGuess.class);
    }

    @Test
    void testWin() {
        HangmanGameSession session = new HangmanGameSession("ball", 1);
        GuessResult[] guessResults = new GuessResult[3];

        int n = 0;
        for (char x : new char[] {'b', 'a'}) {
            guessResults[n++] = session.guess(x);
        }
        guessResults[n] = session.guess('l');

        for (int i = 0; i < n; ++i) {
            assertThat(guessResults[i]).isInstanceOf(GuessResult.SuccessfulGuess.class);
        }
        assertThat(guessResults[n]).isInstanceOf(GuessResult.GameWin.class);
    }

    @Test
    void testLose() {
        HangmanGameSession session = new HangmanGameSession("fun", 3);

        for (char x : new char[] {'b', 'a'}) {
            session.guess(x);
        }
        GuessResult finalRes = session.guess('l');

        assertThat(finalRes).isInstanceOf(GuessResult.GameOver.class);
    }

    @Test
    void testRepeatingLose() {
        HangmanGameSession session = new HangmanGameSession("ox", 3);
        GuessResult[] guessResults = new GuessResult[3];

        for (char x : new char[] {'a', 'o', 'b'}) {
            session.guess(x);
        }
        int lostN = 0;
        for (char x : new char[] {'c', 'd', 'x'}) {
            guessResults[lostN++] = session.guess(x);
        }

        for (int i = 0; i < lostN; ++i) {
            assertThat(guessResults[i]).isInstanceOf(GuessResult.GameOver.class);
        }
    }

    @Test
    void testGiveUp() {
        HangmanGameSession session = new HangmanGameSession("programming", 42);

        GuessResult res = session.giveUp();

        assertThat(res).isInstanceOf(GuessResult.GivenUp.class);
    }
}
