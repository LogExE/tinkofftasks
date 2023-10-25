package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Random rand = new Random();

    @Override
    public Connection getConnection() {
        boolean faulty = rand.nextBoolean();
        if (faulty) {
            LOGGER.info("Will be faulty connection");
            return new FaultyConnection();
        }
        LOGGER.info("Will be stable connection");
        return new StableConnection();
    }
}
