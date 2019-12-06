package ir.maktab32.java.projects.onlinestoreversion2.daoimpl;

import ir.maktab32.java.projects.onlinestoreversion2.connection.ConnectionClass;
import ir.maktab32.java.projects.onlinestoreversion2.dao.UserDao;
import ir.maktab32.java.projects.onlinestoreversion2.model.Address;
import ir.maktab32.java.projects.onlinestoreversion2.model.Cart;
import ir.maktab32.java.projects.onlinestoreversion2.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    protected Connection connection = ConnectionClass.getConnection();
    protected PreparedStatement preparedStatement = null;
    protected String sql = null;

    //Evaluate user information before using this method
    public void addUser(User user) {
        try {
            CartDaoImpl cartDaoImpl = new CartDaoImpl();
            cartDaoImpl.addCart(new Cart());
            int cartId = cartDaoImpl.findAllCarts().get(cartDaoImpl.findAllCarts().size()-1).getId();

            AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
            addressDaoImpl.addAddress(new Address(
                    user.getAddress().getProvince(),
                    user.getAddress().getCity(),
                    user.getAddress().getStreet(),
                    user.getAddress().getZipCode()));
            int addressId = addressDaoImpl.findAllAddresses().get(addressDaoImpl.findAllAddresses().size()-1).getAddressId();

            sql = "insert into users(username, password, firstname, lastname, mobilenumber," +
                    " email, address_id, cart_id) values(?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getMobileNumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, addressId);
            preparedStatement.setInt(8, cartId);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            int cartId = findUserByUsername(username).getCartId();
            int addressId = findUserByUsername(username).getAddressId();

            sql = "delete from users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.execute();

            new CartDaoImpl().deleteCart(cartId);
            new AddressDaoImpl().deleteAddress(addressId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Evaluate user information before using this method
    public void editUser(String username, User user) {
        try {
            sql = "update users set " +
                    "password = ?, " +
                    "firstname = ?, " +
                    "lastname = ?, " +
                    "mobilenumber = ?, " +
                    "email = ? " +
                    "where username = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6,username);
            preparedStatement.execute();

            int addressId = findUserByUsername(username).getAddressId();
            new AddressDaoImpl().editAddress(addressId, user.getAddress());

            int cartId = findUserByUsername(username).getCartId();
            new CartDaoImpl().editCart(cartId, user.getCart());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByUsername(String username) {
        User resultUser = null;
        try {
            sql = "select * from users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String mobileNumber = resultSet.getString("mobilenumber");
                String email = resultSet.getString("email");
                int addressId = resultSet.getInt("address_id");
                int cartId = resultSet.getInt("cart_id");

                Address address = new AddressDaoImpl().findAddressById(addressId);
                Cart cart = new CartDaoImpl().findCartById(cartId);

                resultUser = new User(firstName,lastName,mobileNumber,email,
                        address ,username, password);
                resultUser.setAddressId(addressId);
                resultUser.setCartId(cartId);
                resultUser.setCart(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;    }

    public List<User> findAllUsers() {
        List<User> resultUsersList = new ArrayList<User>();
        try {
            sql = "select * from users";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            String username;
            String password;
            String firstName;
            String lastName;
            String mobileNumber;
            String email;
            int addressId;
            int cartId;
            User userItem;


            while (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                mobileNumber = resultSet.getString("mobilenumber");
                email = resultSet.getString("email");
                addressId = resultSet.getInt("address_id");
                cartId = resultSet.getInt("cart_id");

                Address address = new AddressDaoImpl().findAddressById(addressId);
                Cart cart = new CartDaoImpl().findCartById(cartId);

                userItem = new User(firstName,lastName,mobileNumber,email,
                        address ,username, password);
                userItem.setCartId(cartId);
                userItem.setAddressId(addressId);
                userItem.setCart(cart);

                resultUsersList.add(userItem);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUsersList;
    }
}
