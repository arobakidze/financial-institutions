package institutions;

import client.Address;
import client.Customer;

public class InvestmentBank extends institutions.Bank {

    private String specialization;

    public InvestmentBank(String name, Address address, String bankCode, Customer[] customers, Employee[] employees, String specialization) {
        super(name, address, bankCode, customers, employees);
        this.specialization = specialization;
    }
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}