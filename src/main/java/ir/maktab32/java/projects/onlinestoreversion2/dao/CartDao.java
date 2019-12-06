package ir.maktab32.java.projects.onlinestoreversion2.dao;

import ir.maktab32.java.projects.onlinestoreversion2.model.Cart;

import java.util.List;

public interface CartDao {
    void addCart(Cart cart);
    void deleteCart(int cartId);
    void editCart(int cartId, Cart cart);
    Cart findCartById(int cartId);
    List<Cart> findAllCarts();
}
