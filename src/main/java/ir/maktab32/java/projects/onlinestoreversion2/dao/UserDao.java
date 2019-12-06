package ir.maktab32.java.projects.onlinestoreversion2.dao;

import ir.maktab32.java.projects.onlinestoreversion2.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(String username);
    void editUser(String username, User user);
    User findUserByUsername(String username);
    List<User> findAllUsers();
}
