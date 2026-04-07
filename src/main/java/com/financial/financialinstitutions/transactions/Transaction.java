package com.financial.financialinstitutions.transactions;

import com.financial.financialinstitutions.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private BigDecimal transactionAmount;
    private TransactionType transactionType;
    private LocalDate transactionDate;

    public Transaction(BigDecimal transactionAmount, TransactionType transactionType, LocalDate transactionDate) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{amount=" + transactionAmount + ", type=" + transactionType + ", date=" + transactionDate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionAmount, that.transactionAmount) &&
                transactionType == that.transactionType &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionAmount, transactionType, transactionDate);
    }

    public BigDecimal getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(BigDecimal transactionAmount) { this.transactionAmount = transactionAmount; }
    public TransactionType getTransactionType() { return transactionType; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

}