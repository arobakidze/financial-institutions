package com.financial.financialinstitutions.accounts;

import com.financial.financialinstitutions.base.FinancialProduct;

import java.time.LocalDate;

public class Card extends FinancialProduct {

    private String cardNumber;
    private LocalDate expiryDate;
    private String cardType;

    public Card(String cardNumber, LocalDate expiryDate, String cardType) {
        super("Card");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
    }

    @Override
    public String getProductType() {
        return cardType + " Card";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}