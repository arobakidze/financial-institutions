package com.financial.financialinstitutions.enums;

public enum RiskLevel {

    LOW("Minimal risk", 1),
    MEDIUM("Moderate risk", 2),
    HIGH("Significant risk", 3),
    CRITICAL("Extreme risk", 4);

    private final String description;
    private final int score;

    static {
        System.out.println("RiskLevel enum loaded.");
    }

    RiskLevel(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public String getRiskAdvice() {
        if (score <= 1) return "Standard loan terms apply.";
        if (score == 2) return "Additional verification required.";
        if (score == 3) return "Collateral required before approval.";
        return "Loan application likely to be rejected.";
    }

    public boolean isHighRisk() {
        return this == HIGH || this == CRITICAL;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }

}