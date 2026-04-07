package com.financial.financialinstitutions.loans;

import com.financial.financialinstitutions.base.FinancialProduct;
import com.financial.financialinstitutions.enums.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan extends FinancialProduct {

    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private LocalDate startDate;
    private int durationMonths;
    private LoanStatus loanStatus;

    public Loan(BigDecimal loanAmount, BigDecimal interestRate, LocalDate startDate, int durationMonths) {
        super("Loan");
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.durationMonths = durationMonths;
        this.loanStatus = LoanStatus.PENDING;
    }

    @Override
    public String getProductType() {
        return "Loan";
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

}