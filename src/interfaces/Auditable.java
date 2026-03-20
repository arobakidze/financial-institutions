package interfaces;

import java.time.LocalDate;

public interface Auditable {

    void audit();

    LocalDate getLastAuditDate();

}