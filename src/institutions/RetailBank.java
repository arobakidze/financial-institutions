package institutions;

import client.Address;
import client.Customer;

public class RetailBank extends Bank {

    private int numberOfBranches;
    private Branch[] branches;

    public RetailBank(String name, Address address, String bankCode, Customer[] customers, Employee[] employees, int numberOfBranches, Branch[] branches) {
        super(name, address, bankCode, customers, employees);
        this.numberOfBranches = numberOfBranches;
        this.branches = branches;
    }

    public int getNumberOfBranches() {
        return numberOfBranches;
    }

    public void setNumberOfBranches(int numberOfBranches) {
        this.numberOfBranches = numberOfBranches;
    }

    public Branch[] getBranches() {
        return branches;
    }

    public void setBranches(Branch[] branches) {
        this.branches = branches;
    }

}