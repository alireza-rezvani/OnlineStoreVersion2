package ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

public class SportShoe extends Shoe {
    private int sportShoeId;
    private String usage;

    public SportShoe(String title, int price, int count, Gender gender, int size, String color, String usage) {
        super(title, price, count, gender, size, color);
        this.usage = usage;
    }

    public int getSportShoeId() {
        return sportShoeId;
    }

    public void setSportShoeId(int sportShoeId) {
        this.sportShoeId = sportShoeId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return
                "\tProduct ID: " + getProductId() + "\t--->\tSport Shoe" +
                        ", Title: " + getTitle() +
                        ", Price: " + getPrice() +
                        ", Available Count: " + getCount() +
                        ", Gender: " + getGender() +
                        ", Size: " + getSize() +
                        ", Color: " + getColor() +
                        ", Usage Case: " + usage ;
    }
}
