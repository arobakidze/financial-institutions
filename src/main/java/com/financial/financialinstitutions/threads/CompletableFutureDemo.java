package com.financial.financialinstitutions.threads;

import com.financial.financialinstitutions.connection.Connection;
import com.financial.financialinstitutions.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    private static final Logger logger = LogManager.getLogger(CompletableFutureDemo.class);

    public static void runAll() {

        ConnectionPool pool = ConnectionPool.getInstance(5);

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            logger.info("CF1: Running async audit task...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("CF1: Audit task complete.");
        });

        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("CF2: Fetching account balance...");
                    return new BigDecimal("5000.00");
                })
                .thenApply(balance -> {
                    logger.info("CF2: Applying interest to balance: {}", balance);
                    return balance.multiply(new BigDecimal("1.05")).toString();
                })
                .thenApply(result -> {
                    logger.info("CF2: Final balance after interest: {}", result);
                    return "Balance with interest: " + result;
                });

        CompletableFuture<Void> future3 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("CF3: Loading customer data...");
                    return "Avtandili Robakidze";
                })
                .thenAccept(name -> logger.info("CF3: Sending welcome email to: {}", name));

        CompletableFuture<String> future4 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("CF4: Getting institution name...");
                    return "TBC Bank";
                })
                .thenCompose(name -> CompletableFuture.supplyAsync(() -> {
                    logger.info("CF4: Generating report for: {}", name);
                    return "Report generated for " + name;
                }));

        CompletableFuture<String> future5a = CompletableFuture.supplyAsync(() -> {
            logger.info("CF5a: Getting customer name...");
            return "Elene Maisuradze";
        });

        CompletableFuture<String> future5b = CompletableFuture.supplyAsync(() -> {
            logger.info("CF5b: Getting account type...");
            return "Savings Account";
        });

        CompletableFuture<String> future5 = future5a.thenCombine(future5b,
                (name, accountType) -> {
                    logger.info("CF5: Combining results...");
                    return name + " owns a " + accountType;
                });

        CompletableFuture.allOf(future1, future2, future3, future4, future5).join();

        try {
            logger.info("CF2 result: {}", future2.get());
            logger.info("CF4 result: {}", future4.get());
            logger.info("CF5 result: {}", future5.get());
        } catch (Exception e) {
            logger.error("CompletableFuture error: {}", e.getMessage());
        }

    }

}