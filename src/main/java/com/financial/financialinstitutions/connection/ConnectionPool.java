package com.financial.financialinstitutions.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private final BlockingQueue<Connection> pool;

    private ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            pool.offer(new Connection(i));
        }
        logger.info("ConnectionPool initialized with {} connections.", size);
    }

    public static ConnectionPool getInstance(int size) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(size);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        logger.info("[{}] waiting for connection...", Thread.currentThread().getName());
        Connection connection = pool.take();
        logger.info("[{}] acquired {}", Thread.currentThread().getName(), connection);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
        logger.info("[{}] released {}", Thread.currentThread().getName(), connection);
    }

}