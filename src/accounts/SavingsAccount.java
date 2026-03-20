package accounts;

import interfaces.Transferable;
import transactions.Transaction;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements Transferable {

    private static final BigDecimal TRANSFER_LIMIT = new BigDecimal("10000.00");
    private BigDecimal interestRate;

    public SavingsAccount(String owner, BigDecimal balance, Transaction[] transactions, Card card, BigDecimal interestRate) {
        super(owner, balance, transactions, card);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public void transfer(BigDecimal amount, String toAccount) {
        if (amount.compareTo(TRANSFER_LIMIT) > 0) {
            System.out.println("Transfer failed - exceeds limit of " + TRANSFER_LIMIT);
        } else {
            System.out.println("Transferred " + amount + " from " + getOwner() + "'s savings to account " + toAccount);
        }
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