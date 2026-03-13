package accounts;

import transactions.Transaction;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private BigDecimal interestRate;

    public SavingsAccount(String owner, BigDecimal balance, Transaction[] transactions, Card card, BigDecimal interestRate) {
        super(owner, balance, transactions, card);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}