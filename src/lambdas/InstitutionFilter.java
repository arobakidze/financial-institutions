package lambdas;

import institutions.FinancialInstitution;

@FunctionalInterface
public interface InstitutionFilter {

    boolean filter(FinancialInstitution institution);

}