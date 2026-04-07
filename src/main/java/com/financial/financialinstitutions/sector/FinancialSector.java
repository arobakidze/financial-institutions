package com.financial.financialinstitutions.sector;

import com.financial.financialinstitutions.base.InstitutionGroup;
import com.financial.financialinstitutions.institutions.FinancialInstitution;
import com.financial.financialinstitutions.interfaces.Reportable;

import java.util.Map;

public final class FinancialSector extends InstitutionGroup implements Reportable {

    private static final String SECTOR_TYPE = "Financial Sector";
    private Map<String, FinancialInstitution> institutions;

    public FinancialSector(Map<String, FinancialInstitution> institutions) {
        super("Georgian Financial Sector");
        this.institutions = institutions;
    }

    @Override
    public final String getGroupType() {
        return SECTOR_TYPE;
    }

    @Override
    public String generateReport() {
        return "Financial Sector Report | Total Institutions: " + institutions.size();
    }

    public Map<String, FinancialInstitution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Map<String, FinancialInstitution> institutions) {
        this.institutions = institutions;
    }

}