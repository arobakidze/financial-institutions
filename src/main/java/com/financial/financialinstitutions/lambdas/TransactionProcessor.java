package com.financial.financialinstitutions.lambdas;

import com.financial.financialinstitutions.transactions.Transaction;

@FunctionalInterface
public interface TransactionProcessor {

    void process(Transaction transaction, String accountOwner);

}
