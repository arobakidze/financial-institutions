package institutions;

import accounts.Account;
import annotations.BusinessMethod;
import client.Address;
import client.Customer;
import exceptions.AuditFailedException;
import interfaces.Auditable;
import interfaces.Reportable;
import interfaces.Taxable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bank extends FinancialInstitution implements Taxable, Auditable, Reportable {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.20");
    private String bankCode;
    private List<Customer> customers;
    private Set<Employee> employees;
    private Map<Customer, Account> customerAccountMap;
    private LocalDate lastAuditDate;

    public Bank(String name, String registrationNumber, Address address, String bankCode, List<Customer> customers, Set<Employee> employees) {
        super(name, registrationNumber, address);
        this.bankCode = bankCode;
        this.customers = customers;
        this.employees = employees;
        this.customerAccountMap = new HashMap<>();
        // stream 9 — replacing for loop in constructor
        customers.stream().forEach(customer -> customerAccountMap.put(customer, customer.getAccount()));
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
    @BusinessMethod(description = "Audits the bank and verifies customer data")
    public void audit() throws AuditFailedException {
        if (customers == null || customers.isEmpty()) {
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
        return "Bank Report: " + getName() + " | Customers: " + customers.size() + " | Tax: " + calculateTax();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Map<Customer, Account> getCustomerAccountMap() {
        return customerAccountMap;
    }

    public void setCustomerAccountMap(Map<Customer, Account> map) {
        this.customerAccountMap = map;
    }

}