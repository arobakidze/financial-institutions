package com.financial.financialinstitutions.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public enum AccountTier {

    BASIC("Basic account", new BigDecimal("0.00")),
    PREMIUM("Premium account", new BigDecimal("10.00")),
    ELITE("Elite account", new BigDecimal("50.00"));

    private static final Logger LOGGER = LogManager.getLogger(AccountTier.class);

    private final String description;
    private final BigDecimal monthlyFee;

    static {
        LOGGER.info("AccountTier enum loaded.");
    }

    AccountTier(String description, BigDecimal monthlyFee) {
        this.description = description;
        this.monthlyFee = monthlyFee;
    }

    public String getTierBenefits() {
        switch (this) {
            case BASIC:
                return "Standard features, no monthly fee waiver.";
            case PREMIUM:
                return "Free transfers, priority support.";
            case ELITE:
                return "Unlimited transfers, personal banker, travel insurance.";
            default:
                return "Unknown tier.";
        }
    }

    public boolean hasFee() {
        return monthlyFee.compareTo(BigDecimal.ZERO) > 0;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

}