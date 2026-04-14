package com.financial.financialinstitutions.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum InstitutionStatus {

    ACTIVE("Fully operational", "ACT"),
    INACTIVE("Not operational", "INA"),
    SUSPENDED("Under investigation", "SUS");

    private static final Logger LOGGER = LogManager.getLogger(InstitutionStatus.class);

    private final String description;
    private final String code;

    static {
        LOGGER.info("InstitutionStatus enum loaded.");
    }

    InstitutionStatus(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getStatusMessage() {
        return description + " [" + code + "]";
    }

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