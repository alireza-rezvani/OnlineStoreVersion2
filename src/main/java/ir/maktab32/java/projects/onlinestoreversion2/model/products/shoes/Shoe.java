package ir.maktab32.java.projects.onlinestoreversion2.model.products.shoes;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.Product;
import ir.maktab32.java.projects.onlinestoreversion2.utilities.Gender;

public class Shoe extends Product {
    private int shoeId;
    private Gender gender;
    private int size;
    private String color;

    public Shoe(String title, int price, int count, Gender gender, int size, String color) {
        super(title, price, count);
        this.gender = gender;
        this.size = size;
        this.color = color;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
