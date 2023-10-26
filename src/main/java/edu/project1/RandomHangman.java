package edu.project1;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomHangman implements HangmanPlayer {
    private final static Logger LOGGER = LogManager.getLogger();
    Random rand = new Random();

    @Override
    public GuessResult tryGuess(HangmanGameSession session) {
        char guess = (char) (rand.nextInt('z' - 'a') + 'a');
        LOGGER.info("Random guess: " + guess);
        return session.guess(guess);
    }

    @Override
    public void evaluateState(GuessResult guessRes) {
        LOGGER.info(guessRes);
    }
}
