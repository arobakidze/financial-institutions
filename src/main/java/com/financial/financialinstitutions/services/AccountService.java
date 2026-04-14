package com.financial.financialinstitutions.services;

import com.financial.financialinstitutions.accounts.Account;
import com.financial.financialinstitutions.client.Customer;
import com.financial.financialinstitutions.exceptions.AuditFailedException;
import com.financial.financialinstitutions.exceptions.InvalidTransactionException;
import com.financial.financialinstitutions.generic.Pair;
import com.financial.financialinstitutions.generic.Repository;
import com.financial.financialinstitutions.institutions.Employee;
import com.financial.financialinstitutions.institutions.FinancialInstitution;
import com.financial.financialinstitutions.interfaces.Auditable;
import com.financial.financialinstitutions.interfaces.Notifiable;
import com.financial.financialinstitutions.interfaces.Reportable;
import com.financial.financialinstitutions.lambdas.AccountValidator;
import com.financial.financialinstitutions.lambdas.InstitutionFilter;
import com.financial.financialinstitutions.lambdas.TransactionProcessor;
import com.financial.financialinstitutions.annotations.BusinessMethod;
import com.financial.financialinstitutions.summary.TransactionSummary;
import com.financial.financialinstitutions.transactions.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AccountService extends BaseService implements Notifiable {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    private static final String NOTIFICATION_CHANNEL = "EMAIL";

    @Override
    public void process(FinancialInstitution institution) {
        logAction("Processing accounts for: " + institution.getName());
        LOGGER.info("Institution type: {}", institution.getInstitutionType());

        if (institution instanceof Reportable) {
            LOGGER.info("{}", ((Reportable) institution).generateReport());
        }

        if (institution instanceof Auditable) {
            try {
                ((Auditable) institution).audit();
            } catch (AuditFailedException e) {
                LOGGER.error("Audit error: {}", e.getMessage());
            } finally {
                LOGGER.info("Audit process completed for: {}", institution.getName());
            }
        }
    }

    @BusinessMethod(description = "Filters accounts based on a given predicate")
    public List<Account> filterAccounts(List<Account> accounts, Predicate<Account> predicate) {
        return accounts.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<String> mapAccountsToStrings(List<Account> accounts, Function<Account, String> mapper) {
        return accounts.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public void processTransactions(List<Transaction> transactions, Consumer<Transaction> consumer) {
        for (Transaction transaction : transactions) {
            consumer.accept(transaction);
        }
    }

    public void printReport(Supplier<String> headerSupplier, List<Account> accounts) {
        LOGGER.info("{}", headerSupplier.get());
        accounts.stream()
                .map(a -> "  " + a.getOwner() + " — " + a.getBalance())
                .forEach(LOGGER::info);
    }

    public boolean canTransfer(Account account, BigDecimal amount, BiFunction<Account, BigDecimal, Boolean> transferCheck) {
        return transferCheck.apply(account, amount);
    }

    public void logCustomerAccounts(List<Customer> customers, BiConsumer<Customer, Account> logger) {
        customers.stream()
                .forEach(c -> logger.accept(c, c.getAccount()));
    }

    @BusinessMethod(description = "Validates an account using a custom validator")
    public boolean validateAccount(Account account, AccountValidator validator) {
        return validator.validate(account);
    }

    public void applyTransactionProcessor(List<Transaction> transactions, String owner, TransactionProcessor processor) {
        transactions.stream()
                .forEach(t -> processor.process(t, owner));
    }

    public List<FinancialInstitution> filterInstitutions(List<FinancialInstitution> institutions, InstitutionFilter filter) {
        return institutions.stream()
                .filter(i -> filter.filter(i))
                .collect(Collectors.toList());
    }

    public TransactionSummary buildTransactionSummary(String owner, List<Transaction> transactions) {
        BigDecimal total = transactions.stream()
                .map(Transaction::getTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TransactionSummary(owner, total, transactions.size());
    }

    public void useRepository(List<Transaction> transactions) {
        Repository<Transaction> repo = new Repository<>();
        transactions.stream()
                .filter(t -> t.getTransactionAmount().compareTo(BigDecimal.ZERO) > 0)
                .forEach(repo::add);
        LOGGER.info("Repository size: {}", repo.size());
        LOGGER.info("Repository empty: {}", repo.isEmpty());
        LOGGER.info("First transaction: {}", repo.getFirst());
        repo.remove(repo.get(0));
        LOGGER.info("After remove, size: {}", repo.size());
    }

    public void iterateCustomers(List<Customer> customers) {
        LOGGER.info("--- Iterating customers list ---");
        for (Customer customer : customers) {
            LOGGER.info("Customer: {}", customer.getCustomerName());
        }
        LOGGER.info("Total customers: {}", customers.size());
        LOGGER.info("First customer: {}", customers.get(0).getCustomerName());
    }

    public void iterateEmployees(Set<Employee> employees) {
        LOGGER.info("--- Iterating employees set ---");
        for (Employee employee : employees) {
            LOGGER.info("Employee: {} - {}", employee.getEmployeeName(), employee.getPosition());
        }
        LOGGER.info("Total employees: {}", employees.size());
        LOGGER.info("First employee: {}", employees.iterator().next().getEmployeeName());
    }

    public void iterateCustomerAccounts(Map<Customer, Account> customerAccountMap) {
        LOGGER.info("--- Iterating customer-account map ---");
        for (Map.Entry<Customer, Account> entry : customerAccountMap.entrySet()) {
            LOGGER.info("Customer: {} → Account: {}", entry.getKey().getCustomerName(), entry.getValue().getAccountType());
        }
        LOGGER.info("Map size: {}", customerAccountMap.size());
        Map.Entry<Customer, Account> first = customerAccountMap.entrySet().iterator().next();
        LOGGER.info("First map entry: {} → {}", first.getKey().getCustomerName(), first.getValue().getAccountType());
    }

    public Pair<String, BigDecimal> getAccountSummaryPair(Account account) {
        return new Pair<>(account.getOwner(), account.getBalance());
    }

    @Override
    public void sendNotification(String message) {
        LOGGER.info("[{}] Notification: {}", NOTIFICATION_CHANNEL, message);
    }

    @Override
    public String getNotificationChannel() {
        return NOTIFICATION_CHANNEL;
    }

    public void printAccountInfo(Account account) {
        logAction("Fetching account info");
        LOGGER.info("Account type: {}", account.getAccountType());
        LOGGER.info("Owner: {}", account.getOwner());
        LOGGER.info("Balance: {}", account.getBalance());
    }

    public void validateTransaction(Transaction transaction) {
        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Transaction amount must be greater than zero.");
        }
        LOGGER.info("Transaction valid: {}", transaction.getTransactionAmount());
    }

}