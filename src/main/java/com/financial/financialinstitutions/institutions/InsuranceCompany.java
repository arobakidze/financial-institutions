package com.financial.financialinstitutions.institutions;

import com.financial.financialinstitutions.client.Address;

public class InsuranceCompany extends FinancialInstitution {

    private String insuranceType;

    public InsuranceCompany(String name, String registrationNumber, Address address, String insuranceType) {
        super(name, registrationNumber, address);
        this.insuranceType = insuranceType;
    }

    @Override
    public String getInstitutionType() {
        return "Insurance Company";
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

}