package com.financial.financialinstitutions.accounts;

import com.financial.financialinstitutions.enums.RiskLevel;
import com.financial.financialinstitutions.exceptions.AuditFailedException;
import com.financial.financialinstitutions.exceptions.InvalidLoanAmountException;
import com.financial.financialinstitutions.interfaces.Auditable;
import com.financial.financialinstitutions.loans.Loan;
import com.financial.financialinstitutions.transactions.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LoanAccount extends Account implements Auditable {

    private static final Logger LOGGER = LogManager.getLogger(LoanAccount.class);

    private Loan loan;
    private LocalDate lastAuditDate;
    private RiskLevel riskLevel;

    public LoanAccount(String owner, BigDecimal balance, List<Transaction> transactions, Card card, Loan loan) {
        super(owner, balance, transactions, card);
        if (loan.getLoanAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanAmountException("Loan amount must be greater than zero.");
        }
        this.loan = loan;
        this.lastAuditDate = LocalDate.now();
        this.riskLevel = RiskLevel.LOW;
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
        LOGGER.info("Auditing loan account for: {}", getOwner());
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

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

}