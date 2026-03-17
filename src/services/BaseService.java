package services;

import institutions.FinancialInstitution;

public abstract class BaseService {

    public abstract void process(FinancialInstitution institution);

    protected void logAction(String action) {
        System.out.println("[LOG] " + action);
    }

}