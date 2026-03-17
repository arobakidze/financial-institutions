package institutions;

import client.Address;

public class InsuranceCompany extends FinancialInstitution {

    private String insuranceType;

    public InsuranceCompany(String name, Address address, String insuranceType) {
        super(name, address);
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