package ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;

public class ElectronicDevice extends Product {
    private int electronicDeviceId;
    private String voltage;

    public ElectronicDevice(String title, int price, int count, String voltage) {
        super(title, price, count);
        this.voltage = voltage;
    }

    public int getElectronicDeviceId() {
        return electronicDeviceId;
    }

    public void setElectronicDeviceId(int electronicDeviceId) {
        this.electronicDeviceId = electronicDeviceId;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getVoltage() {
        return voltage;
    }

}
