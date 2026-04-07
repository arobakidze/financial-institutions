package com.financial.financialinstitutions.institutions;

import com.financial.financialinstitutions.client.Address;

public class Branch {

    private String branchName;
    private Address address;

    public Branch(String branchName, Address address) {
        this.branchName = branchName;
        this.address = address;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}