package ir.maktab32.java.projects.onlinestoreversion2.model.products.electronicdevices;

public class TV extends ElectronicDevice {
    private int tvId;
    private String screenSize;

    public TV(String title, int price, int count, String voltage, String screenSize) {
        super(title, price, count, voltage);
        this.screenSize = screenSize;
    }

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tTV" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Voltage: " + getVoltage() +
                        ", Screen Size: " + screenSize ;
    }
}
