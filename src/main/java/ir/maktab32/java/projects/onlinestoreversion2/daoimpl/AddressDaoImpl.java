package ir.maktab32.java.projects.onlinestoreversion2.daoimpl;

import ir.maktab32.java.projects.onlinestoreversion2.connection.ConnectionClass;
import ir.maktab32.java.projects.onlinestoreversion2.dao.AddressDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    protected Connection connection = ConnectionClass.getConnection();
    protected PreparedStatement preparedStatement = null;
    protected String sql = null;

    public void addAddress(Address address) {
        try {
            sql = "insert into addresses(province, city, street, zipcode) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address.getProvince());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getZipCode());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(int addressId) {
        try {
            sql = "delete from addresses where id = " + addressId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAddress(int addressId, Address address) {
        try {
            sql = "update addresses set province = ?, city = ?, street = ?, zipcode = ? where id = " + addressId;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, address.getProvince());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getZipCode());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address findAddressById(int addressId) {
        Address resultAddress = null;
        try {
            sql = "select * from addresses where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, addressId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String province = resultSet.getString("province");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String zipCode = resultSet.getString("zipcode");

                resultAddress = new Address(province, city, street,zipCode);
                resultAddress.setAddressId(addressId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAddress;    }

    public List<Address> findAllAddresses() {
        List<Address> resultAddressesList = new ArrayList<Address>();
        try {
            sql = "select * from addresses";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            int addressId;
            String province;
            String city;
            String street;
            String zipCode;

            while (resultSet.next()) {
                addressId = resultSet.getInt("id");
                province = resultSet.getString("province");
                city = resultSet.getString("city");
                street = resultSet.getString("street");
                zipCode = resultSet.getString("zipcode");

                resultAddressesList.add(new Address(province, city, street, zipCode));
                resultAddressesList.get(resultAddressesList.size()-1).setAddressId(addressId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAddressesList;    }
}
