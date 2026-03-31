package enums;

public enum LoanStatus {

    PENDING("Awaiting review", 1) {
        @Override
        public String getStatusMessage() {
            return "Loan application is pending review.";
        }
    },
    APPROVED("Loan approved", 2) {
        @Override
        public String getStatusMessage() {
            return "Loan has been approved and funds will be disbursed.";
        }
    },
    REJECTED("Loan rejected", 3) {
        @Override
        public String getStatusMessage() {
            return "Loan application has been rejected.";
        }
    },
    CLOSED("Loan fully repaid", 4) {
        @Override
        public String getStatusMessage() {
            return "Loan has been fully repaid and closed.";
        }
    };

    private final String description;
    private final int statusCode;

    static {
        System.out.println("LoanStatus enum loaded.");
    }

    LoanStatus(String description, int statusCode) {
        this.description = description;
        this.statusCode = statusCode;
    }

    public abstract String getStatusMessage();

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