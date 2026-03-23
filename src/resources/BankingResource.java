package resources;

import exceptions.AccountNotFoundException;

public class BankingResource implements AutoCloseable {

    private final String resourceName;
    private boolean isOpen;

    public BankingResource(String resourceName) {
        this.resourceName = resourceName;
        this.isOpen = true;
        System.out.println("Opening resource: " + resourceName);
    }

    public void process(String accountId) {
        if (!isOpen) {
            throw new AccountNotFoundException("Resource is closed. Cannot find account: " + accountId);
        }
        System.out.println("Processing account " + accountId + " via " + resourceName);
    }

    @Override
    public void close() {
        this.isOpen = false;
        System.out.println("Closing resource: " + resourceName);
    }

}