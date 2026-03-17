package institutions;

import client.Address;

public abstract class FinancialInstitution {

    private String name;
    private Address address;
    private static int totalInstitutions = 0;

    static {
        System.out.println("FinancialInstitution class loaded.");
    }

    public FinancialInstitution(String name, Address address) {
        this.name = name;
        this.address = address;
        totalInstitutions++;
    }

    public abstract String getInstitutionType();

    protected String getBasicInfo() {
        return "Name: " + name + ", City: " + address.getCity();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void setTotalInstitutions(int totalInstitutions) {
        FinancialInstitution.totalInstitutions = totalInstitutions;
    }

}