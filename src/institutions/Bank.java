package institutions;

import client.Address;
import client.Customer;
import exceptions.AuditFailedException;
import interfaces.Auditable;
import interfaces.Reportable;
import interfaces.Taxable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bank extends FinancialInstitution implements Taxable, Auditable, Reportable {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.20");
    private String bankCode;
    private Customer[] customers;
    private Employee[] employees;
    private LocalDate lastAuditDate;

    public Bank(String name, String registrationNumber, Address address, String bankCode, Customer[] customers, Employee[] employees) {
        super(name, registrationNumber, address);
        this.bankCode = bankCode;
        this.customers = customers;
        this.employees = employees;
        this.lastAuditDate = LocalDate.now();
    }

    @Override
    public String getInstitutionType() {
        return "Bank";
    }

    @Override
    public BigDecimal calculateTax() {
        return new BigDecimal("500000.00").multiply(TAX_RATE);
    }

    @Override
    public String getTaxId() {
        return "TAX-" + getRegistrationNumber();
    }

    @Override
    public void audit() throws AuditFailedException {
        if (customers == null || customers.length == 0) {
            throw new AuditFailedException("Audit failed — no customers found for bank: " + getName());
        }
        System.out.println("Auditing bank: " + getName());
        this.lastAuditDate = LocalDate.now();
    }

    @Override
    public LocalDate getLastAuditDate() {
        return lastAuditDate;
    }

    @Override
    public String generateReport() {
        return "Bank Report: " + getName() + " | Customers: " + customers.length + " | Tax: " + calculateTax();
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