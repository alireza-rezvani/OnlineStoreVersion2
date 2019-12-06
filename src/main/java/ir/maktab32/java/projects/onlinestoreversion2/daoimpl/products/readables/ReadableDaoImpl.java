package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables.ReadableDao;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.ProductDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Readable;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadableDaoImpl extends ProductDaoImpl implements ReadableDao {
    public void addReadable(Readable readable) {
        try {
            super.addProduct(readable);
            int productId = super.findAllProducts().get(super.findAllProducts().size()-1).getProductId();

            sql = "insert into readables(pages, publisher, product_id) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readable.getPages());
            preparedStatement.setString(2, readable.getPublisher());
            preparedStatement.setInt(3,productId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReadable(int readableId) {
        try {
            int productId = findReadableById(readableId).getProductId();

            sql = "delete from readables where id = " + readableId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteProduct(productId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editReadable(int readableId, Readable readable) {
        try {
            int productId = findReadableById(readableId).getProductId();
            super.editProduct(productId,readable);

            sql = "update readables set pages = ?, publisher = ? where id = " + readableId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readable.getPages());
            preparedStatement.setString(2, readable.getPublisher());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Readable findReadableById(int readableId) {
        Readable resultReadable = null;
        try {
            int productId = 0;
            int pages = 0;
            String publisher = "";

            sql = "select * from readables where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readableId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pages = resultSet.getInt("pages");
                publisher = resultSet.getString("publisher");
                productId = resultSet.getInt("product_id");
            }

            Product product = super.findProductById(productId);
            String title = product.getTitle();
            int price = product.getPrice();
            int count = product.getCount();

            resultReadable = new Readable(title, price, count, pages, publisher);
            resultReadable.setProductId(productId);
            resultReadable.setReadableId(readableId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultReadable;    }

    public List<Readable> findAllReadables() {
        List<Readable> resultReadablesList = new ArrayList<Readable>();
        try {
            int readableId;
            int pages;
            String publisher;
            int productId;
            Product product;
            String title;
            int price;
            int count;

            sql = "select * from readables";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                readableId = resultSet.getInt("id");
                pages = resultSet.getInt("pages");
                publisher = resultSet.getString("publisher");
                productId = resultSet.getInt("product_id");
                product = super.findProductById(productId);
                title = product.getTitle();
                price = product.getPrice();
                count = product.getCount();
                resultReadablesList.add(new Readable(title, price, count, pages, publisher));
                resultReadablesList.get(resultReadablesList.size()-1).setProductId(productId);
                resultReadablesList.get(resultReadablesList.size()-1).setReadableId(readableId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultReadablesList;
    }
}
