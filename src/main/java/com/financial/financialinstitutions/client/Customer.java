package com.financial.financialinstitutions.client;

import com.financial.financialinstitutions.accounts.Account;
import com.financial.financialinstitutions.base.AccountHolder;
import java.util.Objects;

public class Customer extends AccountHolder {

    private String customerEmail;
    private Passport passport;

    public Customer(String customerName, String customerEmail, Passport passport, Account account) {
        super(customerName, customerEmail, account);
        this.customerEmail = customerEmail;
        this.passport = passport;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return "Customer{name='" + getFullName() + "', email='" + customerEmail + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerEmail, customer.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerEmail);
    }

    public String getCustomerName() {
        return getFullName();
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

}