package ir.maktab32.java.projects.onlinestoreversion2.daoimpl.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices.RadioDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.ElectronicDevice;
import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.Radio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RadioDaoImpl extends ElectronicDeviceDaoImpl implements RadioDao {
    public void addRadio(Radio radio) {
        try {
            super.addElectronicDevice(radio);
            int electronicDeviceId = super.findAllElectronicDevices()
                    .get(super.findAllElectronicDevices().size()-1).getElectronicDeviceId();

            sql = "insert into radios(supported_bands, electronic_device_id) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, radio.getSupportedBands());
            preparedStatement.setInt(2, electronicDeviceId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRadio(int radioId) {
        try {
            int electronicDeviceId = findRadioById(radioId).getElectronicDeviceId();

            sql = "delete from radios where id = " + radioId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            super.deleteElectronicDevice(electronicDeviceId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editRadio(int radioId, Radio radio) {
        try {
            int electronicDeviceId = findRadioById(radioId).getElectronicDeviceId();
            super.editElectronicDevice(electronicDeviceId,radio);

            sql = "update radios set supported_bands = ? where id = " + radioId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, radio.getSupportedBands());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Radio findRadioById(int radioId) {
        Radio resultRadio = null;
        try {
            int electronicDeviceId = 0;
            String supportedBands = "";

            sql = "select * from radios where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, radioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                supportedBands = resultSet.getString("supported_bands");
                electronicDeviceId = resultSet.getInt("electronic_device_id");
            }

            ElectronicDevice electronicDevice = super.findElectronicDeviceById(electronicDeviceId);
            String title = electronicDevice.getTitle();
            int price = electronicDevice.getPrice();
            int count = electronicDevice.getCount();
            String voltage = electronicDevice.getVoltage();
            int productId = electronicDevice.getProductId();

            resultRadio = new Radio(title, price, count, voltage,supportedBands);

            resultRadio.setProductId(productId);
            resultRadio.setElectronicDeviceId(electronicDeviceId);
            resultRadio.setRadioId(radioId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultRadio;    }

    public List<Radio> findAllRadios() {
        List<Radio> resultRadiosList = new ArrayList<Radio>();
        try {
            int radioId;
            String supportedBands;
            int electronicDeviceId;
            ElectronicDevice electronicDevice;
            String title;
            int price;
            int count;
            String voltage;
            int productId;

            sql = "select * from radios";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                radioId = resultSet.getInt("id");
                supportedBands = resultSet.getString("supported_bands");
                electronicDeviceId = resultSet.getInt("electronic_device_id");
                electronicDevice = super.findElectronicDeviceById(electronicDeviceId);
                title = electronicDevice.getTitle();
                price = electronicDevice.getPrice();
                count = electronicDevice.getCount();
                voltage = electronicDevice.getVoltage();
                productId = electronicDevice.getProductId();

                resultRadiosList.add(new Radio(title, price, count, voltage,supportedBands));

                resultRadiosList.get(resultRadiosList.size()-1).setProductId(productId);
                resultRadiosList.get(resultRadiosList.size()-1).setElectronicDeviceId(electronicDeviceId);
                resultRadiosList.get(resultRadiosList.size()-1).setRadioId(radioId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultRadiosList;
    }
}
