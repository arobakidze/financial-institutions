package com.financial.financialinstitutions.main;

import com.financial.financialinstitutions.connection.Connection;
import com.financial.financialinstitutions.connection.ConnectionPool;
import com.financial.financialinstitutions.threads.CompletableFutureDemo;
import com.financial.financialinstitutions.threads.ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.financial.financialinstitutions.services.TextFileService;
import com.financial.financialinstitutions.accounts.Card;
import com.financial.financialinstitutions.accounts.LoanAccount;
import com.financial.financialinstitutions.accounts.SavingsAccount;
import com.financial.financialinstitutions.accounts.Account;
import com.financial.financialinstitutions.client.Address;
import com.financial.financialinstitutions.client.ContactInfo;
import com.financial.financialinstitutions.client.Customer;
import com.financial.financialinstitutions.client.Passport;
import com.financial.financialinstitutions.enums.AccountTier;
import com.financial.financialinstitutions.enums.InstitutionStatus;
import com.financial.financialinstitutions.enums.LoanStatus;
import com.financial.financialinstitutions.enums.RiskLevel;
import com.financial.financialinstitutions.enums.TransactionType;
import com.financial.financialinstitutions.exceptions.AuditFailedException;
import com.financial.financialinstitutions.generic.Pair;
import com.financial.financialinstitutions.institutions.Bank;
import com.financial.financialinstitutions.institutions.Branch;
import com.financial.financialinstitutions.institutions.Employee;
import com.financial.financialinstitutions.institutions.FinancialInstitution;
import com.financial.financialinstitutions.institutions.HedgeFund;
import com.financial.financialinstitutions.institutions.InsuranceCompany;
import com.financial.financialinstitutions.institutions.InvestmentBank;
import com.financial.financialinstitutions.institutions.RetailBank;
import com.financial.financialinstitutions.interfaces.Auditable;
import com.financial.financialinstitutions.interfaces.Reportable;
import com.financial.financialinstitutions.interfaces.Taxable;
import com.financial.financialinstitutions.interfaces.Transferable;
import com.financial.financialinstitutions.loans.Loan;
import com.financial.financialinstitutions.summary.TransactionSummary;
import com.financial.financialinstitutions.resources.BankingResource;
import com.financial.financialinstitutions.sector.FinancialSector;
import com.financial.financialinstitutions.services.AccountService;
import com.financial.financialinstitutions.services.LoanService;
import com.financial.financialinstitutions.transactions.Transaction;
import com.financial.financialinstitutions.reflection.ReflectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.financial.financialinstitutions.parser.JacksonParser;
import com.financial.financialinstitutions.parser.JaxbParser;
import com.financial.financialinstitutions.parser.Parser;
import com.financial.financialinstitutions.parser.SaxParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Address tbilisi = new Address("Rustaveli Avenue", "Tbilisi", "Georgia");
        Address kutaisi = new Address("Chavchavadze Street", "Kutaisi", "Georgia");

        ContactInfo contactInfo = new ContactInfo("+995599123456", "info@tbcbank.ge", tbilisi);

        Passport passport1 = new Passport("GE123456", LocalDate.of(2028, 5, 20));
        Passport passport2 = new Passport("GE654321", LocalDate.of(2027, 3, 15));
        Passport passport3 = new Passport("GE111222", LocalDate.of(2029, 8, 10));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new BigDecimal("300.00"), TransactionType.DEPOSIT, LocalDate.of(2025, 3, 10)));
        transactions.add(new Transaction(new BigDecimal("150.00"), TransactionType.WITHDRAWAL, LocalDate.of(2025, 3, 11)));

        Card card1 = new Card("4111111111111111", LocalDate.of(2027, 12, 31), "debit");
        Card card2 = new Card("4222222222222222", LocalDate.of(2026, 6, 30), "credit");
        Card card3 = new Card("4333333333333333", LocalDate.of(2028, 9, 15), "debit");

        Loan loan = new Loan(new BigDecimal("15000.00"), new BigDecimal("5.5"), LocalDate.of(2025, 1, 1), 24);
        loan.setLoanStatus(LoanStatus.APPROVED);

        Account account = new SavingsAccount("Avtandili", new BigDecimal("2000.00"), transactions, card1, new BigDecimal("3.0"));
        Account savings = new SavingsAccount("Elene", new BigDecimal("8000.00"), transactions, card2, new BigDecimal("4.5"));
        Account loanAccount = new LoanAccount("Luka", new BigDecimal("0.00"), transactions, card3, loan);

        account.setAccountTier(AccountTier.PREMIUM);
        savings.setAccountTier(AccountTier.ELITE);

        ((LoanAccount) loanAccount).setRiskLevel(RiskLevel.MEDIUM);

        Customer customer1 = new Customer("Avtandili Robakidze", "avtandili@gmail.com", passport1, account);
        Customer customer2 = new Customer("Elene Maisuradze", "elene@gmail.com", passport2, savings);
        Customer customer3 = new Customer("Luka Beridze", "luka@gmail.com", passport3, loanAccount);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee("Giorgi Kiknadze", "Manager"));
        employees.add(new Employee("Nino Tsiklauri", "Accountant"));

        List<Branch> branches = new ArrayList<>();
        branches.add(new Branch("TBC Rustaveli Branch", tbilisi));
        branches.add(new Branch("Liberty Kutaisi Branch", kutaisi));

        FinancialInstitution institution1 = new Bank("TBC Bank", "REG001", tbilisi, "TBC001", customers, employees);
        FinancialInstitution institution2 = new HedgeFund("Basis Bank", "REG002", tbilisi, new BigDecimal("12000000.00"));
        FinancialInstitution institution3 = new InsuranceCompany("GPI Holding", "REG003", tbilisi, "Health");
        FinancialInstitution institution4 = new InvestmentBank("Bank of Georgia", "REG004", tbilisi, "BOG001", customers, employees, "Investments");
        FinancialInstitution institution5 = new RetailBank("Liberty Bank", "REG005", tbilisi, "LIB001", customers, employees, 60, branches);

        institution2.setStatus(InstitutionStatus.INACTIVE);

        Map<String, FinancialInstitution> institutionMap = new LinkedHashMap<>();
        institutionMap.put(institution1.getName(), institution1);
        institutionMap.put(institution2.getName(), institution2);
        institutionMap.put(institution3.getName(), institution3);
        institutionMap.put(institution4.getName(), institution4);
        institutionMap.put(institution5.getName(), institution5);

        FinancialSector sector = new FinancialSector(institutionMap);

        AccountService accountService = new AccountService();
        LoanService loanService = new LoanService();

        TextFileService textFileService = new TextFileService();
        LOGGER.info("Total institutions created: {}", FinancialInstitution.getTotalInstitutions());
        textFileService.countUniqueWords("book.txt", "unique_words.txt");

        LOGGER.info("\n--- Enum demos ---");
        LOGGER.info("{}", institution1.getStatus().getStatusMessage());
        LOGGER.info("{}", institution2.getStatus().getStatusMessage());
        LOGGER.info("{}", loan.getLoanStatus().getStatusMessage());
        LOGGER.info("{}", ((LoanAccount) loanAccount).getRiskLevel().getRiskAdvice());
        LOGGER.info("{}", account.getAccountTier().getTierBenefits());
        LOGGER.info("{}", TransactionType.TRANSFER.calculateFee(new BigDecimal("1000.00")));

        LOGGER.info("\n--- Record demo ---");
        TransactionSummary summary = accountService.buildTransactionSummary("Avtandili", transactions);
        LOGGER.info("Owner: {}", summary.owner());
        LOGGER.info("Total: {}", summary.totalAmount());
        LOGGER.info("Count: {}", summary.transactionCount());
        LOGGER.info("Average: {}", summary.averageAmount());
        LOGGER.info("Has transactions: {}", summary.hasTransactions());

        LOGGER.info("\n--- Runnable ---");
        Runnable auditTask = () -> LOGGER.info("Running background audit for all institutions...");
        auditTask.run();

        LOGGER.info("\n--- Supplier ---");
        Supplier<String> reportHeader = () -> "=== Financial Sector Report - " + LocalDate.now() + " ===";
        List<Account> allAccounts = new ArrayList<>();
        allAccounts.add(account);
        allAccounts.add(savings);
        allAccounts.add(loanAccount);
        accountService.printReport(reportHeader, allAccounts);

        LOGGER.info("\n--- Consumer ---");
        Consumer<Transaction> transactionPrinter = t ->
                LOGGER.info("  [{}] {} on {}", t.getTransactionType(), t.getTransactionAmount(), t.getTransactionDate());
        accountService.processTransactions(transactions, transactionPrinter);

        LOGGER.info("\n--- Function ---");
        Function<Account, String> accountSummaryMapper = a ->
                a.getOwner() + " | " + a.getAccountType() + " | Balance: " + a.getBalance() + " | Tier: " + a.getAccountTier();
        List<String> summaries = accountService.mapAccountsToStrings(allAccounts, accountSummaryMapper);
        summaries.forEach(s -> LOGGER.info("  {}", s));

        LOGGER.info("\n--- Predicate ---");
        Predicate<Account> hasEnoughBalance = a -> a.getBalance().compareTo(new BigDecimal("1000.00")) > 0;
        List<Account> richAccounts = accountService.filterAccounts(allAccounts, hasEnoughBalance);
        LOGGER.info("Accounts with balance > 1000: {}", richAccounts.size());
        for (Account a : richAccounts) {
            LOGGER.info("  {} — {}", a.getOwner(), a.getBalance());
        }

        LOGGER.info("\n--- BiFunction ---");
        BiFunction<Account, BigDecimal, Boolean> transferFeasibility =
                (a, amount) -> a.getBalance().compareTo(amount) >= 0;
        boolean canTransfer = accountService.canTransfer(account, new BigDecimal("500.00"), transferFeasibility);
        LOGGER.info("Can Avtandili transfer 500? {}", canTransfer);

        LOGGER.info("\n--- BiConsumer ---");
        BiConsumer<Customer, Account> customerAccountLogger =
                (c, a) -> LOGGER.info("  {} owns a {} with balance {}", c.getCustomerName(), a.getAccountType(), a.getBalance());
        accountService.logCustomerAccounts(customers, customerAccountLogger);

        LOGGER.info("\n--- Custom AccountValidator lambda ---");
        boolean isValid = accountService.validateAccount(account,
                a -> a.getBalance().compareTo(BigDecimal.ZERO) > 0 && a.getOwner() != null);
        LOGGER.info("Account valid: {}", isValid);

        LOGGER.info("\n--- Custom TransactionProcessor lambda ---");
        accountService.applyTransactionProcessor(transactions, "Avtandili",
                (t, owner) -> LOGGER.info("  Processing {} of {} for {}. Fee: {}", t.getTransactionType(), t.getTransactionAmount(), owner, t.getTransactionType().calculateFee(t.getTransactionAmount())));

        LOGGER.info("\n--- Custom InstitutionFilter lambda ---");
        List<FinancialInstitution> allInstitutions = new ArrayList<>(institutionMap.values());
        List<FinancialInstitution> activeInstitutions = accountService.filterInstitutions(allInstitutions,
                i -> i.getStatus() == InstitutionStatus.ACTIVE);
        LOGGER.info("Active institutions: {}", activeInstitutions.size());
        for (FinancialInstitution inst : activeInstitutions) {
            LOGGER.info("  {}", inst.getName());
        }

        LOGGER.info("\n--- Collections ---");
        accountService.iterateCustomers(customers);
        accountService.iterateEmployees(((Bank) institution1).getEmployees());
        accountService.iterateCustomerAccounts(((Bank) institution1).getCustomerAccountMap());
        accountService.useRepository(transactions);

        Pair<String, BigDecimal> pair = accountService.getAccountSummaryPair(account);
        LOGGER.info("Account summary pair: {}", pair);

        LOGGER.info("\n--- Interfaces ---");
        Taxable taxable1 = (Taxable) institution1;
        Taxable taxable2 = (Taxable) institution2;
        LOGGER.info("TBC Bank tax: {}", taxable1.calculateTax());
        LOGGER.info("Basis Bank tax: {}", taxable2.calculateTax());

        Reportable reportable1 = (Reportable) institution1;
        LOGGER.info("{}", reportable1.generateReport());
        LOGGER.info("{}", sector.generateReport());

        LOGGER.info("\n--- Audit ---");
        Auditable auditable1 = (Auditable) institution1;
        try {
            auditable1.audit();
        } catch (AuditFailedException e) {
            LOGGER.error("Audit error: {}", e.getMessage());
        } finally {
            LOGGER.info("Audit process completed for: {}", institution1.getName());
        }

        Transferable transferable = (Transferable) account;
        transferable.transfer(new BigDecimal("500.00"), "GE29TB1234567890");

        accountService.process(institution1);
        accountService.process(institution2);
        accountService.process(institution3);
        accountService.sendNotification("Welcome to TBC Bank!");
        accountService.validateTransaction(transactions.get(0));
        accountService.printAccountInfo(account);
        accountService.printAccountInfo(savings);
        accountService.printAccountInfo(loanAccount);

        loanService.process(institution1);
        loanService.approveLoan(customer3, (LoanAccount) loanAccount);

        try (BankingResource resource = new BankingResource("TBC Core Banking System")) {
            resource.process("ACC-001");
            resource.process("ACC-002");
        }

        LOGGER.info("\n--- Reflection ---");
        ReflectionUtil.printClassInfo(Employee.class);

        try {
            Employee reflectedEmployee = (Employee) ReflectionUtil.createObject(
                    Employee.class,
                    new Class[]{String.class, String.class},
                    new Object[]{"Mariam Gelashvili", "Director"}
            );
            LOGGER.info("Created via reflection: {} - {}", reflectedEmployee.getEmployeeName(), reflectedEmployee.getPosition());

            String position = (String) ReflectionUtil.invokeMethod(
                    reflectedEmployee,
                    "getPosition",
                    new Class[]{},
                    new Object[]{}
            );
            LOGGER.info("Method called via reflection - position: {}", position);
        } catch (Exception e) {
            LOGGER.error("Reflection error: {}", e.getMessage());
        }

        ReflectionUtil.handleBusinessMethods(LoanService.class);
        ReflectionUtil.handleBusinessMethods(AccountService.class);
        ReflectionUtil.handleBusinessMethods(Bank.class);

        LOGGER.info("--- Connection Pool + Threads ---");

        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        Thread runnableThread = new Thread(ThreadDemo.createRunnableThread(), "RunnableThread");
        runnableThread.start();

        ThreadDemo.CustomThread customThread = new ThreadDemo.CustomThread("CustomThread");
        customThread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(7);

        for (int i = 1; i <= 7; i++) {
            final int threadNum = i;
            executorService.submit(() -> {
                Connection conn = null;
                try {
                    LOGGER.info("Worker-{} requesting connection...", threadNum);
                    conn = connectionPool.getConnection();
                    conn.create("Data from Worker-" + threadNum);
                    conn.get(threadNum);
                    conn.update(threadNum, "Updated by Worker-" + threadNum);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("Worker-{} interrupted", threadNum);
                } finally {
                    if (conn != null) {
                        connectionPool.releaseConnection(conn);
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("ExecutorService interrupted: {}", e.getMessage());
        }

        try {
            runnableThread.join();
            customThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Thread join interrupted: {}", e.getMessage());
        }

        LOGGER.info("--- CompletableFutures ---");
        CompletableFutureDemo.runAll();

        LOGGER.info("\n--- SAX Parser ---");
        Parser saxParser = new SaxParser();
        saxParser.parse("src/main/resources/financial-institutions.xml");

        LOGGER.info("\n--- JAXB Parser ---");
        Parser jaxbParser = new JaxbParser();
        jaxbParser.parse("src/main/resources/financial-institutions.xml");

        LOGGER.info("\n--- Jackson Parser ---");
        Parser jacksonParser = new JacksonParser();
        jacksonParser.parse("src/main/resources/financial-institutions.json");

        LOGGER.info("\n{}", customer1);
        LOGGER.info("{}", transactions.get(0));
        LOGGER.info("{}", account);

        LOGGER.info("Sector type: {}", sector.getGroupType());
        LOGGER.info("Contact phone: {}", contactInfo.getPhoneNumber());
        LOGGER.info("Total institutions created: {}", FinancialInstitution.getTotalInstitutions());

    }

}