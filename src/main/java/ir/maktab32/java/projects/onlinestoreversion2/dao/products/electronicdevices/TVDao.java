package ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.TV;

import java.util.List;

public interface TVDao {
    void addTV(TV tv);
    void deleteTV(int tvId);
    void editTV(int tvId, TV tv);
    TV findTVById(int tvId);
    List<TV> findAllTVs();
}
