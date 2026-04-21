package com.financial.financialinstitutions.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger logger = LogManager.getLogger(Connection.class);
    private final int connectionId;

    public Connection(int connectionId) {
        this.connectionId = connectionId;
    }

    public void create(String data) {
        logger.info("[Connection-{}] CREATE: {}", connectionId, data);
    }

    public String get(int id) {
        logger.info("[Connection-{}] GET: account with id {}", connectionId, id);
        return "Account-" + id;
    }

    public void update(int id, String data) {
        logger.info("[Connection-{}] UPDATE: account {} with data {}", connectionId, id, data);
    }

    public void delete(int id) {
        logger.info("[Connection-{}] DELETE: account with id {}", connectionId, id);
    }

    public int getConnectionId() {
        return connectionId;
    }

    @Override
    public String toString() {
        return "Connection{id=" + connectionId + "}";
    }

}