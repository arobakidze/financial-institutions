package enums;

public enum InstitutionStatus {

    ACTIVE("Fully operational", "ACT") {
        @Override
        public String getStatusMessage() {
            return "Institution is fully operational and accepting customers.";
        }
    },
    INACTIVE("Not operational", "INA") {
        @Override
        public String getStatusMessage() {
            return "Institution is currently inactive.";
        }
    },
    SUSPENDED("Under investigation", "SUS") {
        @Override
        public String getStatusMessage() {
            return "Institution operations are suspended pending investigation.";
        }
    };

    private final String description;
    private final String code;

    static {
        System.out.println("InstitutionStatus enum loaded.");
    }

    InstitutionStatus(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public abstract String getStatusMessage();

    public boolean isOperational() {
        return this == ACTIVE;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

}
