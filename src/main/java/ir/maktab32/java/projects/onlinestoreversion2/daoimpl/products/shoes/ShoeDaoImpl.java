package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes.ShoeDao;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.ProductDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.Shoe;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoeDaoImpl extends ProductDaoImpl implements ShoeDao {
    public void addShoe(Shoe shoe) {
        try {
            super.addProduct(shoe);
            int productId = super.findAllProducts().get(super.findAllProducts().size()-1).getProductId();

            sql = "insert into shoes(gender, size, color, product_id) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(shoe.getGender()));
            preparedStatement.setInt(2, shoe.getSize());
            preparedStatement.setString(3, shoe.getColor());
            preparedStatement.setInt(4, productId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShoe(int shoeId) {
        try {
            int productId = findShoeById(shoeId).getProductId();

            sql = "delete from shoes where id = " + shoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteProduct(productId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editShoe(int shoeId, Shoe shoe) {
        try {
            int productId = findShoeById(shoeId).getProductId();
            super.editProduct(productId,shoe);

            sql = "update shoes set gender = ?, size = ?, color = ? where id = " + shoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(shoe.getGender()));
            preparedStatement.setInt(2, shoe.getSize());
            preparedStatement.setString(3, shoe.getColor());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Shoe findShoeById(int shoeId) {
        Shoe resultShoe = null;
        try {
            int productId = 0;
            Gender gender = Gender.BOTH;
            int size = 0;
            String color = "";

            sql = "select * from shoes where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shoeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                gender = Gender.valueOf(resultSet.getString("gender"));
                size = resultSet.getInt("size");
                color = resultSet.getString("color");
                productId = resultSet.getInt("product_id");
            }

            Product product = super.findProductById(productId);
            String title = product.getTitle();
            int price = product.getPrice();
            int count = product.getCount();

            resultShoe = new Shoe(title, price, count, gender, size, color);
            resultShoe.setProductId(productId);
            resultShoe.setShoeId(shoeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultShoe;
    }

    public List<Shoe> findAllShoes() {
        List<Shoe> resultShoesList = new ArrayList<Shoe>();
        try {
            int shoeId;
            Gender gender;
            int size;
            String color;
            int productId;
            Product product;
            String title;
            int price;
            int count;

            sql = "select * from shoes";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                shoeId = resultSet.getInt("id");
                gender = Gender.valueOf(resultSet.getString("gender"));
                size = resultSet.getInt("size");
                color = resultSet.getString("color");
                productId = resultSet.getInt("product_id");
                product = super.findProductById(productId);
                title = product.getTitle();
                price = product.getPrice();
                count = product.getCount();
                resultShoesList.add(new Shoe(title, price, count, gender,size,color));
                resultShoesList.get(resultShoesList.size()-1).setProductId(productId);
                resultShoesList.get(resultShoesList.size()-1).setShoeId(shoeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultShoesList;
    }
}
