package base;

import client.Address;

public abstract class LegalEntity {

    private String registrationNumber;
    private Address address;

    public LegalEntity(String registrationNumber, Address address) {
        this.registrationNumber = registrationNumber;
        this.address = address;
    }

    public abstract String getEntityType();

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}