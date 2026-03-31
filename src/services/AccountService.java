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
import lambdas.AccountValidator;
import lambdas.InstitutionFilter;
import lambdas.TransactionProcessor;
import records.TransactionSummary;
import transactions.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    public List<Account> filterAccounts(List<Account> accounts, Predicate<Account> predicate) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts) {
            if (predicate.test(account)) {
                result.add(account);
            }
        }
        return result;
    }

    public List<String> mapAccountsToStrings(List<Account> accounts, Function<Account, String> mapper) {
        List<String> result = new ArrayList<>();
        for (Account account : accounts) {
            result.add(mapper.apply(account));
        }
        return result;
    }

    public void processTransactions(List<Transaction> transactions, Consumer<Transaction> consumer) {
        for (Transaction transaction : transactions) {
            consumer.accept(transaction);
        }
    }

    public void printReport(Supplier<String> headerSupplier, List<Account> accounts) {
        System.out.println(headerSupplier.get());
        for (Account account : accounts) {
            System.out.println("  " + account.getOwner() + " — " + account.getBalance());
        }
    }

    public boolean canTransfer(Account account, BigDecimal amount, BiFunction<Account, BigDecimal, Boolean> transferCheck) {
        return transferCheck.apply(account, amount);
    }

    public void logCustomerAccounts(List<Customer> customers, BiConsumer<Customer, Account> logger) {
        for (Customer customer : customers) {
            logger.accept(customer, customer.getAccount());
        }
    }

    public boolean validateAccount(Account account, AccountValidator validator) {
        return validator.validate(account);
    }

    public void applyTransactionProcessor(List<Transaction> transactions, String owner, TransactionProcessor processor) {
        for (Transaction transaction : transactions) {
            processor.process(transaction, owner);
        }
    }

    public List<FinancialInstitution> filterInstitutions(List<FinancialInstitution> institutions, InstitutionFilter filter) {
        List<FinancialInstitution> result = new ArrayList<>();
        for (FinancialInstitution institution : institutions) {
            if (filter.filter(institution)) {
                result.add(institution);
            }
        }
        return result;
    }

    public TransactionSummary buildTransactionSummary(String owner, List<Transaction> transactions) {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction t : transactions) {
            total = total.add(t.getTransactionAmount());
        }
        return new TransactionSummary(owner, total, transactions.size());
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