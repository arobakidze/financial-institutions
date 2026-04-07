package com.financial.financialinstitutions.accounts;

import com.financial.financialinstitutions.enums.AccountTier;
import com.financial.financialinstitutions.transactions.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class Account {

    private String owner;
    private BigDecimal balance;
    private List<Transaction> transactions;
    private Card card;
    private AccountTier accountTier;

    public Account(String owner, BigDecimal balance, List<Transaction> transactions, Card card) {
        this.owner = owner;
        this.balance = balance;
        this.transactions = transactions;
        this.card = card;
        this.accountTier = AccountTier.BASIC;
    }

    public abstract String getAccountType();

    protected String getAccountSummary() {
        return "Owner: " + owner + ", Balance: " + balance + ", Type: " + getAccountType() + ", Tier: " + accountTier;
    }

    @Override
    public String toString() {
        return "Account{owner='" + owner + "', balance=" + balance + ", type='" + getAccountType() + "', tier=" + accountTier + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(owner, account.owner) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, balance);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public AccountTier getAccountTier() {
        return accountTier;
    }

    public void setAccountTier(AccountTier accountTier) {
        this.accountTier = accountTier;
    }

}