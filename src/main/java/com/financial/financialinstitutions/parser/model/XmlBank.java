package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class XmlBank {

    private String name;
    private String registrationNumber;
    private String bankCode;
    private boolean active;
    private BigDecimal totalAssets;
    private LocalDateTime establishedDate;
    private List<XmlCustomer> customers;

    public XmlBank() {
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @XmlElement(name = "registrationNumber")
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    @XmlElement(name = "bankCode")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public boolean isActive() {
        return active;
    }

    @XmlElement(name = "active")
    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    @XmlElement(name = "totalAssets")
    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public LocalDateTime getEstablishedDate() {
        return establishedDate;
    }

    @XmlElement(name = "establishedDate")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public void setEstablishedDate(LocalDateTime establishedDate) {
        this.establishedDate = establishedDate;
    }

    public List<XmlCustomer> getCustomers() {
        return customers;
    }

    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    public void setCustomers(List<XmlCustomer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "XmlBank{name='" + name + "', registrationNumber='" + registrationNumber +
                "', bankCode='" + bankCode + "', active=" + active +
                ", totalAssets=" + totalAssets + ", establishedDate=" + establishedDate +
                ", customers=" + customers + "}";
    }
}