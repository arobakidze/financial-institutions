package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

public class XmlCustomer {

    private String name;
    private String email;
    private boolean verified;
    private int age;
    private LocalDateTime registeredAt;
    private XmlAccount account;

    public XmlCustomer() {
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    @XmlElement(name = "verified")
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getAge() {
        return age;
    }

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    @XmlElement(name = "registeredAt")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public XmlAccount getAccount() {
        return account;
    }

    @XmlElement(name = "account")
    public void setAccount(XmlAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "XmlCustomer{name='" + name + "', email='" + email +
                "', verified=" + verified + ", age=" + age +
                ", registeredAt=" + registeredAt + ", account=" + account + "}";
    }
}