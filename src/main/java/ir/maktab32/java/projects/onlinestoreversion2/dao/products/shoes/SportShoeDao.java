package ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.SportShoe;

import java.util.List;

public interface SportShoeDao {
    void addSportShoe(SportShoe sportShoe);
    void deleteSportShoe(int sportShoeId);
    void editSportShoe(int sportShoeId, SportShoe sportShoe);
    SportShoe findSportShoeById(int sportShoeId);
    List<SportShoe> findAllSportShoes();
}
