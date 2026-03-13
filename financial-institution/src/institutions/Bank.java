package institutions;

import client.Address;
import client.Customer;

public class Bank extends FinancialInstitution {

    private String bankCode;
    private Customer[] customers;
    private Employee[] employees;

    public Bank(String name, Address address, String bankCode, Customer[] customers, Employee[] employees) {
        super(name, address);
        this.bankCode = bankCode;
        this.customers = customers;
        this.employees = employees;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

}