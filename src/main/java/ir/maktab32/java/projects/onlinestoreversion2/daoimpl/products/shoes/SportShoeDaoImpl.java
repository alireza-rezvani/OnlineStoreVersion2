package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes.SportShoeDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.Shoe;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.SportShoe;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportShoeDaoImpl extends ShoeDaoImpl implements SportShoeDao {
    public void addSportShoe(SportShoe sportShoe) {
        try {
            super.addShoe(sportShoe);
            int shoeId = super.findAllShoes()
                    .get(super.findAllShoes().size()-1).getShoeId();

            sql = "insert into sport_shoes(usage_case, shoe_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sportShoe.getUsage());
            preparedStatement.setInt(2, shoeId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSportShoe(int sportShoeId) {
        try {
            int shoeId = findSportShoeById(sportShoeId).getShoeId();

            sql = "delete from sport_shoes where id = " + sportShoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteShoe(shoeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSportShoe(int sportShoeId, SportShoe sportShoe) {
        try {
            int shoeId = findSportShoeById(sportShoeId).getShoeId();
            super.editShoe(shoeId,sportShoe);

            sql = "update sport_shoes set usage_case = ? where id = " + sportShoeId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sportShoe.getUsage());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SportShoe findSportShoeById(int sportShoeId) {
        SportShoe resultSportShoe = null;
        try {
            int shoeId = 0;
            String usage = "";

            sql = "select * from sport_shoes where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sportShoeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usage = resultSet.getString("usage_case");
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

            resultSportShoe = new SportShoe(title, price, count, gender, size, color, usage);

            resultSportShoe.setProductId(productId);
            resultSportShoe.setShoeId(shoeId);
            resultSportShoe.setSportShoeId(sportShoeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSportShoe;
    }

    public List<SportShoe> findAllSportShoes() {
        List<SportShoe> resultSportShoesList = new ArrayList<SportShoe>();
        try {
            int sportShoeId;
            String usage;
            int shoeId;
            Shoe shoe;
            String title;
            int price;
            int count;
            Gender gender;
            int size;
            String color;
            int productId;

            sql = "select * from sport_shoes";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sportShoeId = resultSet.getInt("id");
                usage = resultSet.getString("usage_case");
                shoeId = resultSet.getInt("shoe_id");
                shoe = super.findShoeById(shoeId);
                title = shoe.getTitle();
                price = shoe.getPrice();
                count = shoe.getCount();
                gender = shoe.getGender();
                size = shoe.getSize();
                color = shoe.getColor();
                productId = shoe.getProductId();

                resultSportShoesList.add(new SportShoe(title, price, count, gender, size, color, usage));

                resultSportShoesList.get(resultSportShoesList.size()-1).setProductId(productId);
                resultSportShoesList.get(resultSportShoesList.size()-1).setShoeId(shoeId);
                resultSportShoesList.get(resultSportShoesList.size()-1).setSportShoeId(sportShoeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSportShoesList;
    }
}
