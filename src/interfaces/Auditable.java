package interfaces;

import exceptions.AuditFailedException;

import java.time.LocalDate;

public interface Auditable {

    void audit() throws AuditFailedException;

    LocalDate getLastAuditDate();

}