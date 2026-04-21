package com.financial.financialinstitutions.threads;

import com.financial.financialinstitutions.connection.Connection;
import com.financial.financialinstitutions.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDemo {

    private static final Logger logger = LogManager.getLogger(ThreadDemo.class);


    public static Runnable createRunnableThread() {
        return () -> {
            logger.info("Runnable thread started: {}", Thread.currentThread().getName());
            ConnectionPool pool = ConnectionPool.getInstance(5);
            Connection conn = null;
            try {
                conn = pool.getConnection();
                conn.create("Runnable thread account data");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Runnable thread interrupted: {}", e.getMessage());
            } finally {
                if (conn != null) {
                    pool.releaseConnection(conn);
                }
            }
            logger.info("Runnable thread finished: {}", Thread.currentThread().getName());
        };
    }


    public static class CustomThread extends Thread {

        public CustomThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Logger log = LogManager.getLogger(CustomThread.class);
            log.info("Custom Thread started: {}", getName());
            ConnectionPool pool = ConnectionPool.getInstance(5);
            Connection conn = null;
            try {
                conn = pool.getConnection();
                String result = conn.get(42);
                log.info("Custom Thread got: {}", result);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Custom Thread interrupted: {}", e.getMessage());
            } finally {
                if (conn != null) {
                    pool.releaseConnection(conn);
                }
            }
            log.info("Custom Thread finished: {}", getName());
        }

    }

}