package lambdas;

import transactions.Transaction;

@FunctionalInterface
public interface TransactionProcessor {

    void process(Transaction transaction, String accountOwner);

}
