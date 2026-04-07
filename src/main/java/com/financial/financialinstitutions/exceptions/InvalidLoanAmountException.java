package com.financial.financialinstitutions.exceptions;

public class InvalidLoanAmountException extends RuntimeException {

    public InvalidLoanAmountException(String message) {
        super(message);
    }

}