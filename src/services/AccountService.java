package services;

import accounts.Account;
import client.Customer;
import exceptions.AuditFailedException;
import exceptions.InvalidTransactionException;
import generic.Pair;
import generic.Repository;
import institutions.Employee;
import institutions.FinancialInstitution;
import interfaces.Auditable;
import interfaces.Notifiable;
import interfaces.Reportable;
import transactions.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            } finally {
                System.out.println("Audit process completed for: " + institution.getName());
            }
        }
    }


    public void iterateCustomers(List<Customer> customers) {
        System.out.println("--- Iterating customers list ---");
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer.getCustomerName());
        }
        System.out.println("Total customers: " + customers.size());
        System.out.println("First customer: " + customers.get(0).getCustomerName());
    }


    public void iterateEmployees(Set<Employee> employees) {
        System.out.println("--- Iterating employees set ---");
        for (Employee employee : employees) {
            System.out.println("Employee: " + employee.getEmployeeName() + " - " + employee.getPosition());
        }
        System.out.println("Total employees: " + employees.size());
        System.out.println("First employee: " + employees.iterator().next().getEmployeeName());
    }


    public void iterateCustomerAccounts(Map<Customer, Account> customerAccountMap) {
        System.out.println("--- Iterating customer-account map ---");
        for (Map.Entry<Customer, Account> entry : customerAccountMap.entrySet()) {
            System.out.println("Customer: " + entry.getKey().getCustomerName() + " → Account: " + entry.getValue().getAccountType());
        }
        System.out.println("Map size: " + customerAccountMap.size());
        Map.Entry<Customer, Account> first = customerAccountMap.entrySet().iterator().next();
        System.out.println("First map entry: " + first.getKey().getCustomerName() + " → " + first.getValue().getAccountType());
    }


    public Pair<String, BigDecimal> getAccountSummaryPair(Account account) {
        return new Pair<>(account.getOwner(), account.getBalance());
    }


    public void useRepository(List<Transaction> transactions) {
        Repository<Transaction> repo = new Repository<>();
        for (Transaction t : transactions) {
            repo.add(t);
        }
        System.out.println("Repository size: " + repo.size());
        System.out.println("Repository empty: " + repo.isEmpty());
        System.out.println("First transaction: " + repo.getFirst());
        repo.remove(repo.get(0));
        System.out.println("After remove, size: " + repo.size());
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
            throw new InvalidTransactionException("Transaction amount must be greater than zero.");
        }
        System.out.println("Transaction valid: " + transaction.getTransactionAmount());
    }

}