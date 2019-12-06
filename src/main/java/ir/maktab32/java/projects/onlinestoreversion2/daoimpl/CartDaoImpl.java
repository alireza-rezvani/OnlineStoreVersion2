package ir.maktab32.java.projects.onlinestoreversion2.daoimpl;

import ir.maktab32.java.projects.onlinestoreversion2.connection.ConnectionClass;
import ir.maktab32.java.projects.onlinestoreversion2.dao.CartDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    protected Connection connection = ConnectionClass.getConnection();
    protected PreparedStatement preparedStatement = null;
    protected String sql = null;

    public void addCart(Cart cart) {
        try {
            sql = "insert into carts values()";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCart(int cartId) {
        try {
            sql = "delete from carts where id = " + cartId;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCart(int cartId, Cart cart) {
        try {
            sql = "update carts set " +
                    "product1_id = ?, product1_count = ?, " +
                    "product2_id = ?, product2_count = ?, " +
                    "product3_id = ?, product3_count = ?, " +
                    "product4_id = ?, product4_count = ?, " +
                    "product5_id = ?, product5_count = ? " +
                    " where id = " + cartId;

            preparedStatement = connection.prepareStatement(sql);

            int cartListIndex = 0;
            for (int i = 1; i <= 10; i++) {
                if (i % 2 != 0)
                    preparedStatement.setInt(i, cart.getProductsIdList().get(cartListIndex));
                else{
                    preparedStatement.setInt(i, cart.getProductsCountList().get(cartListIndex));
                    cartListIndex++;
                }
            }

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cart findCartById(int cartId) {
        Cart resultCart = null;
        try {
            sql = "select * from carts where id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int product1Id = resultSet.getInt("product1_id");
                int product1Count = resultSet.getInt("product1_count");
                int product2Id = resultSet.getInt("product2_id");
                int product2Count = resultSet.getInt("product2_count");
                int product3Id = resultSet.getInt("product3_id");
                int product3Count = resultSet.getInt("product3_count");
                int product4Id = resultSet.getInt("product4_id");
                int product4Count = resultSet.getInt("product4_count");
                int product5Id = resultSet.getInt("product5_id");
                int product5Count = resultSet.getInt("product5_count");

                ArrayList<Integer> idList = new ArrayList<Integer>();
                idList.add(product1Id);
                idList.add(product2Id);
                idList.add(product3Id);
                idList.add(product4Id);
                idList.add(product5Id);

                ArrayList<Integer> countList = new ArrayList<Integer>();
                countList.add(product1Count);
                countList.add(product2Count);
                countList.add(product3Count);
                countList.add(product4Count);
                countList.add(product5Count);

                resultCart = new Cart();
                resultCart.setId(id);
                resultCart.setProductsIdList(idList);
                resultCart.setProductsCountList(countList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCart;    }

    public List<Cart> findAllCarts() {
        List<Cart> resultCartsList = new ArrayList<Cart>();
        try {
            sql = "select * from carts";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id;
            int product1Id;
            int product1Count;
            int product2Id;
            int product2Count;
            int product3Id;
            int product3Count;
            int product4Id;
            int product4Count;
            int product5Id;
            int product5Count;
            ArrayList<Integer> idList;
            ArrayList<Integer> countList;
            Cart resultCart;

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                product1Id = resultSet.getInt("product1_id");
                product1Count = resultSet.getInt("product1_count");
                product2Id = resultSet.getInt("product2_id");
                product2Count = resultSet.getInt("product2_count");
                product3Id = resultSet.getInt("product3_id");
                product3Count = resultSet.getInt("product3_count");
                product4Id = resultSet.getInt("product4_id");
                product4Count = resultSet.getInt("product4_count");
                product5Id = resultSet.getInt("product5_id");
                product5Count = resultSet.getInt("product5_count");

                idList = new ArrayList<Integer>();
                idList.add(product1Id);
                idList.add(product2Id);
                idList.add(product3Id);
                idList.add(product4Id);
                idList.add(product5Id);

                countList = new ArrayList<Integer>();
                countList.add(product1Count);
                countList.add(product2Count);
                countList.add(product3Count);
                countList.add(product4Count);
                countList.add(product5Count);

                resultCart = new Cart();
                resultCart.setId(id);
                resultCart.setProductsIdList(idList);
                resultCart.setProductsCountList(countList);

                resultCartsList.add(resultCart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCartsList;    }
}
