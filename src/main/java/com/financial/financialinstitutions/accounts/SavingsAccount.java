package com.financial.financialinstitutions.accounts;

import com.financial.financialinstitutions.exceptions.InsufficientFundsException;
import com.financial.financialinstitutions.interfaces.Transferable;
import com.financial.financialinstitutions.transactions.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class SavingsAccount extends Account implements Transferable {

    private static final BigDecimal TRANSFER_LIMIT = new BigDecimal("10000.00");
    private BigDecimal interestRate;

    public SavingsAccount(String owner, BigDecimal balance, List<Transaction> transactions, Card card, BigDecimal interestRate) {
        super(owner, balance, transactions, card);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public void transfer(BigDecimal amount, String toAccount) {
        if (amount.compareTo(getBalance()) > 0) {
            throw new InsufficientFundsException("Insufficient funds. Balance: " + getBalance() + ", Requested: " + amount);
        }
        if (amount.compareTo(TRANSFER_LIMIT) > 0) {
            throw new InsufficientFundsException("Transfer exceeds limit of " + TRANSFER_LIMIT);
        }
        System.out.println("Transferred " + amount + " from " + getOwner() + "'s savings to account " + toAccount);
    }

    @Override
    public BigDecimal getTransferLimit() {
        return TRANSFER_LIMIT;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}