package com.financial.financialinstitutions.institutions;

import com.financial.financialinstitutions.client.Address;
import com.financial.financialinstitutions.client.Customer;

import java.util.List;
import java.util.Set;

public class RetailBank extends Bank {

    private int numberOfBranches;
    private List<Branch> branches;

    public RetailBank(String name, String registrationNumber, Address address, String bankCode, List<Customer> customers, Set<Employee> employees, int numberOfBranches, List<Branch> branches) {
        super(name, registrationNumber, address, bankCode, customers, employees);
        this.numberOfBranches = numberOfBranches;
        this.branches = branches;
    }

    @Override
    public String getInstitutionType() {
        return "Retail Bank";
    }

    public int getNumberOfBranches() {
        return numberOfBranches;
    }

    public void setNumberOfBranches(int numberOfBranches) {
        this.numberOfBranches = numberOfBranches;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

}