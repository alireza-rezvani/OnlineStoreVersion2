package ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.Radio;

import java.util.List;

public interface RadioDao {
    void addRadio(Radio radio);
    void deleteRadio(int radioId);
    void editRadio(int radioId, Radio radio);
    Radio findRadioById(int radioId);
    List<Radio> findAllRadios();
}
