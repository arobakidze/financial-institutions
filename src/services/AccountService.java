package services;

import accounts.Account;
import exceptions.AuditFailedException;
import exceptions.InvalidTransactionException;
import interfaces.Auditable;
import interfaces.Notifiable;
import interfaces.Reportable;
import institutions.FinancialInstitution;
import transactions.Transaction;

import java.math.BigDecimal;

public class AccountService extends BaseService implements Notifiable {

    private static final String NOTIFICATION_CHANNEL = "EMAIL";

    @Override
    public void process(FinancialInstitution institution) {
        logAction("Processing accounts for: " + institution.getName());
        System.out.println("Institution type: " + institution.getInstitutionType());

        if (institution instanceof Reportable) {
            System.out.println(((Reportable) institution).generateReport());
        }

        if (institution instanceof Auditable) {
            try {
                ((Auditable) institution).audit();
            } catch (AuditFailedException e) {
                System.out.println("Audit error: " + e.getMessage());
            }
        }
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[" + NOTIFICATION_CHANNEL + "] Notification: " + message);
    }

    @Override
    public String getNotificationChannel() {
        return NOTIFICATION_CHANNEL;
    }

    public void printAccountInfo(Account account) {
        logAction("Fetching account info");
        System.out.println("Account type: " + account.getAccountType());
        System.out.println("Owner: " + account.getOwner());
        System.out.println("Balance: " + account.getBalance());
    }

    public void validateTransaction(Transaction transaction) {
        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Transaction amount must be greater than zero. Got: " + transaction.getTransactionAmount());
        }
        System.out.println("Transaction valid: " + transaction.getTransactionAmount());
    }

}