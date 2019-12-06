package ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Magazine;

import java.util.List;

public interface MagazineDao {
    void addMagazine(Magazine magazine);
    void deleteMagazine(int magazineId);
    void editMagazine(int magazineId, Magazine magazine);
    Magazine findMagazineById(int magazineId);
    List<Magazine> findAllMagazines();
}
