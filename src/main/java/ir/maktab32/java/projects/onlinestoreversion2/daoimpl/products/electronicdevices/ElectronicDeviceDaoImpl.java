package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices.ElectronicDeviceDao;
import ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.ProductDaoImpl;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.ElectronicDevice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElectronicDeviceDaoImpl extends ProductDaoImpl implements ElectronicDeviceDao {
    public void addElectronicDevice(ElectronicDevice electronicDevice) {
        try {
            super.addProduct(electronicDevice);
            int productId = super.findAllProducts().get(super.findAllProducts().size()-1).getProductId();

            sql = "insert into electronic_devices(voltage, product_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, electronicDevice.getVoltage());
            preparedStatement.setInt(2, productId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteElectronicDevice(int electronicDeviceId) {
        try {
            int productId = findElectronicDeviceById(electronicDeviceId).getProductId();

            sql = "delete from electronic_devices where id = " + electronicDeviceId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteProduct(productId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editElectronicDevice(int electronicDeviceId, ElectronicDevice electronicDevice) {
        try {
            int productId = findElectronicDeviceById(electronicDeviceId).getProductId();
            super.editProduct(productId,electronicDevice);

            sql = "update electronic_devices set voltage = ? where id = " + electronicDeviceId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, electronicDevice.getVoltage());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ElectronicDevice findElectronicDeviceById(int electronicDeviceId) {
        ElectronicDevice resultElectronicDevice = null;
        try {
            int productId = 0;
            String voltage = "";

            sql = "select * from electronic_devices where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, electronicDeviceId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                voltage = resultSet.getString("voltage");
                productId = resultSet.getInt("product_id");
            }

            Product product = super.findProductById(productId);
            String title = product.getTitle();
            int price = product.getPrice();
            int count = product.getCount();

            resultElectronicDevice = new ElectronicDevice(title, price, count, voltage);
            resultElectronicDevice.setProductId(productId);
            resultElectronicDevice.setElectronicDeviceId(electronicDeviceId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultElectronicDevice;
    }

    public List<ElectronicDevice> findAllElectronicDevices() {
        List<ElectronicDevice> resultElectronicDevicesList = new ArrayList<ElectronicDevice>();
        try {
            int electronicDeviceId;
            String voltage;
            int productId;
            Product product;
            String title;
            int price;
            int count;

            sql = "select * from electronic_devices";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                electronicDeviceId = resultSet.getInt("id");
                voltage = resultSet.getString("voltage");
                productId = resultSet.getInt("product_id");
                product = super.findProductById(productId);
                title = product.getTitle();
                price = product.getPrice();
                count = product.getCount();
                resultElectronicDevicesList.add(new ElectronicDevice(title, price, count, voltage));
                resultElectronicDevicesList.get(resultElectronicDevicesList.size()-1).setProductId(productId);
                resultElectronicDevicesList.get(resultElectronicDevicesList.size()-1).setElectronicDeviceId(electronicDeviceId);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultElectronicDevicesList;
    }
}
