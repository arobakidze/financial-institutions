package institutions;

import client.Address;
import interfaces.Reportable;
import interfaces.Taxable;

import java.math.BigDecimal;

public class HedgeFund extends FinancialInstitution implements Taxable, Reportable {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.25");
    private BigDecimal totalAssets;

    public HedgeFund(String name, String registrationNumber, Address address, BigDecimal totalAssets) {
        super(name, registrationNumber, address);
        this.totalAssets = totalAssets;
    }

    @Override
    public String getInstitutionType() {
        return "Hedge Fund";
    }

    @Override
    public BigDecimal calculateTax() {
        return totalAssets.multiply(TAX_RATE);
    }

    @Override
    public String getTaxId() {
        return "TAX-HF-" + getRegistrationNumber();
    }

    @Override
    public String generateReport() {
        return "Hedge Fund Report: " + getName() + " | Total Assets: " + totalAssets + " | Tax: " + calculateTax();
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

}