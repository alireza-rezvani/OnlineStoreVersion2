package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes.FormalShoeDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.FormalShoe;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.Shoe;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormalShoeDaoImpl extends ShoeDaoImpl implements FormalShoeDao {
    public void addFormalShoe(FormalShoe formalShoe) {
        try {
            super.addShoe(formalShoe);
            int shoeId = super.findAllShoes()
                    .get(super.findAllShoes().size()-1).getShoeId();

            sql = "insert into formal_shoes(material, shoe_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formalShoe.getMaterial());
            preparedStatement.setInt(2, shoeId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFormalShoe(int formalShoeId) {
        try {
            int shoeId = findFormalShoeById(formalShoeId).getShoeId();

            sql = "delete from formal_shoes where id = " + formalShoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteShoe(shoeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editFormalShoe(int formalShoeId, FormalShoe formalShoe) {
        try {
            int shoeId = findFormalShoeById(formalShoeId).getShoeId();
            super.editShoe(shoeId,formalShoe);

            sql = "update formal_shoes set material = ? where id = " + formalShoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formalShoe.getMaterial());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FormalShoe findFormalShoeById(int formalShoeId) {
        FormalShoe resultFormalShoe = null;
        try {
            int shoeId = 0;
            String material = "";

            sql = "select * from formal_shoes where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, formalShoeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                material = resultSet.getString("material");
                shoeId = resultSet.getInt("shoe_id");
            }

            Shoe shoe = super.findShoeById(shoeId);
            String title = shoe.getTitle();
            int price = shoe.getPrice();
            int count = shoe.getCount();
            Gender gender = shoe.getGender();
            int size = shoe.getSize();
            String color = shoe.getColor();
            int productId = shoe.getProductId();

            resultFormalShoe = new FormalShoe(title, price, count, gender, size, color, material);

            resultFormalShoe.setProductId(productId);
            resultFormalShoe.setShoeId(shoeId);
            resultFormalShoe.setFormalShoeId(formalShoeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultFormalShoe;
    }

    public List<FormalShoe> findAllFormalShoes() {
        List<FormalShoe> resultFormalShoesList = new ArrayList<FormalShoe>();
        try {
            int formalShoeId;
            String material;
            int shoeId;
            Shoe shoe;
            String title;
            int price;
            int count;
            Gender gender;
            int size;
            String color;
            int productId;

            sql = "select * from formal_shoes";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                formalShoeId = resultSet.getInt("id");
                material = resultSet.getString("material");
                shoeId = resultSet.getInt("shoe_id");
                shoe = super.findShoeById(shoeId);
                title = shoe.getTitle();
                price = shoe.getPrice();
                count = shoe.getCount();
                gender = shoe.getGender();
                size = shoe.getSize();
                color = shoe.getColor();
                productId = shoe.getProductId();

                resultFormalShoesList.add(new FormalShoe(title, price, count, gender, size, color, material));

                resultFormalShoesList.get(resultFormalShoesList.size()-1).setProductId(productId);
                resultFormalShoesList.get(resultFormalShoesList.size()-1).setShoeId(shoeId);
                resultFormalShoesList.get(resultFormalShoesList.size()-1).setFormalShoeId(formalShoeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultFormalShoesList;
    }
}
