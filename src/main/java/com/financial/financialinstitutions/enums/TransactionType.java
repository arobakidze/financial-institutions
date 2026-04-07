package com.financial.financialinstitutions.enums;

import java.math.BigDecimal;

public enum TransactionType {

    DEPOSIT("Credit to account", 0.0) {
        @Override
        public String getOperationDescription() {
            return "Money deposited into account.";
        }
    },
    WITHDRAWAL("Debit from account", 0.5) {
        @Override
        public String getOperationDescription() {
            return "Money withdrawn from account.";
        }
    },
    TRANSFER("Between accounts", 1.0) {
        @Override
        public String getOperationDescription() {
            return "Money transferred between accounts.";
        }
    };

    private final String description;
    private final double feePercentage;

    static {
        System.out.println("TransactionType enum loaded.");
    }

    TransactionType(String description, double feePercentage) {
        this.description = description;
        this.feePercentage = feePercentage;
    }

    public abstract String getOperationDescription();

    public BigDecimal calculateFee(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(feePercentage / 100));
    }

    public String getDescription() {
        return description;
    }

    public double getFeePercentage() {
        return feePercentage;
    }

}