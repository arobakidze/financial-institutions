package lambdas;

import accounts.Account;

@FunctionalInterface
public interface AccountValidator {

    boolean validate(Account account);

}