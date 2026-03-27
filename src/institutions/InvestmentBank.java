package institutions;

import client.Address;
import client.Customer;

import java.util.List;
import java.util.Set;

public class InvestmentBank extends Bank {

    private String specialization;

    public InvestmentBank(String name, String registrationNumber, Address address, String bankCode, List<Customer> customers, Set<Employee> employees, String specialization) {
        super(name, registrationNumber, address, bankCode, customers, employees);
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