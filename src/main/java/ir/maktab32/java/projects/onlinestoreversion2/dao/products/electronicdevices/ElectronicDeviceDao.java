package ir.maktab32.java.projects.onlinestoreversion2.dao.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices.ElectronicDevice;

import java.util.List;

public interface ElectronicDeviceDao {
    void addElectronicDevice(ElectronicDevice electronicDevice);
    void deleteElectronicDevice(int electronicDeviceId);
    void editElectronicDevice(int electronicDeviceId, ElectronicDevice electronicDevice);
    ElectronicDevice findElectronicDeviceById(int electronicDeviceId);
    List<ElectronicDevice> findAllElectronicDevices();
}
