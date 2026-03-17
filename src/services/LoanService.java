package services;

import accounts.LoanAccount;
import client.Customer;
import institutions.FinancialInstitution;
import java.math.BigDecimal;

public class LoanService extends BaseService {

    @Override
    public void process(FinancialInstitution institution) {
        logAction("Processing loans for: " + institution.getName());
        System.out.println("Institution type: " + institution.getInstitutionType());
    }

    public void approveLoan(Customer customer, LoanAccount loanAccount) {
        if (loanAccount.getLoan().getLoanAmount().compareTo(new BigDecimal("100000")) > 0) {
            System.out.println("Loan rejected for " + customer.getCustomerName() + " - amount too high.");
        } else {
            System.out.println("Loan approved for " + customer.getCustomerName() + "!");
            System.out.println("Amount: " + loanAccount.getLoan().getLoanAmount());
            System.out.println("Sending confirmation email to " + customer.getCustomerEmail());
        }
    }

}