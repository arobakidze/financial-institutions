package accounts;

import interfaces.Auditable;
import loans.Loan;
import transactions.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanAccount extends Account implements Auditable {

    private Loan loan;
    private LocalDate lastAuditDate;

    public LoanAccount(String owner, BigDecimal balance, Transaction[] transactions, Card card, Loan loan) {
        super(owner, balance, transactions, card);
        this.loan = loan;
        this.lastAuditDate = LocalDate.now();
    }

    @Override
    public String getAccountType() {
        return "Loan Account";
    }

    @Override
    public void audit() {
        System.out.println("Auditing loan account for: " + getOwner());
        this.lastAuditDate = LocalDate.now();
    }

    @Override
    public LocalDate getLastAuditDate() {
        return lastAuditDate;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

}