package base;

import accounts.Account;

public class AccountHolder extends Person {

    private Account account;

    public AccountHolder(String fullName, String email, Account account) {
        super(fullName, email);
        this.account = account;
    }

    @Override
    public String getRole() {
        return "Account Holder";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}