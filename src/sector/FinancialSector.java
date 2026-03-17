package sector;

import base.InstitutionGroup;
import institutions.Bank;
import institutions.HedgeFund;
import institutions.InsuranceCompany;

public class FinancialSector extends InstitutionGroup {

    private Bank[] banks;
    private HedgeFund[] hedgeFunds;
    private InsuranceCompany[] insuranceCompanies;

    public FinancialSector(Bank[] banks, HedgeFund[] hedgeFunds, InsuranceCompany[] insuranceCompanies) {
        super("Georgian Financial Sector");
        this.banks = banks;
        this.hedgeFunds = hedgeFunds;
        this.insuranceCompanies = insuranceCompanies;
    }

    @Override
    public String getGroupType() {
        return "Financial Sector";
    }

    public Bank[] getBanks() {
        return banks;
    }

    public void setBanks(Bank[] banks) {
        this.banks = banks;
    }

    public HedgeFund[] getHedgeFunds() {
        return hedgeFunds;
    }

    public void setHedgeFunds(HedgeFund[] hedgeFunds) {
        this.hedgeFunds = hedgeFunds;
    }

    public InsuranceCompany[] getInsuranceCompanies() {
        return insuranceCompanies;
    }

    public void setInsuranceCompanies(InsuranceCompany[] insuranceCompanies) {
        this.insuranceCompanies = insuranceCompanies;
    }

}