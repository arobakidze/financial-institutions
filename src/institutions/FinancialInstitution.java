package institutions;

import base.LegalEntity;
import client.Address;

public abstract class FinancialInstitution extends LegalEntity {

    private String name;
    private static int totalInstitutions = 0;

    static {
        System.out.println("FinancialInstitution class loaded.");
    }

    public FinancialInstitution(String name, String registrationNumber, Address address) {
        super(registrationNumber, address);
        this.name = name;
        totalInstitutions++;
    }

    public abstract String getInstitutionType();

    @Override
    public String getEntityType() {
        return "Financial Institution";
    }

    protected String getBasicInfo() {
        return "Name: " + name + ", Registration: " + getRegistrationNumber();
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

    public static void setTotalInstitutions(int totalInstitutions) {
        FinancialInstitution.totalInstitutions = totalInstitutions;
    }

}