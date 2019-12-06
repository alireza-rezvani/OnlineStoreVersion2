package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices.TVDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.ElectronicDevice;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.TV;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TVDaoImpl extends ElectronicDeviceDaoImpl implements TVDao {
    public void addTV(TV tv) {
        try {
            super.addElectronicDevice(tv);
            int electronicDeviceId = super.findAllElectronicDevices()
                    .get(super.findAllElectronicDevices().size()-1).getElectronicDeviceId();

            sql = "insert into tvs(size, electronic_device_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tv.getScreenSize());
            preparedStatement.setInt(2, electronicDeviceId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTV(int tvId) {
        try {
            int electronicDeviceId = findTVById(tvId).getElectronicDeviceId();

            sql = "delete from tvs where id = " + tvId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteElectronicDevice(electronicDeviceId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editTV(int tvId, TV tv) {
        try {
            int electronicDeviceId = findTVById(tvId).getElectronicDeviceId();
            super.editElectronicDevice(electronicDeviceId,tv);

            sql = "update tvs set size = ? where id = " + tvId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tv.getScreenSize());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TV findTVById(int tvId) {
        TV resultTV = null;
        try {
            int electronicDeviceId = 0;
            String screenSize = "";

            sql = "select * from tvs where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tvId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                screenSize = resultSet.getString("size");
                electronicDeviceId = resultSet.getInt("electronic_device_id");
            }

            ElectronicDevice electronicDevice = super.findElectronicDeviceById(electronicDeviceId);
            String title = electronicDevice.getTitle();
            int price = electronicDevice.getPrice();
            int count = electronicDevice.getCount();
            String voltage = electronicDevice.getVoltage();
            int productId = electronicDevice.getProductId();

            resultTV = new TV(title, price, count, voltage,screenSize);

            resultTV.setProductId(productId);
            resultTV.setElectronicDeviceId(electronicDeviceId);
            resultTV.setTvId(tvId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTV;
    }

    public List<TV> findAllTVs() {
        List<TV> resultTVsList = new ArrayList<TV>();
        try {
            int tvId;
            String screenSize;
            int electronicDeviceId;
            ElectronicDevice electronicDevice;
            String title;
            int price;
            int count;
            String voltage;
            int productId;

            sql = "select * from tvs";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tvId = resultSet.getInt("id");
                screenSize = resultSet.getString("size");
                electronicDeviceId = resultSet.getInt("electronic_device_id");
                electronicDevice = super.findElectronicDeviceById(electronicDeviceId);
                title = electronicDevice.getTitle();
                price = electronicDevice.getPrice();
                count = electronicDevice.getCount();
                voltage = electronicDevice.getVoltage();
                productId = electronicDevice.getProductId();

                resultTVsList.add(new TV(title, price, count, voltage,screenSize));

                resultTVsList.get(resultTVsList.size()-1).setProductId(productId);
                resultTVsList.get(resultTVsList.size()-1).setElectronicDeviceId(electronicDeviceId);
                resultTVsList.get(resultTVsList.size()-1).setTvId(tvId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultTVsList;
    }
}
