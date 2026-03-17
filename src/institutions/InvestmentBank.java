package institutions;

import client.Address;
import client.Customer;

public class InvestmentBank extends Bank {

    private String specialization;

    public InvestmentBank(String name, Address address, String bankCode, Customer[] customers, Employee[] employees, String specialization) {
        super(name, address, bankCode, customers, employees);
        this.specialization = specialization;
    }

    @Override
    public String getInstitutionType() {
        return "Investment Bank";
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}