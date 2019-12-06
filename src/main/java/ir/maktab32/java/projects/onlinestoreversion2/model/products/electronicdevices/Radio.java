package ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices;

public class Radio extends ElectronicDevice {
    private int radioId;
    private String supportedBands;

    public Radio(String title, int price, int count, String voltage, String supportedBands) {
        super(title, price, count, voltage);
        this.supportedBands = supportedBands;
    }

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
    }

    public String getSupportedBands() {
        return supportedBands;
    }

    public void setSupportedBands(String supportedBands) {
        this.supportedBands = supportedBands;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tRadio" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Voltage: " + getVoltage() +
                        ", Supported Bands: " + supportedBands ;
    }
}
