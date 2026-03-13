package accounts;

import transactions.Transaction;

import java.math.BigDecimal;

public class Account {

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