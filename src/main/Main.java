package main;

import accounts.Card;
import accounts.LoanAccount;
import accounts.SavingsAccount;
import accounts.Account;
import client.Address;
import client.ContactInfo;
import client.Customer;
import client.Passport;
import enums.AccountTier;
import enums.InstitutionStatus;
import enums.LoanStatus;
import enums.RiskLevel;
import enums.TransactionType;
import exceptions.AuditFailedException;
import generic.Pair;
import institutions.Bank;
import institutions.Branch;
import institutions.Employee;
import institutions.FinancialInstitution;
import institutions.HedgeFund;
import institutions.InsuranceCompany;
import institutions.InvestmentBank;
import institutions.RetailBank;
import interfaces.Auditable;
import interfaces.Reportable;
import interfaces.Taxable;
import interfaces.Transferable;
import loans.Loan;
import summary.TransactionSummary;
import resources.BankingResource;
import sector.FinancialSector;
import services.AccountService;
import services.LoanService;
import transactions.Transaction;
import reflection.ReflectionUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

public class Main {

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

        System.out.println("\n--- Enum demos ---");
        System.out.println(institution1.getStatus().getStatusMessage());
        System.out.println(institution2.getStatus().getStatusMessage());
        System.out.println(loan.getLoanStatus().getStatusMessage());
        System.out.println(((LoanAccount) loanAccount).getRiskLevel().getRiskAdvice());
        System.out.println(account.getAccountTier().getTierBenefits());
        System.out.println(TransactionType.TRANSFER.calculateFee(new BigDecimal("1000.00")));

        System.out.println("\n--- Record demo ---");
        TransactionSummary summary = accountService.buildTransactionSummary("Avtandili", transactions);
        System.out.println("Owner: " + summary.owner());
        System.out.println("Total: " + summary.totalAmount());
        System.out.println("Count: " + summary.transactionCount());
        System.out.println("Average: " + summary.averageAmount());
        System.out.println("Has transactions: " + summary.hasTransactions());

        System.out.println("\n--- Runnable ---");
        Runnable auditTask = () -> System.out.println("Running background audit for all institutions...");
        auditTask.run();

        System.out.println("\n--- Supplier ---");
        Supplier<String> reportHeader = () -> "=== Financial Sector Report - " + LocalDate.now() + " ===";
        List<Account> allAccounts = new ArrayList<>();
        allAccounts.add(account);
        allAccounts.add(savings);
        allAccounts.add(loanAccount);
        accountService.printReport(reportHeader, allAccounts);

        System.out.println("\n--- Consumer ---");
        Consumer<Transaction> transactionPrinter = t ->
                System.out.println("  [" + t.getTransactionType() + "] " + t.getTransactionAmount() + " on " + t.getTransactionDate());
        accountService.processTransactions(transactions, transactionPrinter);

        System.out.println("\n--- Function ---");
        Function<Account, String> accountSummaryMapper = a ->
                a.getOwner() + " | " + a.getAccountType() + " | Balance: " + a.getBalance() + " | Tier: " + a.getAccountTier();
        List<String> summaries = accountService.mapAccountsToStrings(allAccounts, accountSummaryMapper);
        summaries.forEach(s -> System.out.println("  " + s));

        System.out.println("\n--- Predicate ---");
        Predicate<Account> hasEnoughBalance = a -> a.getBalance().compareTo(new BigDecimal("1000.00")) > 0;
        List<Account> richAccounts = accountService.filterAccounts(allAccounts, hasEnoughBalance);
        System.out.println("Accounts with balance > 1000: " + richAccounts.size());
        for (Account a : richAccounts) {
            System.out.println("  " + a.getOwner() + " — " + a.getBalance());
        }

        System.out.println("\n--- BiFunction ---");
        BiFunction<Account, BigDecimal, Boolean> transferFeasibility =
                (a, amount) -> a.getBalance().compareTo(amount) >= 0;
        boolean canTransfer = accountService.canTransfer(account, new BigDecimal("500.00"), transferFeasibility);
        System.out.println("Can Avtandili transfer 500? " + canTransfer);

        System.out.println("\n--- BiConsumer ---");
        BiConsumer<Customer, Account> customerAccountLogger =
                (c, a) -> System.out.println("  " + c.getCustomerName() + " owns a " + a.getAccountType() + " with balance " + a.getBalance());
        accountService.logCustomerAccounts(customers, customerAccountLogger);

        System.out.println("\n--- Custom AccountValidator lambda ---");
        boolean isValid = accountService.validateAccount(account,
                a -> a.getBalance().compareTo(BigDecimal.ZERO) > 0 && a.getOwner() != null);
        System.out.println("Account valid: " + isValid);

        System.out.println("\n--- Custom TransactionProcessor lambda ---");
        accountService.applyTransactionProcessor(transactions, "Avtandili",
                (t, owner) -> System.out.println("  Processing " + t.getTransactionType() + " of " + t.getTransactionAmount() + " for " + owner + ". Fee: " + t.getTransactionType().calculateFee(t.getTransactionAmount())));

        System.out.println("\n--- Custom InstitutionFilter lambda ---");
        List<FinancialInstitution> allInstitutions = new ArrayList<>(institutionMap.values());
        List<FinancialInstitution> activeInstitutions = accountService.filterInstitutions(allInstitutions,
                i -> i.getStatus() == InstitutionStatus.ACTIVE);
        System.out.println("Active institutions: " + activeInstitutions.size());
        for (FinancialInstitution inst : activeInstitutions) {
            System.out.println("  " + inst.getName());
        }

        System.out.println("\n--- Collections ---");
        accountService.iterateCustomers(customers);
        accountService.iterateEmployees(((Bank) institution1).getEmployees());
        accountService.iterateCustomerAccounts(((Bank) institution1).getCustomerAccountMap());
        accountService.useRepository(transactions);

        Pair<String, BigDecimal> pair = accountService.getAccountSummaryPair(account);
        System.out.println("Account summary pair: " + pair);

        System.out.println("\n--- Interfaces ---");
        Taxable taxable1 = (Taxable) institution1;
        Taxable taxable2 = (Taxable) institution2;
        System.out.println("TBC Bank tax: " + taxable1.calculateTax());
        System.out.println("Basis Bank tax: " + taxable2.calculateTax());

        Reportable reportable1 = (Reportable) institution1;
        System.out.println(reportable1.generateReport());
        System.out.println(sector.generateReport());

        System.out.println("\n--- Audit ---");
        Auditable auditable1 = (Auditable) institution1;
        try {
            auditable1.audit();
        } catch (AuditFailedException e) {
            System.out.println("Audit error: " + e.getMessage());
        } finally {
            System.out.println("Audit process completed for: " + institution1.getName());
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

        System.out.println("\n--- Reflection ---");
        ReflectionUtil.printClassInfo(Employee.class);

        try {
            Employee reflectedEmployee = (Employee) ReflectionUtil.createObject(
                    Employee.class,
                    new Class[]{String.class, String.class},
                    new Object[]{"Mariam Gelashvili", "Director"}
            );
            System.out.println("Created via reflection: " + reflectedEmployee.getEmployeeName() + " - " + reflectedEmployee.getPosition());

            String position = (String) ReflectionUtil.invokeMethod(
                    reflectedEmployee,
                    "getPosition",
                    new Class[]{},
                    new Object[]{}
            );
            System.out.println("Method called via reflection - position: " + position);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
        }

        ReflectionUtil.handleBusinessMethods(LoanService.class);
        ReflectionUtil.handleBusinessMethods(AccountService.class);
        ReflectionUtil.handleBusinessMethods(Bank.class);

        System.out.println("\n" + customer1);
        System.out.println(transactions.get(0));
        System.out.println(account);

        System.out.println("Sector type: " + sector.getGroupType());
        System.out.println("Contact phone: " + contactInfo.getPhoneNumber());
        System.out.println("Total institutions created: " + FinancialInstitution.getTotalInstitutions());

    }

}