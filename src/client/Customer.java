package client;

import accounts.Account;

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