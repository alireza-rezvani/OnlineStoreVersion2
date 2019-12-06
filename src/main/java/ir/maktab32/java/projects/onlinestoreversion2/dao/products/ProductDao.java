package ir.maktab32.java.projects.onlinestoreversion2.dao.products;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);
    void deleteProduct(int productId);
    void editProduct(int productId, Product product);
    Product findProductById(int productId);
    List<Product> findAllProducts();
}
