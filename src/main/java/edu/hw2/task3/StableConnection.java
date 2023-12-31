package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String cmd) {
        LOGGER.info("Command " + cmd + " executed successfully!");
    }

    @Override
    public void close() {
        LOGGER.info("StableConnection.close()");
    }
}
