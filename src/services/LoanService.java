package services;

import accounts.LoanAccount;
import client.Customer;
import institutions.FinancialInstitution;
import interfaces.Notifiable;

import java.math.BigDecimal;

public class LoanService extends BaseService implements Notifiable {

    private static final String NOTIFICATION_CHANNEL = "SMS";

    @Override
    public void process(FinancialInstitution institution) {
        logAction("Processing loans for: " + institution.getName());
        System.out.println("Institution type: " + institution.getInstitutionType());
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[" + NOTIFICATION_CHANNEL + "] Notification: " + message);
    }

    @Override
    public String getNotificationChannel() {
        return NOTIFICATION_CHANNEL;
    }

    public void approveLoan(Customer customer, LoanAccount loanAccount) {
        if (loanAccount.getLoan().getLoanAmount().compareTo(new BigDecimal("100000")) > 0) {
            sendNotification("Loan rejected for " + customer.getCustomerName());
            System.out.println("Loan rejected for " + customer.getCustomerName() + " - amount too high.");
        } else {
            sendNotification("Loan approved for " + customer.getCustomerName());
            System.out.println("Loan approved for " + customer.getCustomerName() + "!");
            System.out.println("Amount: " + loanAccount.getLoan().getLoanAmount());
            System.out.println("Sending confirmation email to " + customer.getCustomerEmail());
        }
    }

}