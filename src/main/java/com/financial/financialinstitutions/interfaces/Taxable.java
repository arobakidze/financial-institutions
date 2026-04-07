package com.financial.financialinstitutions.interfaces;

import java.math.BigDecimal;

public interface Taxable {

    BigDecimal calculateTax();

    String getTaxId();

}