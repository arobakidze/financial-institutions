package com.financial.financialinstitutions.summary;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class TransactionSummary {

    private final String owner;
    private final BigDecimal totalAmount;
    private final int transactionCount;

    public TransactionSummary(String owner, BigDecimal totalAmount, int transactionCount) {
        this.owner = owner;
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }

    public BigDecimal averageAmount() {
        if (transactionCount == 0) return BigDecimal.ZERO;
        return totalAmount.divide(BigDecimal.valueOf(transactionCount), 2, RoundingMode.HALF_UP);
    }

    public boolean hasTransactions() {
        return transactionCount > 0;
    }

    public String owner() {
        return owner;
    }

    public BigDecimal totalAmount() {
        return totalAmount;
    }

    public int transactionCount() {
        return transactionCount;
    }

}