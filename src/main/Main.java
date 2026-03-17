package main;

import accounts.Card;
import accounts.LoanAccount;
import accounts.SavingsAccount;
import accounts.Account;
import client.Address;
import client.Customer;
import client.Passport;
import institutions.Bank;
import institutions.Branch;
import institutions.Employee;
import institutions.FinancialInstitution;
import institutions.HedgeFund;
import institutions.InsuranceCompany;
import institutions.InvestmentBank;
import institutions.RetailBank;
import loans.Loan;
import sector.FinancialSector;
import services.AccountService;
import services.LoanService;
import transactions.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Address tbilisi = new Address("Rustaveli Avenue", "Tbilisi", "Georgia");
        Address kutaisi = new Address("Chavchavadze Street", "Kutaisi", "Georgia");

        Passport passport1 = new Passport("GE123456", LocalDate.of(2028, 5, 20));
        Passport passport2 = new Passport("GE654321", LocalDate.of(2027, 3, 15));
        Passport passport3 = new Passport("GE111222", LocalDate.of(2029, 8, 10));

        Transaction transaction1 = new Transaction(new BigDecimal("300.00"), "deposit", LocalDate.of(2025, 3, 10));
        Transaction transaction2 = new Transaction(new BigDecimal("150.00"), "withdrawal", LocalDate.of(2025, 3, 11));
        Transaction[] transactions = {transaction1, transaction2};

        Card card1 = new Card("4111111111111111", LocalDate.of(2027, 12, 31), "debit");
        Card card2 = new Card("4222222222222222", LocalDate.of(2026, 6, 30), "credit");
        Card card3 = new Card("4333333333333333", LocalDate.of(2028, 9, 15), "debit");

        Loan loan = new Loan(new BigDecimal("15000.00"), new BigDecimal("5.5"), LocalDate.of(2025, 1, 1), 24);


        Account account = new SavingsAccount("Avtandili", new BigDecimal("2000.00"), transactions, card1, new BigDecimal("3.0"));
        Account savings = new SavingsAccount("Elene", new BigDecimal("8000.00"), transactions, card2, new BigDecimal("4.5"));
        Account loanAccount = new LoanAccount("Luka", new BigDecimal("0.00"), transactions, card3, loan);

        Customer customer1 = new Customer("Avtandili Robakidze", "avtandili@gmail.com", passport1, account);
        Customer customer2 = new Customer("Elene Maisuradze", "elene@gmail.com", passport2, savings);
        Customer customer3 = new Customer("Luka Beridze", "luka@gmail.com", passport3, loanAccount);
        Customer[] customers = {customer1, customer2, customer3};

        Employee employee1 = new Employee("Giorgi Kiknadze", "Manager");
        Employee employee2 = new Employee("Nino Tsiklauri", "Accountant");
        Employee[] employees = {employee1, employee2};

        Branch branch1 = new Branch("TBC Rustaveli Branch", tbilisi);
        Branch branch2 = new Branch("Liberty Kutaisi Branch", kutaisi);
        Branch[] branches = {branch1, branch2};


        FinancialInstitution institution1 = new Bank("TBC Bank", tbilisi, "TBC001", customers, employees);
        FinancialInstitution institution2 = new HedgeFund("Basis Bank", tbilisi, new BigDecimal("12000000.00"));
        FinancialInstitution institution3 = new InsuranceCompany("GPI Holding", tbilisi, "Health");
        FinancialInstitution institution4 = new InvestmentBank("Bank of Georgia", tbilisi, "BOG001", customers, employees, "Investments");
        FinancialInstitution institution5 = new RetailBank("Liberty Bank", tbilisi, "LIB001", customers, employees, 60, branches);

        Bank[] banks = {(Bank) institution1, (Bank) institution4, (Bank) institution5};
        HedgeFund[] hedgeFunds = {(HedgeFund) institution2};
        InsuranceCompany[] insuranceCompanies = {(InsuranceCompany) institution3};

        FinancialSector sector = new FinancialSector(banks, hedgeFunds, insuranceCompanies);

        AccountService accountService = new AccountService();
        accountService.process(institution1);
        accountService.process(institution2);
        accountService.process(institution3);

        accountService.printAccountInfo(account);
        accountService.printAccountInfo(savings);
        accountService.printAccountInfo(loanAccount);

        LoanService loanService = new LoanService();
        loanService.process(institution1);
        loanService.approveLoan(customer3, (LoanAccount) loanAccount);

        System.out.println(customer1);
        System.out.println(transaction1);
        System.out.println(account);

        System.out.println("Total institutions created: " + FinancialInstitution.getTotalInstitutions());

    }

}