package com.financial.financialinstitutions.lambdas;

import com.financial.financialinstitutions.accounts.Account;

@FunctionalInterface
public interface AccountValidator {

    boolean validate(Account account);

}