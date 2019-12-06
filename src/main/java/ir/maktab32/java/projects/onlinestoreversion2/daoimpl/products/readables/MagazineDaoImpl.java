package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables.MagazineDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Magazine;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Readable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineDaoImpl extends ReadableDaoImpl implements MagazineDao {
    public void addMagazine(Magazine magazine) {
        try {
            super.addReadable(magazine);
            int readableId = super.findAllReadables()
                    .get(super.findAllReadables().size()-1).getReadableId();

            sql = "insert into magazines(editor, readable_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, magazine.getEditor());
            preparedStatement.setInt(2, readableId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMagazine(int magazineId) {
        try {
            int readableId = findMagazineById(magazineId).getReadableId();

            sql = "delete from magazines where id = " + magazineId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteReadable(readableId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editMagazine(int magazineId, Magazine magazine) {
        try {
            int readableId = findMagazineById(magazineId).getReadableId();
            super.editReadable(readableId,magazine);

            sql = "update magazines set editor = ? where id = " + magazineId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, magazine.getEditor());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Magazine findMagazineById(int magazineId) {
        Magazine resultMagazine = null;
        try {
            int readableId = 0;
            String editor = "";

            sql = "select * from magazines where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, magazineId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                editor = resultSet.getString("editor");
                readableId = resultSet.getInt("readable_id");
            }

            Readable readable = super.findReadableById(readableId);
            String title = readable.getTitle();
            int price = readable.getPrice();
            int count = readable.getCount();
            int pages = readable.getPages();
            String publisher = readable.getPublisher();
            int productId = readable.getProductId();

            resultMagazine = new Magazine(title, price, count, pages, publisher, editor);

            resultMagazine.setProductId(productId);
            resultMagazine.setReadableId(readableId);
            resultMagazine.setMagazineId(magazineId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMagazine;
    }

    public List<Magazine> findAllMagazines() {
        List<Magazine> resultMagazinesList = new ArrayList<Magazine>();
        try {
            int magazineId;
            String editor;
            int readableId;
            Readable readable;
            String title;
            int price;
            int count;
            int pages;
            String publisher;
            int productId;

            sql = "select * from magazines";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                magazineId = resultSet.getInt("id");
                editor = resultSet.getString("editor");
                readableId = resultSet.getInt("readable_id");
                readable = super.findReadableById(readableId);
                title = readable.getTitle();
                price = readable.getPrice();
                count = readable.getCount();
                pages = readable.getPages();
                publisher = readable.getPublisher();
                productId = readable.getProductId();

                resultMagazinesList.add(new Magazine(title, price, count, pages, publisher, editor));

                resultMagazinesList.get(resultMagazinesList.size()-1).setProductId(productId);
                resultMagazinesList.get(resultMagazinesList.size()-1).setReadableId(readableId);
                resultMagazinesList.get(resultMagazinesList.size()-1).setMagazineId(magazineId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMagazinesList;
    }
}
