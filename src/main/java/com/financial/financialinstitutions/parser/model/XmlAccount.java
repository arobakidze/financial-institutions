package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.math.BigDecimal;
import java.util.List;

public class XmlAccount {

    private String owner;
    private BigDecimal balance;
    private String accountType;
    private boolean active;
    private List<XmlTransaction> transactions;

    public XmlAccount() {
    }

    public String getOwner() {
        return owner;
    }

    @XmlElement(name = "owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @XmlElement(name = "balance")
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    @XmlElement(name = "accountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isActive() {
        return active;
    }

    @XmlElement(name = "active")
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<XmlTransaction> getTransactions() {
        return transactions;
    }

    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    public void setTransactions(List<XmlTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "XmlAccount{owner='" + owner + "', balance=" + balance +
                ", accountType='" + accountType + "', active=" + active +
                ", transactions=" + transactions + "}";
    }
}