package enums;

import java.math.BigDecimal;

public enum AccountTier {

    BASIC("Basic account", new BigDecimal("0.00")) {
        @Override
        public String getTierBenefits() {
            return "Standard features, no monthly fee waiver.";
        }
    },
    PREMIUM("Premium account", new BigDecimal("10.00")) {
        @Override
        public String getTierBenefits() {
            return "Free transfers, priority support.";
        }
    },
    ELITE("Elite account", new BigDecimal("50.00")) {
        @Override
        public String getTierBenefits() {
            return "Unlimited transfers, personal banker, travel insurance.";
        }
    };

    private final String description;
    private final BigDecimal monthlyFee;

    static {
        System.out.println("AccountTier enum loaded.");
    }

    AccountTier(String description, BigDecimal monthlyFee) {
        this.description = description;
        this.monthlyFee = monthlyFee;
    }

    public abstract String getTierBenefits();

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