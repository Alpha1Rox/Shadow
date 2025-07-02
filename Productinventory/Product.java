public class Product {
    private String productName;
    private int productCount;

    public Product(String productName, int productCount) {
        this.productName = productName;
        this.productCount = productCount;
    }

    public String getProductName() { return productName; }
    public int getProductCount() { return productCount; }
    public void setProductName(String name) { this.productName = name; }
    public void setProductCount(int count) { this.productCount = count; }
}
