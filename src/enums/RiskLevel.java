package enums;

public enum RiskLevel {

    LOW("Minimal risk", 1) {
        @Override
        public String getRiskAdvice() {
            return "Standard loan terms apply.";
        }
    },
    MEDIUM("Moderate risk", 2) {
        @Override
        public String getRiskAdvice() {
            return "Additional verification required.";
        }
    },
    HIGH("Significant risk", 3) {
        @Override
        public String getRiskAdvice() {
            return "Collateral required before approval.";
        }
    },
    CRITICAL("Extreme risk", 4) {
        @Override
        public String getRiskAdvice() {
            return "Loan application likely to be rejected.";
        }
    };

    private final String description;
    private final int score;

    static {
        System.out.println("RiskLevel enum loaded.");
    }

    RiskLevel(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public abstract String getRiskAdvice();

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

