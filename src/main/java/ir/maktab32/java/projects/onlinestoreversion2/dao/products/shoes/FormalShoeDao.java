package ir.maktab32.java.projects.onlinestoreversion2.dao.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes.FormalShoe;

import java.util.List;

public interface FormalShoeDao {
    void addFormalShoe(FormalShoe formalShoe);
    void deleteFormalShoe(int formalShoeId);
    void editFormalShoe(int formalShoeId, FormalShoe formalShoe);
    FormalShoe findFormalShoeById(int formalShoeId);
    List<FormalShoe> findAllFormalShoes();
}
