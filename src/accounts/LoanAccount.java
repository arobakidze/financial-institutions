package accounts;

import loans.Loan;
import transactions.Transaction;

import java.math.BigDecimal;

public class LoanAccount extends Account {

    private Loan loan;

    public LoanAccount(String owner, BigDecimal balance, Transaction[] transactions, Card card, Loan loan) {
        super(owner, balance, transactions, card);
        this.loan = loan;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

}