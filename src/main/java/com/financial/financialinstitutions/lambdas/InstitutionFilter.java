package com.financial.financialinstitutions.lambdas;

import com.financial.financialinstitutions.institutions.FinancialInstitution;

@FunctionalInterface
public interface InstitutionFilter {

    boolean filter(FinancialInstitution institution);

}