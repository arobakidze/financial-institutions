package loans;

import accounts.LoanAccount;
import client.Customer;

import java.math.BigDecimal;

public class LoanService {

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