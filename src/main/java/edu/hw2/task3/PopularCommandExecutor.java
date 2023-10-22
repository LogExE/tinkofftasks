package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    private final static Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        Throwable cause = null;
        try (Connection con = manager.getConnection()) {
            boolean executed = false;
            for (int i = 0; i < maxAttempts; ++i) {
                try {
                    con.execute(command);
                    executed = true;
                    break;
                } catch (ConnectionException conEx) {
                    LOGGER.info("Failed to execute command...");
                    cause = conEx;
                }
            }
            if (!executed) {
                ConnectionException ex = new ConnectionException();
                ex.initCause(cause);
                throw ex;
            }
        } catch (Exception ex) {
            LOGGER.error("Connection couldn't be closed...");
        }
    }
}
