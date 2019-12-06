package ir.maktab32.java.projects.onlinestoreversion2.model.products;

public class Product {
    private int productId;
    private String title;
    private int price;
    private int count;

    public Product(String title, int price, int count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
