package com.financial.financialinstitutions.client;

import java.time.LocalDate;

public class Passport {

    private String passportNumber;
    private LocalDate expiryDate;

    public Passport(String passportNumber, LocalDate expiryDate) {
        this.passportNumber = passportNumber;
        this.expiryDate = expiryDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

}