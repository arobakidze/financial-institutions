package base;

public abstract class FinancialProduct {

    private String productName;

    public FinancialProduct(String productName) {
        this.productName = productName;
    }

    public abstract String getProductType();

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}