package ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.Shoe;

import java.util.List;

public interface ShoeDao {
    void addShoe(Shoe shoe);
    void deleteShoe(int shoeId);
    void editShoe(int shoeId, Shoe shoe);
    Shoe findShoeById(int shoeId);
    List<Shoe> findAllShoes();
}
