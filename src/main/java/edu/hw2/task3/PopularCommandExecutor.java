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
        try {
            tryExecute("apt update && apt upgrade -y");
        } catch (ConnectionException conEx) {
            int exCount = 1 + conEx.getSuppressed().length;
            if (exCount < maxAttempts) {
                LOGGER.warn("During package updates " + exCount + " exceptions occurred");
            } else {
                LOGGER.error("Max attempts reached during package updates!");
            }
        }
    }

    void tryExecute(String command) {
        ConnectionException accumEx = null;
        try (Connection con = manager.getConnection()) {
            for (int i = 0; i < maxAttempts; ++i) {
                try {
                    con.execute(command);
                    break;
                } catch (ConnectionException conEx) {
                    LOGGER.warn(conEx.getMessage());
                    if (accumEx == null) {
                        accumEx = conEx;
                    } else {
                        accumEx.addSuppressed(conEx);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Connection couldn't be closed...");
        }
        if (accumEx != null) {
            throw accumEx;
        }
    }
}
