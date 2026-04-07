package com.financial.financialinstitutions.services;

import com.financial.financialinstitutions.institutions.FinancialInstitution;

public abstract class BaseService {

    public abstract void process(FinancialInstitution institution);

    protected void logAction(String action) {
        System.out.println("[LOG] " + action);
    }

}