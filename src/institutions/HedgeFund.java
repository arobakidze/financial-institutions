package institutions;

import client.Address;
import java.math.BigDecimal;

public class HedgeFund extends FinancialInstitution {

    private BigDecimal totalAssets;

    public HedgeFund(String name, Address address, BigDecimal totalAssets) {
        super(name, address);
        this.totalAssets = totalAssets;
    }

    @Override
    public String getInstitutionType() {
        return "Hedge Fund";
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

}