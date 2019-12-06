package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products;

import ir.maktab32.java.projects.onlinestoreversion2.connection.ConnectionClass;
import ir.maktab32.java.projects.onlinestoreversion2.dao.products.ProductDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    protected Connection connection = ConnectionClass.getConnection();
    protected PreparedStatement preparedStatement = null;
    protected String sql = null;

    public void addProduct(Product product) {
        try {
            sql = "insert into products(title, price, count) values(?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getCount());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try {
            sql = "delete from products where id = " + productId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(int productId, Product product) {
        try {
            sql = "update products set title = ?, price = ?, count = ? where id = " + productId;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getCount());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product findProductById(int productId) {
        Product resultProduct = null;
        try {
            sql = "select * from products where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                int count = resultSet.getInt("count");
                resultProduct = new Product(title, price, count);
                resultProduct.setProductId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultProduct;
    }

    public List<Product> findAllProducts() {

        List<Product> resultProductsList = new ArrayList<Product>();
        try {

            sql = "select * from products";

            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                int count = resultSet.getInt("count");
                resultProductsList.add(new Product(title, price, count));
                resultProductsList.get(resultProductsList.size()-1).setProductId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultProductsList;
    }
}
