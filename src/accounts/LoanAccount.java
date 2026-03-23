package accounts;

import exceptions.AuditFailedException;
import exceptions.InvalidLoanAmountException;
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
        if (loan.getLoanAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanAmountException("Loan amount must be greater than zero.");
        }
        this.loan = loan;
        this.lastAuditDate = LocalDate.now();
    }

    @Override
    public String getAccountType() {
        return "Loan Account";
    }

    @Override
    public void audit() throws AuditFailedException {
        if (loan == null) {
            throw new AuditFailedException("Audit failed — no loan attached to account for: " + getOwner());
        }
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