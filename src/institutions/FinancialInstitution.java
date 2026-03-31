package institutions;

import base.LegalEntity;
import client.Address;
import enums.InstitutionStatus;

public abstract class FinancialInstitution extends LegalEntity {

    private String name;
    private InstitutionStatus status;
    private static int totalInstitutions = 0;

    static {
        System.out.println("FinancialInstitution class loaded.");
    }

    public FinancialInstitution(String name, String registrationNumber, Address address) {
        super(registrationNumber, address);
        this.name = name;
        this.status = InstitutionStatus.ACTIVE;
        totalInstitutions++;
    }

    public abstract String getInstitutionType();

    @Override
    public String getEntityType() {
        return "Financial Institution";
    }

    protected String getBasicInfo() {
        return "Name: " + name + ", Registration: " + getRegistrationNumber() + ", Status: " + status.getCode();
    }

    public static int getTotalInstitutions() {
        return totalInstitutions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionStatus getStatus() {
        return status;
    }

    public void setStatus(InstitutionStatus status) {
        this.status = status;
    }

    public static void setTotalInstitutions(int totalInstitutions) {
        FinancialInstitution.totalInstitutions = totalInstitutions;
    }

}