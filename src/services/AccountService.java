package services;

import accounts.Account;
import institutions.FinancialInstitution;

public class AccountService extends BaseService {

    @Override
    public void process(FinancialInstitution institution) {
        logAction("Processing accounts for: " + institution.getName());
        System.out.println("Institution type: " + institution.getInstitutionType());
    }

    public void printAccountInfo(Account account) {
        logAction("Fetching account info");
        System.out.println("Account type: " + account.getAccountType());
        System.out.println("Owner: " + account.getOwner());
        System.out.println("Balance: " + account.getBalance());
    }

}