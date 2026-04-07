package com.financial.financialinstitutions.enums;

public enum LoanStatus {

    PENDING("Awaiting review", 1),
    APPROVED("Loan approved", 2),
    REJECTED("Loan rejected", 3),
    CLOSED("Loan fully repaid", 4);

    private final String description;
    private final int statusCode;

    static {
        System.out.println("LoanStatus enum loaded.");
    }

    LoanStatus(String description, int statusCode) {
        this.description = description;
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return description + " (code: " + statusCode + ")";
    }

    public boolean isFinal() {
        return this == REJECTED || this == CLOSED;
    }

    public String getDescription() {
        return description;
    }

    public int getStatusCode() {
        return statusCode;
    }

}