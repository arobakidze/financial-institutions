package com.financial.financialinstitutions.interfaces;

import com.financial.financialinstitutions.exceptions.AuditFailedException;

import java.time.LocalDate;

public interface Auditable {

    void audit() throws AuditFailedException;

    LocalDate getLastAuditDate();

}