package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class XmlTransaction {

    private BigDecimal amount;
    private String type;
    private boolean successful;
    private LocalDateTime transactionDate;

    public XmlTransaction() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    public boolean isSuccessful() {
        return successful;
    }

    @XmlElement(name = "successful")
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @XmlElement(name = "transactionDate")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "XmlTransaction{amount=" + amount +
                ", type='" + type + '\'' +
                ", successful=" + successful +
                ", transactionDate=" + transactionDate + "}";
    }
}