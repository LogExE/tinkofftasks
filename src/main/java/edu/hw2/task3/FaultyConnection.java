package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    private final Random rand = new Random();

    @Override
    public void execute(String cmd) {
        boolean failed = rand.nextBoolean();
        if (failed) {
            throw new ConnectionException();
        }
        LOGGER.info("Command " + cmd + " executed successfully!");
    }

    @Override
    public void close() {
        LOGGER.info("FaultyConnection.close()");
    }
}
