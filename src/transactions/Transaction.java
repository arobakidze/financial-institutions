package transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private BigDecimal transactionAmount;
    private String transactionType;
    private LocalDate transactionDate;

    public Transaction(BigDecimal transactionAmount, String transactionType, LocalDate transactionDate) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}