package client;

import accounts.Account;
import java.util.Objects;

public class Customer {

    private String customerName;
    private String customerEmail;
    private Passport passport;
    private Account account;

    public Customer(String customerName, String customerEmail, Passport passport, Account account) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.passport = passport;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{name='" + customerName + "', email='" + customerEmail + "'}";
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
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}