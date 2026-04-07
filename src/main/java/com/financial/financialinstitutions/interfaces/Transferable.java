package com.financial.financialinstitutions.interfaces;

import java.math.BigDecimal;

public interface Transferable {

    void transfer(BigDecimal amount, String toAccount);

    BigDecimal getTransferLimit();

}