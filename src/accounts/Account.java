package accounts;

import transactions.Transaction;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account {

    private String owner;
    private BigDecimal balance;
    private Transaction[] transactions;
    private Card card;

    public Account(String owner, BigDecimal balance, Transaction[] transactions, Card card) {
        this.owner = owner;
        this.balance = balance;
        this.transactions = transactions;
        this.card = card;
    }

    public abstract String getAccountType();

    protected String getAccountSummary() {
        return "Owner: " + owner + ", Balance: " + balance + ", Type: " + getAccountType();
    }

    @Override
    public String toString() {
        return "Account{owner='" + owner + "', balance=" + balance + ", type='" + getAccountType() + "'}";
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

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}